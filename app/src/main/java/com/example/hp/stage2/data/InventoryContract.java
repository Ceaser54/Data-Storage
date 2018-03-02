package com.example.hp.stage2.data;

import android.provider.BaseColumns;

/**
 * Created by hp on 14/02/2018.
 */

public class InventoryContract {
    public static final class Inventory implements BaseColumns {
        public static final String _ID = BaseColumns._ID;
        public static final String PRDODUCT_NAME = "productname";
        public static final String PRICE = "price";
        public static final String QUANTITY = "quantity";
        public static final String SUPPLIER_NAME = "supplier";
        public static final String PHONE = "phone" + "";
        public static final String TABLE_NAME = "mytable";
        public static final String CREATE_TABLE = "CREATE TABLE " +
                InventoryContract.Inventory.TABLE_NAME + "(" +
                InventoryContract.Inventory._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                InventoryContract.Inventory.PRDODUCT_NAME + " TEXT NOT NULL," +
                InventoryContract.Inventory.PRICE + " TEXT NOT NULL," +
                InventoryContract.Inventory.PHONE + " TEXT NOT NULL," +
                InventoryContract.Inventory.QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                Inventory.SUPPLIER_NAME + " TEXT NOT NULL" + ");";
    }
}
