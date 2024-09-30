package com.yrl;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/23/02
 *  
 *  Purpose: This is a DataConverter Program that converts data from csv format 
 *  		 to xml and json using libraries like xstream and gson. It then prints
 *           the results to files.
 */

public class DataConverter { 
	
	public static void main(String[] args) throws IOException{
		
		/**
		 *  Filling lists
		 */
		List<Person> Persons = DataLoader.loadPersons();
		List<Store> Stores = DataLoader.loadStores();
		List<Item> Items = DataLoader.loadItems();
		
		/**
		 *  Declaring to help with automation
		 */
		List<String> DataStrings = new ArrayList<String>();
		List<String> DestinationStrings = new ArrayList<String>();
		
		
		XStream xstream = new XStream();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
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
		
		/**
		 * Converting csv input to json
		 */
		String jsonPersons = gson.toJson(Persons);
		DataStrings.add(jsonPersons);
		DestinationStrings.add("data/Persons.json");
		String jsonStores = gson.toJson(Stores);
		DataStrings.add(jsonStores);
		DestinationStrings.add("data/Stores.json");
		String jsonItems = gson.toJson(Items);
		DataStrings.add(jsonItems);
		DestinationStrings.add("data/Items.json");
		
		
		PrintWriter pw = null;
		File destination = null;
		
		/**
		 *  Automating the printing of converted data
		 */
		for(int i=0; i<DataStrings.size(); i++) {
			destination = new File(DestinationStrings.get(i));
			destination.createNewFile();
			pw = new PrintWriter(destination);
			pw.println(DataStrings.get(i));
			pw.close();
		}
		
	}
}