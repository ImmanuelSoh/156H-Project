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
}
