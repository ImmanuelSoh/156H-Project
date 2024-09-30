/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is an abstract class used to represent a general product for YRLess' business model.
 */
package com.yrl;

public abstract class Product extends Item {

    Product(String itemTag, String itemName, double itemBaseCost, char type) {
        super(itemTag, itemName, itemBaseCost, type);
    }
    
    public abstract String getDescription();

    // Abstract method to get the cost of the product
    public abstract double getCost();
}
