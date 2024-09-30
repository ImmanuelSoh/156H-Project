package com.yrl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Output {

    public static void printOutput(Map<String, Person> personsMap, Map<String, Purchase> itemsMap,
                                       Map<String, Store> storesMap, Map<String, Sale> salesMap) {
        printTotalSummaryReport(salesMap);
        printStoresSummaryReport(storesMap);
    }

    public static void printStoresSummaryReport(Map<String, Store> storesMap) {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/output.txt", true))) {
            // Print the header
            writer.write("+----------------------------------------------------------------+\n");
            writer.write("| Store Sales Summary Report                                     |\n");
            writer.write("+----------------------------------------------------------------+\n");
            writer.write("| Store      | Manager            | # Sales | Grand Total         |\n");
            writer.write("+----------------------------------------------------------------+\n");

            // Print store summary data
            double grandTotal = 0.0;
            int totalSales = 0;
            for (Map.Entry<String, Store> entry : storesMap.entrySet()) {
                Store store = entry.getValue();
                String managerName = store.getManager().getLastName() + ", " + store.getManager().getFirstName();

                writer.write("| ");
                writer.write(String.format("%-10s", store.getStoreCode()));
                writer.write("| ");
                writer.write(String.format("%-20s", managerName));
                writer.write("| ");
                writer.write(String.format("%-7d", store.getTotalSales()));
                writer.write("| ");
                double storeTotalMoney = store.getTotalMoney(storesMap);
                writer.write(String.format("$%9.2f", storeTotalMoney));
                writer.write("\n");
                
                grandTotal += storeTotalMoney;
                totalSales += store.getTotalSales();
            }

            // Print the footer
            writer.write("+----------------------------------------------------------------+\n");
            writer.write(String.format("| %-61s| %-7d| $%9.2f\n", "", totalSales, grandTotal)); // Using calculated total values
            writer.write("+----------------------------------------------------------------+\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTotalSummaryReport(Map<String, Sale> salesMap) {
    	
    	String fileName = "data/output.txt"; // Replace with your file name

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            // Truncate the file by opening it in write mode
            fileWriter.close();
            System.out.println("Contents cleared successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    	
        double grandTotalTaxes = 0;
        double grandTotalCost = 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter("data/output.txt", true))) {
            writer.println("+----------------------------------------------------------------------------------------+");
            writer.println("| Summary Report - By Total                                                              |");
            writer.println("+----------------------------------------------------------------------------------------+");
            writer.printf("| %-10s %-10s %-30s %-20s %-10s %-10s |\n", "Invoice #", "Store", "Customer", "Num Items", "Tax", "Total");
            writer.println("+----------------------------------------------------------------------------------------+");

            for (Map.Entry<String, Sale> entry : salesMap.entrySet()) {
                String saleCode = entry.getKey();
                Sale sale = entry.getValue();

                double totalTaxes = 0;
                double subtotal = 0;

                for (Item item : sale.getItems()) {
                    double taxes = item.getTaxes();
                    double cost = item.getCost();

                    totalTaxes += taxes;
                    subtotal += cost + taxes;
                }

                grandTotalTaxes += totalTaxes;
                grandTotalCost += subtotal;

                // Print the summary for the current sale to the file
                writer.printf("| %-10s %-10s %-30s %-20d $%10.2f $%10.2f |\n",
                        sale.getSaleCode(), sale.getStore().getStoreCode(), sale.getCustomer().getLastName(), sale.getItems().size(), totalTaxes, subtotal);
            }

            writer.println("+----------------------------------------------------------------------------------------+");
            writer.printf("| $%10.2f $%10.2f |\n", grandTotalTaxes, grandTotalCost);
            writer.println("+----------------------------------------------------------------------------------------+");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
	private static void mapTester(Map<String, Person> personsMap, Map<String, Purchase> itemsMap,
			Map<String, Store> storesMap, Map<String, Sale> salesMap) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/output.txt"))) {
            // Write Persons
            writer.write("Persons Map:\n");
            for (Map.Entry<String, Person> entry : personsMap.entrySet()) {
                writer.write("Key: " + entry.getKey() + ", Value: " + entry.getValue() + "\n");
            }
            writer.write("\n");

            // Write Items
            writer.write("Items Map:\n");
            for (Map.Entry<String, Purchase> entry : itemsMap.entrySet()) {
                writer.write("Key: " + entry.getKey() + ", Value: " + entry.getValue() + "\n");
            }
            writer.write("\n");

            // Write Stores
            writer.write("Stores Map:\n");
            for (Map.Entry<String, Store> entry : storesMap.entrySet()) {
                writer.write("Key: " + entry.getKey() + ", Value: " + entry.getValue() + "\n");
            }
            writer.write("\n");

            // Write Sales
            writer.write("Sales Map:\n");
            for (Map.Entry<String, Sale> entry : salesMap.entrySet()) {
                writer.write("Key: " + entry.getKey() + ", Value: " + entry.getValue() + "\n");
            }
            writer.write("\n");
            
        } catch (IOException e) {
            System.err.println("Error writing to output.txt: " + e.getMessage());
        }		
	}
}