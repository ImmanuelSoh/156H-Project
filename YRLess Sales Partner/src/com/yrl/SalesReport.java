/**
 * Author: Jaden Miller
 * Email: jmiller144@huskers.unl.edu
 * 
 * Description: This code will read .csv files, format them into a
 * potential data structure for a electronics company called YRless, and then
 * is also able to output them to .json and .xml files.
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
		Map<String, Person> personsMap = DataLoader.loadPeople();
		Map<String, Purchase> itemsMap = DataLoader.loadItems();
		Map<String, Store> storesMap = DataLoader.loadStores(personsMap);
		Map<String, Sale> salesMap = DataLoader.loadSales(personsMap, storesMap);
		
		SaleItemLoader.loadItems(personsMap, itemsMap, storesMap, salesMap);
		
        Output.printOutput(personsMap, itemsMap, storesMap, salesMap);
		

	}
}