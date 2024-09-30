/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This class is used as a main class that calls the different functions of the system to be run.
 */

package com.yrl;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class SalesReport {
	
	public static void main(String[] args) throws IOException {
		Map<String, Person> personsMap = DataLoader.loadPeopleSQL();
		Map<String, Item> itemsMap = DataLoader.loadItemsSQL(personsMap);
		Map<String, Store> storesMap = DataLoader.loadStores(personsMap);
		Map<String, Sale> salesMap = DataLoader.loadSales(personsMap, storesMap);
		
		DataLoader.loadSaleItemsSQL(personsMap, itemsMap, storesMap, salesMap);
		
		Output.printOutput(personsMap, itemsMap, storesMap, salesMap);

	}
}