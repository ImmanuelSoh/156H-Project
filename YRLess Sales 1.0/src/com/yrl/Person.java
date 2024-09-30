package com.yrl;

import java.util.List;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/23/02
 *  
 *  Purpose: This is a Person Class that represents people for the purposes
 *   		 of converting csv data to xml and json data
 */
public class Person {
	
	private String uuid;
	private String lastName;
	private String firstName;
	private Address streetAddress;
	private List<String> emailAddresses;
	
	public Person(String uuid, String lastName, String firstName, Address streetAddress, List<String> emailAddresses) {
		
		this.uuid = uuid;
		this.lastName = lastName;
		this.firstName = firstName;
		this.streetAddress = streetAddress;
		this.emailAddresses = emailAddresses;
	}

	public String getUuid() {
		return uuid;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public Address getStreetAddress() {
		return streetAddress;
	}
	
	/**
	 * Used for constructors
	 */
	public String getPrintableAddress() {
		return streetAddress.getPrintableAddress();
	}

	public List<String> getEmailAddresses() {
		return emailAddresses;
	}
	
	/**
	 * Used for constructors
	 */
	public String getPrintableEmailAddresses() {
		return emailAddresses.toString();
	}
	
	
}