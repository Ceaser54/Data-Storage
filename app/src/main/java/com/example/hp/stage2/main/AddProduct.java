package com.example.hp.stage2.main;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.hp.stage2.R;
import com.example.hp.stage2.data.InventoryContract;
import com.example.hp.stage2.data.InventoryData;
import com.example.hp.stage2.data.helper;

public class AddProduct extends AppCompatActivity
{
    public static helper help;
    public static long id, the_quantity;
    public static EditText the_productedit, quantityedit, the_priceedit, the_supplieredit, the_phoneedit;
    public static Button Increase, Decrease, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        help = new helper(this);
        id = getIntent().getLongExtra("item", 0);
        the_productedit = (EditText) findViewById(R.id.product_name);
        Increase = (Button) findViewById(R.id.increase);
        Decrease = (Button) findViewById(R.id.decrease);
        quantityedit = (EditText) findViewById(R.id.Quantity);
        the_priceedit = (EditText) findViewById(R.id.price);
        add = (Button) findViewById(R.id.insert);
        the_phoneedit = (EditText) findViewById(R.id.phone);
        the_supplieredit = (EditText) findViewById(R.id.Supplier_Name);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });
        if (id == 0) {
            Toast.makeText(this, R.string.Cant, Toast.LENGTH_SHORT).show();
        } else if (id != 0) {
            add.setText(getString(R.string.save));
           EditProduct editProduct=new EditProduct();
            editProduct.Edit(id);
            add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String quantityx =quantityedit.getText().toString().trim();
                    String Pricex = the_priceedit.getText().toString().trim();
                    String ProductX = the_productedit.getText().toString().trim();
                    String Phonex = the_phoneedit.getText().toString().trim();
                    String Supplierx = the_supplieredit.getText().toString().trim();
                    if (Pricex.isEmpty() || ProductX.isEmpty() || Phonex.isEmpty() || Supplierx.isEmpty()||quantityx.equals(""))
                    {
                            Toast.makeText(AddProduct.this, R.string.savee, Toast.LENGTH_SHORT).show();
                            finish();
                    }
                    else
                        {
                        help.UpdateAll(ProductX, Pricex, quantityx, Supplierx, Phonex, id);
                        finish();
                    }
                }
            });
        }
        Decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityedit.getText().toString().equals("")) {
                    Toast.makeText(AddProduct.this, R.string.now, Toast.LENGTH_SHORT).show();
                } else {
                    the_quantity = Integer.parseInt(quantityedit.getText().toString());
                    if (the_quantity == 0 || the_quantity < 0) {
                        Toast.makeText(AddProduct.this, R.string.No, Toast.LENGTH_SHORT).show();
                        quantityedit.setText(String.valueOf(""));
                        finish();
                    } else {
                        --the_quantity;
                        quantityedit.setText(String.valueOf(the_quantity));
                    }
                }
            }
        });
        Increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityedit.getText().toString().equals("")) {
                    Toast.makeText(AddProduct.this, R.string.now, Toast.LENGTH_SHORT).show();
                } else {
                    the_quantity = Integer.parseInt(quantityedit.getText().toString());
                    if (the_quantity == 0 || the_quantity < 0) {
                        Toast.makeText(AddProduct.this, R.string.No, Toast.LENGTH_SHORT).show();
                        quantityedit.setText(String.valueOf(""));
                        finish();
                    } else {
                        ++the_quantity;
                        quantityedit.setText(String.valueOf(the_quantity));
                    }
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    private void Delete(final long itemId) {
        AlertDialog.Builder Build = new AlertDialog.Builder(this);
        Build.setMessage(R.string.del);
        Build.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (itemId == 0) {
                    DeleteAll();
                } else {
                    DeleteOne(itemId);
                }
                finish();
            }
        });
        Build.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alert = Build.create();
        alert.show();
    }
    private int DeleteOne(long id) {
        SQLiteDatabase database = help.getWritableDatabase();
        int deleted = database.delete(InventoryContract.Inventory.TABLE_NAME, InventoryContract.Inventory._ID + "=?", new String[]{String.valueOf(id)});
        return deleted;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Order:
                Call();
                return true;
            case R.id.Deleteone:
                Delete(id);
                return true;
            case R.id.DeleteAll:
                Delete(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int DeleteAll() {
        SQLiteDatabase database = help.getWritableDatabase();
        return database.delete(InventoryContract.Inventory.TABLE_NAME, null, null);
    }

    private void InsertData()
    {
        String Proudct = the_productedit.getText().toString().trim();
        String Supplier = the_supplieredit.getText().toString().trim();
        String Phone = the_phoneedit.getText().toString().trim();
        String Quantity = quantityedit.getText().toString();
        String Price = the_priceedit.getText().toString();
        if (Phone.equals("") || Supplier.equals("") || Price.equals("") || Proudct.equals("") ||Quantity.equals(""))
        {
            if(Phone.equals("")){
                the_phoneedit.setError("Enter phone number!");
            }
            if(Supplier.equals("")){
                the_supplieredit.setError("Enter supplier name");
            }
            if(Price.equals("")){
                the_priceedit.setError("Enter Price");
            }
            if(Proudct.equals("")){
                the_productedit.setError("Enter Product Name");
            }
            if(Quantity.equals("")){
                quantityedit.setError("Enter Quanitity");
            }
            Toast.makeText(this, R.string.feel, Toast.LENGTH_SHORT).show();
        }
        else
            {
            String price = String.valueOf(Integer.parseInt(Price));
            helper help = new helper(this);
            String quantity = Quantity;
            SQLiteDatabase database = help.getWritableDatabase();
            ContentValues values = new ContentValues();
            InventoryData inventory = new InventoryData(Proudct, price, quantity, Supplier, Phone);
            values.put(InventoryContract.Inventory.PRDODUCT_NAME, inventory.getproductname());
            values.put(InventoryContract.Inventory.QUANTITY, inventory.getquantity());
            values.put(InventoryContract.Inventory.PRICE, inventory.getprice());
            values.put(InventoryContract.Inventory.SUPPLIER_NAME, inventory.getsuppliername());
            values.put(InventoryContract.Inventory.PHONE, inventory.getphone());
            database.insert(InventoryContract.Inventory.TABLE_NAME, null, values);
            finish();
        }
    }

    private void Call() {
        AlertDialog.Builder Build = new AlertDialog.Builder(this);
        Build.setMessage(R.string.Dial);
        Build.setPositiveButton(R.string.telephone, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                if(the_phoneedit.getText().toString().equals(""))
                {
                    Toast.makeText(AddProduct.this,R.string.Callempty, Toast.LENGTH_SHORT).show();
                    finish();
                }
                call.setData(Uri.parse("tel:" + the_phoneedit.getText().toString().trim()));
                startActivity(call);
            }
        });
        AlertDialog alertDialog = Build.create();
        alertDialog.show();
    }
}