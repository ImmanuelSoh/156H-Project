/**
 * Author: Jaden Miller
 * Email: jmiller144@huskers.unl.edu
 * 
 * This is a people class, it will have to share an interface
 * with with Stores because the manager of a store is a person.
 * Also, any records of employees will be a subclass of people.
 */


package com.yrl;

//import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import java.util.Scanner;

public class Person {

	private String uuid;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private List<String> email = new ArrayList<String>();
	
	
	
	public Person(String uuid, String firstName, String lastName, String street, String city, String state, String zip,
			List<String> email) {
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "People [uuid=" + uuid + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", zip=" + zip + ", email=" + email + "]\n";
	}

	
//	public Person findPerson(String uuidKey, List<Person> persons) {
//		Collections.sort(persons, new UuidComparator());
//		return persons.get(Collections.binarySearch(persons, new Person(uuidKey, null, null, null, null, null, null, null)));
//	}
}
