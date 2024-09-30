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
}
