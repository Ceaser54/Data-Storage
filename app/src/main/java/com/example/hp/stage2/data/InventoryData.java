package com.example.hp.stage2.data;

/**
 * Created by hp on 14/02/2018.
 */

public class InventoryData {
    static String productName;
    static String price;
    static String Phone;
    static int quantity;
    static String supplierName;
    public InventoryData() {
        InventoryData inventoryData = new InventoryData();
    }

    public InventoryData(String productName, String price, String quantity, String supplierName, String Phone) {
        this.productName = productName;
        this.price = price;
        this.quantity = Integer.parseInt(quantity);
        this.supplierName = supplierName;
        this.Phone = Phone;
    }
    public String getphone() {
        return Phone;
    }

    public String getproductname() {
        return productName;
    }

    public String getprice() {
        return price;
    }

    public String getsuppliername() {
        return supplierName;
    }

    public int getquantity() {
        return quantity;
    }

}
