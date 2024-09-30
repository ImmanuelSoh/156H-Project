package com.yrl;

import java.time.LocalDate;
import java.util.List;

/** 
 * Author: Immanuel Soh
 * 		   isoh2@huskers.unl.edu
 * 
 * 		   Date: 2024/04/12
 * 
 * 		   Purpose: This class is used to represent the different elements of a sale
 */

public class Sale {
	
	private String saleCode;
	private Store store;
	private Person customer;
	private Person manager;
	private LocalDate saleDate;
	private List<Item> saleItems;
	
	public Sale(String saleCode, Store store, Person customer, Person manager, LocalDate saleDate) {
		this.saleCode = saleCode;
		this.store = store;
		this.customer = customer;
		this.manager = manager;
		this.saleDate = saleDate;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public Store getStore() {
		return store;
	}

	public Person getCustomer() {
		return customer;
	}

	public Person getManager() {
		return manager;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}
	
	public List<Item> getItems() {
		return saleItems;
	}
	
}