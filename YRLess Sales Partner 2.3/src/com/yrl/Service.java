/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This class is used to represent a service in YRLess' business model.
 */
package com.yrl;

public class Service extends Item {
    
    double hours;
    Person employee;
    
    Service(String itemTag, String itemName, double itemBaseCost, char type, double hours, Person employee){
        super(itemTag, itemName, itemBaseCost, type);
        this.hours = hours;
        this.employee = employee;
    }
    
    @Override
    public double getTaxes() {
        return getCost() * 0.035; // 3.5% service tax
    }
    
    @Override
    public double getCost() {
        return this.getitemBaseCost() * this.hours;
    }
    
    public String getDescription() {
    	return String.format("Served by %s, %s %n %.2f hours @ $%.2f / hour %n", this.employee.getLastName(), this.employee.getFirstName(), this.hours, this.getitemBaseCost());
    }
}
