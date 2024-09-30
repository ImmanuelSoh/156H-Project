/**
 * Author: Jaden Miller
 * Email: jmiller144@huskers.unl.edu
 * 
 * This is a stores class. The interface with the people
 * class for the shared manager/person uuid will relate
 * these objects.
 */
package com.yrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {

	private String storeCode;
	private Person manager;
	private String street;
	private String city;
	private String state;
	private String zip;
	private List<Sale> sales = new ArrayList<Sale>();
	private double totalCost;
	
	
	public Store(String storeCode, String managerUUID, String street, String city, String state, String zip, Map<String, Person> personsMap) {
		this.storeCode = storeCode;
		this.manager = personsMap.get(managerUUID);
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Person [storeCode=" + storeCode + ", managerUUID=" + manager + ", street=" + street + ", city="
				+ city + ", state=" + state + ", zip=" + zip + "]\n";
	}

	public Object getLocation() {
		return String.format("%s %s %s %s", street, city, state, zip);
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

	
	public double getTotalMoney(Map<String, Store> storesMap) {
		double money = 0;
        for(int i = 0; i < sales.size(); i++) {

            for(int j = 0; j < sales.get(i).getItems().size(); j++) {
                money += sales.get(i).getItems().get(j).getCost();
                money += sales.get(i).getItems().get(j).getTaxes();
            }
        }
        if (this.getTotalSales() != 0) {
            return money/this.getTotalSales();
            } else {
            return 0; // return 0 if totalSales is 0
        }
}
}
