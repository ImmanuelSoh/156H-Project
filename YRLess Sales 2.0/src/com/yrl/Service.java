package com.yrl;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is a Service Class that represents services for the purposes
 *   		 of converting csv data to xml and json data and printing reports
 */

// TODO: Once done with Product, move on to the rest of the subclasses

public class Service extends Item {
	
	public Service(String code, String name, String cost) {
		super(code, name, cost);
	}
	
	public Double getTax() {
		return this.roundToTwoDecimals(this.getCost() * 0.035);
	}
	
	public Double getTotal() {
		return this.roundToTwoDecimals(this.getCost() * 1.035);
	}
}