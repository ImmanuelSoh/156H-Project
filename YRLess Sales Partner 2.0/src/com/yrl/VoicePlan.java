package com.yrl;

public class VoicePlan extends Item {
    
    String phoneNumber;
    int numDays;
    
    VoicePlan(String itemTag, String itemName, double itemBaseCost, char type, String phoneNumber, int numDays){
        super(itemTag, itemName, itemBaseCost, type);
        this.phoneNumber = phoneNumber;
        this.numDays = numDays;
    }
    
    @Override
    public double getTaxes() {
        return getCost() * 0.065; // 6.5% sales tax for voice plans
    }
    
    @Override
    public double getCost() {
        return getitemBaseCost() * (this.numDays / 30.0);
    }
}
