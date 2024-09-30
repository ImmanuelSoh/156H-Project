package com.yrl;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/23/02
 *  
 *  Purpose: This is an Item Class that represents items for the purposes
 *   		 of converting csv data to xml and json data
 */
public class Item {
	
	private String code;
	private String type;
	private String name;
	private String cost;
	
	public Item(String code, String type, String name, String cost) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.cost = cost;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}
	
	
}