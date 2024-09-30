package com.yrl;

public abstract class Product extends Item {

    Product(String itemTag, String itemName, double itemBaseCost, char type) {
        super(itemTag, itemName, itemBaseCost, type);
    }

    // Abstract method to get the cost of the product
    public abstract double getCost();
}
