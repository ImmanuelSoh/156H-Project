/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This class will create objects to record all of the
 * transactions that take place for the tech store called YRLess.
 * This class will have subclasses for specific types of transactions,
 * and it will have an abstract method for calculating tax.
 */

package com.yrl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Sale {

//	saleCode,storeCode,customerUUID,salesPersonUUID,date;
	private String saleCode;
	private Store storeCode;
	private Person customer;
	private Person salesPerson;
	private LocalDate date;
	private List<Item> items = new ArrayList<Item>();

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Sale(String saleCode, String storeCode, String customerUuid, String salesPersonUuid, String date, Map<String, Person> personsMap, Map<String, Store> storesMap) {
		this.saleCode = saleCode;
		this.storeCode = storesMap.get(storeCode);
		this.customer = personsMap.get(customerUuid);
		this.salesPerson = personsMap.get(salesPersonUuid);
		this.date = LocalDate.parse(date);
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public Store getStore() {
		return storeCode;
	}

	public void setStore(Store storeCode) {
		this.storeCode = storeCode;
	}

	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}

	public Person getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(Person salesPerson) {
		this.salesPerson = salesPerson;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Sale [saleCode=" + saleCode + ", storeCode=" + storeCode + ", customerUUID=" + customer
				+ ", salesPersonUUID=" + salesPerson + ", date=" + date + "]";
	}

	public static Sale getSale(String saleCodeKey, Map<String, Sale> salesMap) {
		return salesMap.get(saleCodeKey);
	}

	public void addItem(Item currentItem) {
		items.add(currentItem);
	}
	
	public double getTotalCost() {
		double total = 0;

		for (Item item : this.getItems()) {
			double taxes = item.getTaxes();
			double cost = item.getCost();

			total += cost + taxes;
		}

		return total;
	}
	
}
