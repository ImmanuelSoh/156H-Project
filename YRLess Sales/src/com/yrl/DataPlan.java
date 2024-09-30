package com.yrl;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is a DataPlan Class that represents data plans for the purposes
 *   		 of converting csv data to xml and json data and printing reports
 */


//TODO: Once done with Product, move on to the rest of the subclasses
public class DataPlan extends Item {
	
	public DataPlan(String code, String name, String cost) {
		super(code, name, cost);
	}

	public Double getTax() {
		return this.roundToTwoDecimals(this.getCost() * 0.055);
	}
	
	public Double getTotal() {
		return this.roundToTwoDecimals(this.getCost() * 1.055);
	}
}