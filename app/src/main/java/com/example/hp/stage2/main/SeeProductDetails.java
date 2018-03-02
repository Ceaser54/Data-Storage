package com.example.hp.stage2.main;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.hp.stage2.R;
import com.example.hp.stage2.data.InventoryContract;

public class SeeProductDetails extends CursorAdapter {
    TextView Proudct, Quantity, Price, Sale,SupplierName,Phone;
    final MainActivity main;

    public SeeProductDetails(MainActivity main, Cursor c) {
        super(main, c, 0);
        this.main = main;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_see_product_details, parent, false);
    }
    @Override
    public void bindView(View v, Context context, Cursor c) {
        Sale = (TextView) v.findViewById(R.id.sale);
        final long id = c.getLong(c.getColumnIndex(InventoryContract.Inventory._ID));

        Proudct = (TextView) v.findViewById(R.id.proudct);
        int ProductIndex = c.getColumnIndex(InventoryContract.Inventory.PRDODUCT_NAME);
        String product = c.getString(ProductIndex);
        Proudct.setText(product);

        Quantity = (TextView) v.findViewById(R.id.quantityy);
        int QuantityIndex = c.getColumnIndex(InventoryContract.Inventory.QUANTITY);
        final int quantity = c.getInt(QuantityIndex);
        Quantity.setText(String.valueOf(quantity));

        Sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.Sale(id, quantity);
            }
        });

        Price = (TextView) v.findViewById(R.id.price);
        int PriceIndex = c.getColumnIndex(InventoryContract.Inventory.PRICE);
        int price = c.getInt(PriceIndex);
        Price.setText(String.valueOf(price));


        SupplierName = (TextView) v.findViewById(R.id.supplier);
        int SupplierIndex = c.getColumnIndex(InventoryContract.Inventory.SUPPLIER_NAME);
        String Supplier = c.getString(SupplierIndex);
        SupplierName.setText(String.valueOf(Supplier));


        Phone = (TextView) v.findViewById(R.id.phone);
        int PhoneIndex = c.getColumnIndex(InventoryContract.Inventory.PHONE);
        int phone = c.getInt(PhoneIndex);
        Phone.setText(String.valueOf(phone));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.ClickItem(id);
            }
        });
    }
}
