package com.yrl;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is an Address Class that represents addresses for the Person and Store Classes
 */
public class Address {
	
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}
	
	/**
	 *  A custom-made function used to make printing addresses easier in Person.java and Store.java
	 */
	public String getPrintableAddress() {
		return String.format("%s %s %s %s", street, city, state, zip);
	}
	
	
}