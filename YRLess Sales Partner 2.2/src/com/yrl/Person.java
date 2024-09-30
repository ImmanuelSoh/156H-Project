/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is a person class, it will have to share an interface
 * with with Stores because the manager of a store is a person. Also, any 
 * records of employees will be a subclass of person.
 */

package com.yrl;


import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String uuid;
    private String firstName;
    private String lastName;
    private Address address;
    private List<Email> emails;

    public Person(String uuid, String firstName, String lastName, Address address, List<Email> emails) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emails = emails;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    
    public int compareTo(Person that) {
    	int result = this.lastName.compareTo(that.lastName);
    	if (result == 0) {
    		result = this.firstName.compareTo(that.firstName);
    	}
    	return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uuid='" + uuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", emails=" + emails +
                '}';
    }
}

