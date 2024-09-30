package com.yrl;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is an Item Class that represents items for the purposes
 *   		 of converting csv data to xml and json data and printing reports
 */

public abstract class Item {
	
	private final String code;
	private final String name;
	private final String cost;
	
	public Item(String code, String name, String cost) {
		super();
		this.code = code;
		this.name = name;
		this.cost = cost;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Double getCost() {
		return Double.parseDouble(cost);
	}
	
	public Double roundToTwoDecimals(Double x) {
		return (double) (Math.round(x * 100) / 100);
	}
	
	public abstract Double getTax();
	
	public abstract Double getTotal();
	
	
}