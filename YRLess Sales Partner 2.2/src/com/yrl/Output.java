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
		printTotalSummaryReport(salesMap);
		printStoresSummaryReport(storesMap);
		printDetailedTotalSummaryReport(salesMap);
	}

	public static void printTotalSummaryReport(Map<String, Sale> salesMap) {
		double grandTotalTaxes = 0;
		double grandTotalCost = 0;
		int totalSales = 0;
		List<Sale> saleList = new ArrayList<Sale>(salesMap.values());

		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.println("| Summary Report - By Total                                                              |");
		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.printf("%-10s %-10s %-25s %-15s %-10s %-10s\n", "Invoice #", "Store", "Customer", "Num Items",
				"Tax", "Total");
		System.out.println("+----------------------------------------------------------------------------------------+");
		
		Comparator<Sale> c = Comparator.comparing(Sale::getTotalCost).reversed();
		Collections.sort(saleList, c);
		
		for (Sale sale : saleList) {
			
			double totalTaxes = 0;
			double subtotal = 0;

			for (Item item : sale.getItems()) {
				double taxes = roundToTwoDecimals(item.getTaxes());
				double cost = roundToTwoDecimals(item.getCost());

				totalTaxes += taxes;
				subtotal += cost + taxes;
				totalSales++;
			}

			grandTotalTaxes += totalTaxes;
			grandTotalCost += subtotal;

			// Print the summary for the current sale to the console
			System.out.printf("%-10s %-10s %-25s %-15d $%-10.2f $%-10.2f\n", sale.getSaleCode(),
					sale.getStore().getStoreCode(), sale.getCustomer().getLastName() + ", " + sale.getCustomer().getFirstName(), sale.getItems().size(),
					totalTaxes, subtotal);
		}

		System.out.println("+----------------------------------------------------------------------------------------+");
		System.out.printf("%49d               $%-10.2f $%-10.2f\n", totalSales, grandTotalTaxes, grandTotalCost);
		System.out.println("+----------------------------------------------------------------------------------------+\n");
	}

	public static void printStoresSummaryReport(Map<String, Store> storesMap) {
		double grandTotal = 0.0;
		int totalSales = 0;
		List<Store> storeList = new ArrayList<Store>(storesMap.values());
		
		// Print the header
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("| Store Sales Summary Report                                     |");
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("Store       Manager                  # Sales      Grand Total ");
		System.out.println("+----------------------------------------------------------------+");

		// Print store summary data
		Comparator<Store> m = Comparator.comparing(Store::getTotalMoney).reversed();
		Comparator<Store> c = Comparator.comparing(Store::getManager).thenComparing(m);
		Collections.sort(storeList, c);
		
		for (Store store : storeList) {
			String managerName = store.getManager().getLastName() + ", " + store.getManager().getFirstName();

			System.out.printf("%-10s %-25s %-12d $%-8.2f\n", store.getStoreCode(), managerName,
					store.getTotalSales(), roundToTwoDecimals(store.getTotalMoney()));

			grandTotal += roundToTwoDecimals(store.getTotalMoney());
			totalSales += roundToTwoDecimals(store.getTotalSales());
		}

		// Print the footer
		System.out.println("+----------------------------------------------------------------+");
		System.out.printf("%30s %7d            $%-9.2f\n", "", totalSales, grandTotal); // Using calculated total values
		System.out.println("+----------------------------------------------------------------+\n");
	}

	public static void printDetailedTotalSummaryReport(Map<String, Sale> salesMap) {

		System.out.println("+---------------------------------------------------------------+");
		System.out.println("Detailed Sale Reports                                           |");
		System.out.println("+---------------------------------------------------------------+\n");

		for (Map.Entry<String, Sale> entry : salesMap.entrySet()) {
			Sale sale = entry.getValue();

			// Print sale information
			System.out.printf("Sale     #%s%n", sale.getSaleCode());
			System.out.printf("Store    #%s%n", sale.getStore().getStoreCode());
			System.out.printf("Date     %s%n", sale.getDate());
			System.out.println("Customer:");
			System.out.printf("%s, %s (%s)%n", sale.getCustomer().getLastName(), sale.getCustomer().getFirstName(),
					sale.getCustomer().getUuid());
			System.out.printf("\t[%s]%n", sale.getCustomer().getEmails());
			System.out.printf("\t%s%n", sale.getCustomer().getAddress());

			System.out.println("\nSales Person:");
			System.out.printf("%s, %s (%s)%n", sale.getSalesPerson().getLastName(),
					sale.getSalesPerson().getFirstName(), sale.getSalesPerson().getUuid());
			System.out.printf("\t[%s]%n", sale.getSalesPerson().getEmails());
			System.out.printf("\t%s%n", sale.getSalesPerson().getAddress());
			System.out.println();

			System.out.println("Items (" + sale.getItems().size() + ")");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

			// Print items
			double saleSubtotal = 0;
			double saleTotalTaxes = 0;

			for (Item item : sale.getItems()) {
				System.out.printf("%s (%s)", item.getitemName(), item.getCode());
				System.out.printf("     Tax: $%-8.2f Cost: $%-8.2f%n", roundToTwoDecimals(item.getTaxes()), roundToTwoDecimals(item.getCost()));
				System.out.printf("%s%n", item.getDescription());

				saleSubtotal += roundToTwoDecimals(item.getCost());
				saleTotalTaxes += roundToTwoDecimals(item.getTaxes());
			}

			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.printf("Subtotals: Tax: $%-8.2f Cost: $%-8.2f%n", saleTotalTaxes, saleSubtotal);
			System.out.printf("Grand Total: $%-8.2f%n", (saleSubtotal + saleTotalTaxes));
			System.out.println("\n\n");

		}

	}
	
	public static double roundToTwoDecimals(double d) {
		d = Math.round(d*100);
		d = d/100;
		return (Double) d;
	}
}
