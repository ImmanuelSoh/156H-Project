package com.yrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

/** 
 * Author: Immanuel Soh
 * 
 * Date: 2024/03/08
 * 
 * Purpose: This is a class that handles the Conversion of csv data to xml.
 * 
 */

public class CSVToXML {
	
	public static void convert() throws IOException {
		
		/**
		 *  Filling lists
		 */
		Map<String, Person> Persons = DataLoader.loadPersons();
		Map<String, Store> Stores = DataLoader.loadStores();
		Map<String, Item> Items = DataLoader.loadItems();
		
		/**
		 *  Declaring to help with automation
		 */
		List<String> DataStrings = new ArrayList<String>();
		List<String> DestinationStrings = new ArrayList<String>();
		
		XStream xstream = new XStream();
		
		/**
		 * Converting csv input to xml
		 */
		String xmlPersons = xstream.toXML(Persons);
		DataStrings.add(xmlPersons);
		DestinationStrings.add("data/Persons.xml");
		String xmlStores = xstream.toXML(Stores);
		DataStrings.add(xmlStores);
		DestinationStrings.add("data/Stores.xml");
		String xmlItems = xstream.toXML(Items);
		DataStrings.add(xmlItems);
		DestinationStrings.add("data/Items.xml");
		
		PrintToFile.write(DataStrings, DestinationStrings);
	}
	
	public static void print() throws IOException {
		
		/**
		 *  Filling lists
		 */
		Map<String, Person> Persons = DataLoader.loadPersons();
		Map<String, Store> Stores = DataLoader.loadStores();
		Map<String, Item> Items = DataLoader.loadItems();
		
		/**
		 *  Declaring to help with automation
		 */
		List<String> DataStrings = new ArrayList<String>();
		List<String> DestinationStrings = new ArrayList<String>();
		
		XStream xstream = new XStream();
		
		/**
		 * Converting csv input to xml
		 */
		String xmlPersons = xstream.toXML(Persons);
		DataStrings.add(xmlPersons);
		DestinationStrings.add("data/Persons.xml");
		String xmlStores = xstream.toXML(Stores);
		DataStrings.add(xmlStores);
		DestinationStrings.add("data/Stores.xml");
		String xmlItems = xstream.toXML(Items);
		DataStrings.add(xmlItems);
		DestinationStrings.add("data/Items.xml");
		
		PrintToFile.print(DataStrings, DestinationStrings);
	}
}