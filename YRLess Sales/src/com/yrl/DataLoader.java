package com.yrl;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is a DataLoader Program that loads in data from csv formatting for use in other programs.
 */

public class DataLoader {
	
	public static final String PERSONS_PATH = "data/Persons.csv";
	public static final String STORES_PATH = "data/Stores.csv";
	public static final String ITEMS_PATH = "data/Items.csv";
	public static final String SALES_PATH = "data/Sales.csv";
	
	
	public static Map<String, Person> loadPersons() {
		
		Map<String, Person> result = new HashMap<String, Person>();
		
		String line = null;

		try(Scanner s = new Scanner(new File(PERSONS_PATH))) {

			/**
			 *  Tokenizes person data and puts them into a list
			 */
			line = s.nextLine();
			
			while(s.hasNextLine()) {

				line = s.nextLine();
				if(!line.trim().isEmpty()) {
					Person e = null;
					String tokens[] = line.split(",");
					String uuid = tokens[0];
					String firstName = tokens[1];
					String lastName = tokens[2];
					Address streetAddress = new Address(tokens[3], tokens[4], tokens[5], tokens[6]);
					List<String> emailAddresses = new ArrayList<String>();
					Integer numTokens = line.split(",").length;
					
					/**
					 *  Detects how many email addresses exist in the given line
					 */
					for(int i=7; i<numTokens; i++) {
						emailAddresses.add(tokens[i]);
					}

					e = new Person(uuid, lastName, firstName, streetAddress, emailAddresses);

					result.put(uuid, e);
				}
			}
		} catch(Exception e) {
			throw new RuntimeException("Encountered Error on line " + line, e);
		}

		return result;
	}
	
	
	public static Map<String, Store> loadStores() {
		
		Map<String, Store> result = new HashMap<String, Store>();
		
		Map<String, Person> people = loadPersons();
		
		String line = null;

		try(Scanner s = new Scanner(new File(STORES_PATH))) {

			/**
			 *  Tokenizes store data and puts them into a list
			 */
			line = s.nextLine();
			
			while(s.hasNextLine()) {

				line = s.nextLine();
				if(!line.trim().isEmpty()) {
					Store e = null;
					String tokens[] = line.split(",");
					String storeCode = tokens[0];
					Person manager = people.get(tokens[1]);
					Address streetAddress = new Address(tokens[2], tokens[3], tokens[4], tokens[5]);

					e = new Store(storeCode, manager, streetAddress);

					result.put(storeCode, e);
				}
			}
		} catch(Exception e) {
			throw new RuntimeException("Encountered Error on line " + line, e);
		}

		return result;
		
	}
	
	public static Map<String, Item> loadItems() {
		
		Map<String, Item> result = new HashMap<String, Item>();
		
		String line = null;

		try(Scanner s = new Scanner(new File(ITEMS_PATH))) {
			
			/**
			 *  Tokenizes item data and puts them into a list
			 */
			line = s.nextLine();
			
			while(s.hasNextLine()) {

				line = s.nextLine();
				if(!line.trim().isEmpty()) {
					Item e = null;
					String tokens[] = line.split(",");
					String code = tokens[0];
					String type = tokens[1];
					String name = tokens[2];
					String cost = tokens[3];
					
					if(type.equals("P")) {
						e = new Product(code, name, cost);
						
					} else if(type.equals("S")) {
						e = new Service(code, name, cost);
						
					} else if(type.equals("D")){
						e = new DataPlan(code, name, cost);
						
					} else if(type.equals("V")){
						e = new VoicePlan(code, name, cost);
						
					}

					result.put(code, e);
				}
			}
		} catch(Exception e) {
			throw new RuntimeException("Encountered Error on line " + line, e);
		}

		return result;
	}
	
	public static Map<String, Sale> loadSales() {
		
		Map<String, Sale> result = new HashMap<String, Sale>();
		
		Map<String, Store> stores = loadStores();
		
		Map<String, Person> people = loadPersons();
		
		String line = null;

		try(Scanner s = new Scanner(new File(SALES_PATH))) {

			/**
			 *  Tokenizes store data and puts them into a list
			 */
			line = s.nextLine();
			
			while(s.hasNextLine()) {

				line = s.nextLine();
				if(!line.trim().isEmpty()) {
					Sale e = null;
					String tokens[] = line.split(",");
					String saleCode = tokens[0];
					Store store = stores.get(tokens[1]);
					Person customer = people.get(tokens[2]);
					Person manager = people.get(tokens[3]);
					LocalDate saleDate = LocalDate.parse(tokens[4]);

					e = new Sale(saleCode, store, customer, manager, saleDate);

					result.put(saleCode, e);
				}
			}
		} catch(Exception e) {
			throw new RuntimeException("Encountered Error on line " + line, e);
		}

		return result;
		
	}
}