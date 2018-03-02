package com.example.hp.stage2.main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.stage2.R;
import com.example.hp.stage2.data.InventoryContract;
import com.example.hp.stage2.data.helper;

public class MainActivity extends AppCompatActivity {
    private helper help;
    private ListView list;
    private SeeProductDetails cursorInventory;
    private Cursor c;
    @Override
    protected void onResume() {
        super.onResume();
        c = QueryRead();
        cursorInventory = new SeeProductDetails(this, c);
        list.setAdapter(cursorInventory);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddData();
        list = (ListView) findViewById(R.id.list);
        TextView default_text = (TextView) findViewById(R.id.default_message);
        help = new helper(this);
        list.setEmptyView(default_text);
    }

    public void ClickItem(long id) {
        Intent intent = new Intent(this, AddProduct.class);
        intent.putExtra("item", id);
        startActivity(intent);
    }

    public void Sale(long id, int quantity) {
        help.Sell(quantity, id);
        cursorInventory.swapCursor(QueryRead());
    }

    public Cursor QueryRead() {
        SQLiteDatabase db = help.getReadableDatabase();
        String[] ArrayOfData = {InventoryContract.Inventory.PRICE, InventoryContract.Inventory._ID, InventoryContract.Inventory.PRDODUCT_NAME, InventoryContract.Inventory.QUANTITY, InventoryContract.Inventory.SUPPLIER_NAME, InventoryContract.Inventory.PHONE};
        Cursor cursor = db.query(InventoryContract.Inventory.TABLE_NAME, ArrayOfData, null, null, null, null, null);
        return cursor;
    }

    public void AddData() {
        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProduct.class);
                startActivity(intent);
            }
        });
    }
}