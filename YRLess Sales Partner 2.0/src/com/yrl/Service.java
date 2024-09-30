package com.yrl;

public class Service extends Item {
    
    double hours;
    String employeeUuid;
    
    Service(String itemTag, String itemName, double itemBaseCost, char type, double hours, String employeeUuid){
        super(itemTag, itemName, itemBaseCost, type);
        this.hours = hours;
        this.employeeUuid = employeeUuid;
    }
    
    @Override
    public double getTaxes() {
        return getCost() * 0.035; // 3.5% service tax
    }
    
    @Override
    public double getCost() {
        return this.getitemBaseCost() * this.hours;
    }
}
