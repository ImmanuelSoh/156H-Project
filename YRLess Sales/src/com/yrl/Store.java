package com.yrl;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is a Store Class that represents stores for the purposes
 *   		 of converting csv data to xml and json data
 */
public class Store {
	
	private String storeCode;
	private Person manager;
	private Address streetAddress;
	
	public Store(String storeCode, Person manager, Address streetAddress) {
		
		this.storeCode = storeCode;
		this.manager = manager;
		this.streetAddress = streetAddress;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public Person getManager() {
		return manager;
	}

	public Address getStreetAddress() {
		return streetAddress;
	}
	
	/**
	 * A custom-made function used to make printing Addresses easier for Store constructors
	 */
	public String getPrintableAddress() {
		return streetAddress.getPrintableAddress();
	}
	
}