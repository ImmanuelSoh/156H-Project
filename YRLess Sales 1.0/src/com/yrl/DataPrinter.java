package com.yrl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/23/02
 *  
 *  Purpose: This is a DataPrinter Program that converts data from csv format 
 *  		 to xml and json using libraries like xstream and gson. It then prints
 *  		 the results to the standard output.
 */

public class DataPrinter { 
	
	public static void main(String[] args) {
		
		/**
		 * Filling lists
		 */
		List<Person> Persons = DataLoader.loadPersons();
		List<Store> Stores = DataLoader.loadStores();
		List<Item> Items = DataLoader.loadItems();
		
		/**
		 * Declaring to help with automation
		 */
		List<String> DataStrings = new ArrayList<String>();
		
		XStream xstream = new XStream();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		/**
		 * Converting csv input to xml
		 */
		String xmlPersons = xstream.toXML(Persons);
		DataStrings.add(xmlPersons);
		String xmlStores = xstream.toXML(Stores);
		DataStrings.add(xmlStores);
		String xmlItems = xstream.toXML(Items);
		DataStrings.add(xmlItems);
		
		/**
		 * Converting csv input to json
		 */
		String jsonPersons = gson.toJson(Persons);
		DataStrings.add(jsonPersons);
		String jsonStores = gson.toJson(Stores);
		DataStrings.add(jsonStores);
		String jsonItems = gson.toJson(Items);
		DataStrings.add(jsonItems);
		
		/**
		 * Automating the printing of converted data
		 */
		for(int i=0; i<DataStrings.size(); i++) {
			System.out.println(DataStrings.get(i));
		}
		
	}
}