/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is a stores class. The interface with the people
 * class for the shared manager/person uuid will relate these objects.
 */

package com.yrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {

	private String storeCode;
	private Person manager;
	private Address address;	
	private List<Sale> sales = new ArrayList<Sale>();
	private double totalCost;
	
	
	public Store(String storeCode, Person manager, Address address) {
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
	}
	
	public void addCost(double itemCost) {
		this.totalCost += itemCost;
	}
	
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public Person getManager() {
		return manager;
	}
	public void setManager(Person manager) {
		this.manager = manager;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void addSale(Sale currentSale) {
		sales.add(currentSale);
		
	}

	public int getTotalSales() {
		return this.sales.size();
	}

	
	public double getTotalMoney() {
		double money = 0;
        for(int i = 0; i < sales.size(); i++) {

            for(int j = 0; j < sales.get(i).getItems().size(); j++) {
            	double taxes = sales.get(i).getItems().get(j).getCost();
            	double cost = sales.get(i).getItems().get(j).getTaxes();
            	
            	money += cost + taxes;
            }
        }
        return money;

	}
}
