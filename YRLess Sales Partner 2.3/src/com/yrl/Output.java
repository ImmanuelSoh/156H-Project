/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This class is used to handle the formatting and printing of received data.
 */
package com.yrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Output {

	public static void printOutput(Map<String, Person> personsMap, Map<String, Item> itemsMap,
			Map<String, Store> storesMap, Map<String, Sale> salesMap) {
		printSummaryReportByCustomer(salesMap);
		printSummaryReportByValue(salesMap);
		printSummaryReportByStore(salesMap);

	}

	public static double roundToTwoDecimals(double d) {
		d = Math.round(d * 100);
		d = d / 100;
		return (Double) d;
	}

	/**
	 * Used to print out the first of three final requested reports of sales, sorted by Customer last name then Customer first name
	 * @param salesMap used to load in data from database
	 */
	public static void printSummaryReportByCustomer(Map<String, Sale> salesMap) {

		Comparator<Sale> customerComparator = Comparator.comparing(Sale::getCustomer);
		MyList<Sale> customerLinkedList = new MyList<Sale>(customerComparator);

		for (Map.Entry<String, Sale> entry : salesMap.entrySet()) {
			customerLinkedList.add(entry.getValue());
		}

		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.println("| Sales By Customer                                                              |");
		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.printf("%-10s %-10s %-25s %-25s %-10s\n", "Sale", "Store", "Customer", "Salesperson", "Total");
		System.out.println("+----------------------------------------------------------------------------------------+");

		for (int i = 0; i < customerLinkedList.getSize(); i++) {

			Sale sale = customerLinkedList.get(i);

			double subtotal = 0;

			for (Item item : sale.getItems()) {
				double taxes = roundToTwoDecimals(item.getTaxes());
				double cost = roundToTwoDecimals(item.getCost());

				subtotal += cost + taxes;
			}

			// Print the summary for the current sale to the console
			System.out.printf("%-10s %-10s %-25s %-25s $%-10.2f\n", sale.getSaleCode(), sale.getStore().getStoreCode(),
					sale.getCustomer().getLastName() + ", " + sale.getCustomer().getFirstName(),
					sale.getSalesPerson().getLastName() + "," + sale.getSalesPerson().getFirstName(), subtotal);
		}
		System.out.println("+----------------------------------------------------------------------------------------+");

	}

	/**
	 * Used to print out the second of three final requested reports of sales, sorted by amount of money made a sale.
	 * @param salesMap used to load in data from database
	 */
	public static void printSummaryReportByValue(Map<String, Sale> salesMap) {

		Comparator<Sale> valueComparator = Comparator.comparing(Sale::getTotalCost).reversed();
		MyList<Sale> valueLinkedList = new MyList<Sale>(valueComparator);

		for (Map.Entry<String, Sale> entry : salesMap.entrySet()) {
			valueLinkedList.add(entry.getValue());
		}

		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.println("| Sales By Total                                                              |");
		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.printf("%-10s %-10s %-25s %-25s %-10s\n", "Sale", "Store", "Customer", "Salesperson", "Total");
		System.out.println("+----------------------------------------------------------------------------------------+");

		for (int i = 0; i < valueLinkedList.getSize(); i++) {

			Sale sale = valueLinkedList.get(i);

			double subtotal = 0;

			for (Item item : sale.getItems()) {
				double taxes = roundToTwoDecimals(item.getTaxes());
				double cost = roundToTwoDecimals(item.getCost());

				subtotal += cost + taxes;
			}

			// Print the summary for the current sale to the console
			System.out.printf("%-10s %-10s %-25s %-25s $%-10.2f\n", sale.getSaleCode(), sale.getStore().getStoreCode(),
					sale.getCustomer().getLastName() + ", " + sale.getCustomer().getFirstName(),
					sale.getSalesPerson().getLastName() + "," + sale.getSalesPerson().getFirstName(), subtotal);
		}
		System.out.println("+----------------------------------------------------------------------------------------+");

	}
	/**
	 * Used to print out the third of three final requested reports of sales, sorted by store code then by salesperson last name and first name.
	 * @param salesMap used to load in data from database
	 */
	public static void printSummaryReportByStore(Map<String, Sale> salesMap) {

		Comparator<Sale> storeComparator = Comparator.comparing(Sale::getStore).thenComparing(Sale::getSalesPerson);
		MyList<Sale> storeLinkedList = new MyList<Sale>(storeComparator);

		for (Map.Entry<String, Sale> entry : salesMap.entrySet()) {
			storeLinkedList.add(entry.getValue());
		}

		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.println("| Sales By Store                                                              |");
		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.printf("%-10s %-10s %-25s %-25s %-10s\n", "Sale", "Store", "Customer", "Salesperson", "Total");
		System.out.println("+----------------------------------------------------------------------------------------+");

		for (int i = 0; i < storeLinkedList.getSize(); i++) {

			Sale sale = storeLinkedList.get(i);

			double subtotal = 0;

			for (Item item : sale.getItems()) {
				double taxes = roundToTwoDecimals(item.getTaxes());
				double cost = roundToTwoDecimals(item.getCost());

				subtotal += cost + taxes;
			}

			// Print the summary for the current sale to the console
			System.out.printf("%-10s %-10s %-25s %-25s $%-10.2f\n", sale.getSaleCode(), sale.getStore().getStoreCode(),
					sale.getCustomer().getLastName() + ", " + sale.getCustomer().getFirstName(),
					sale.getSalesPerson().getLastName() + "," + sale.getSalesPerson().getFirstName(), subtotal);
		}
		System.out.println("+----------------------------------------------------------------------------------------+");

	}

}
