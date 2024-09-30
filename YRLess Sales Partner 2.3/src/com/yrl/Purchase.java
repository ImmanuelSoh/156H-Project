/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This class represents a product that is purchased for YRLess' business model.
 */
package com.yrl;

public class Purchase extends Product {
    
    Purchase(String itemTag, String itemName, double itemBaseCost, char type){
        super(itemTag, itemName, itemBaseCost, type);
    }
    
    @Override
    public double getTaxes() {
        return getitemBaseCost() * 0.065; // 6.5% sales tax
    }
    
    @Override
    public double getCost() {
        return getitemBaseCost();
        
    }
    
    public String getDescription() {
    	return "Purchased Product \n";
    }
}
