package com.example.hp.stage2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 14/02/2018.
 */

public class helper extends SQLiteOpenHelper {
    private static final String database = "data.db";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public helper(Context context) {
        super(context, database, null, 1);
    }

    public Cursor Read(long data) {
        SQLiteDatabase database = getReadableDatabase();
        String[] ArrayOfData = {InventoryContract.Inventory.PRICE, InventoryContract.Inventory._ID, InventoryContract.Inventory.PRDODUCT_NAME, InventoryContract.Inventory.QUANTITY, InventoryContract.Inventory.SUPPLIER_NAME, InventoryContract.Inventory.PHONE};
        Cursor c = database.query(InventoryContract.Inventory.TABLE_NAME, ArrayOfData, InventoryContract.Inventory._ID + "=?", new String[]{String.valueOf(data)}, null, null, null);
        return c;
    }

   public void UpdateAll(String productName,String Price ,String Quantity,String supplierName,String Phone,long id)
    {
        SQLiteDatabase database = getWritableDatabase();
        InventoryData inventory = new InventoryData(productName,Price,Quantity,supplierName,Phone);
        ContentValues content = new ContentValues();
        content.put(InventoryContract.Inventory.PRICE, inventory.getprice());
        content.put(InventoryContract.Inventory.PRDODUCT_NAME, inventory.getproductname());
        content.put(InventoryContract.Inventory.QUANTITY, inventory.getquantity());
        content.put(InventoryContract.Inventory.SUPPLIER_NAME, inventory.getsuppliername());
        content.put(InventoryContract.Inventory.PHONE, inventory.getphone());
        database.update(InventoryContract.Inventory.TABLE_NAME, content, InventoryContract.Inventory._ID + "=?", new String[]{String.valueOf(id)});
    }
    public void Sell(int Quantity, long id) {
        ContentValues content = new ContentValues();
        long quantity = 1;
        SQLiteDatabase database = getWritableDatabase();
        if (Quantity > 1) {
            Quantity -= 1;
            quantity = Quantity;
        }
        content.put(InventoryContract.Inventory.QUANTITY, quantity);
        database.update(InventoryContract.Inventory.TABLE_NAME, content, InventoryContract.Inventory._ID + "=?", new String[]{String.valueOf(id)});
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InventoryContract.Inventory.CREATE_TABLE);
    }
}
