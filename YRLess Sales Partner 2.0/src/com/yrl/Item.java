/**
 * Author: Jaden Miller
 * Email: jmiller144@huskers.unl.edu
 * 
 * Description: This class will create objects to record all of the
 * transactions that take place for the tech store called YRLess.
 * This class will have subclasses for specific types of transactions,
 * and it will have an abstract method for calculating tax.
 */
package com.yrl;

import java.util.Map;

public abstract class Item {
	private String code;
	private String itemName;
	private double itemBaseCost;
	private char type;
	
	public Item(String code, String itemName, double itemBaseCost, char type) {
		this.code = code;
		this.itemName = itemName;
		this.itemBaseCost = itemBaseCost;
		this.type = type;
		
	}
		
	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getitemName() {
		return itemName;
	}
	public void setitemName(String itemName) {
		this.itemName = itemName;
	}
	public double getitemBaseCost() {
		return itemBaseCost;
	}
	public void setitemBaseCost(double itemBaseCost) {
		this.itemBaseCost = itemBaseCost;
	}
	
	public abstract double getTaxes();
	
    public abstract double getCost();
	
	@Override
	public String toString() {
		return "Items [code=" + code + ", itemName=" + itemName + ", itemBaseCost=" + itemBaseCost + "]\n";
	}

	public static Item getItem(String codeKey, Map<String, Item> itemsMap) {
		return itemsMap.get(codeKey);
	}
	
	
}
