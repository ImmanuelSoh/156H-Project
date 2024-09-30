/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This class represents a product that is leased for YRLess' business model.
 */

package com.yrl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Lease extends Product {
	
	private LocalDate startDate;
	private LocalDate endDate;

	
	
	Lease(String itemTag, String itemName, double itemBaseCost, char type, String start, String end){
		super(itemTag, itemName, itemBaseCost, type);
		startDate = LocalDate.parse(start);
		endDate = LocalDate.parse(end);

	}
	
	public double getTaxes() {
		return 0.0;
	}
	
	public double getCost() {
		return this.getitemBaseCost()*1.5 / getMonths(this.startDate, this.endDate);
	}
	
	public static int getMonths(LocalDate startDate, LocalDate endDate) {
        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        return (int) months;
    }
	
    public String getDescription() {
    	return String.format("Lease for %d months %n", this.getMonths(this.startDate, this.endDate));
    }
	
}
