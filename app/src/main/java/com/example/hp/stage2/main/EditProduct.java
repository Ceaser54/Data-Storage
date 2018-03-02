package com.example.hp.stage2.main;

import android.database.Cursor;

import com.example.hp.stage2.data.InventoryContract;

/**
 * Created by hp on 15/02/2018.
 */

public class EditProduct extends AddProduct
{
    public void Edit(long id)
    {
        Cursor c = help.Read(id);
        try {
            c.moveToFirst();
            the_priceedit.setText(c.getString(c.getColumnIndex(InventoryContract.Inventory.PRICE)));
            quantityedit.setText(c.getString(c.getColumnIndex(InventoryContract.Inventory.QUANTITY)));
            the_productedit.setText(c.getString(c.getColumnIndex(InventoryContract.Inventory.PRDODUCT_NAME)));
            the_supplieredit.setText(c.getString(c.getColumnIndex(InventoryContract.Inventory.SUPPLIER_NAME)));
            the_phoneedit.setText(c.getString(c.getColumnIndex(InventoryContract.Inventory.PHONE)));
        } catch (Exception e) {
            c.close();
        }
    }

}
