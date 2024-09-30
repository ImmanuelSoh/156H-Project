package com.yrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** 
 * Author: Immanuel Soh
 * 
 * Date: 2024/03/08
 * 
 * Purpose: This is a class that handles the Conversion of csv data to json.
 * 
 */

public class CSVToJSON {
	
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
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
		
		PrintToFile.print(DataStrings, DestinationStrings);
	}
}
