/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is a class representing a Data Plan for YRLess' business model.
 */
package com.yrl;

public class DataPlan extends Item {
    
    Double gb;
    
    DataPlan(String itemTag, String itemName, double itemBaseCost, char type, double gb){
        super(itemTag, itemName, itemBaseCost, type);
        this.gb = gb;
    }
    
    @Override
    public double getTaxes() {
        return getCost() * 0.055; // 5.5% sales tax for data plans
    }
    
    @Override
    public double getCost() {
        return getitemBaseCost() * this.gb;
    }
    
    public String getDescription() {
    	return String.format("Data Plan with %.2f GB @ $%.2f / GB %n", this.gb, this.getitemBaseCost());
    }
}
