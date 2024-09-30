package com.yrl;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is a Product Class that represents products for the purposes
 *   		 of converting csv data to xml and json data and printing reports
 */

public class Product extends Item {
	
	private double subtotal = this.getCost();
	
	public Product(String code, String name, String cost) {
		super(code, name, cost);
	}
	
	//TODO: come back and finish implementing once done with sales related classes
	public void getSubtotal() {
		
		if() {
			subtotal = (subtotal*1.5)/(x months);
		}
	}
	
	public Double getTax() {
		return this.roundToTwoDecimals(subtotal * 0.065);
	}
	
	public Double getTotal() {
		return this.roundToTwoDecimals(subtotal * 1.065);
	}
}