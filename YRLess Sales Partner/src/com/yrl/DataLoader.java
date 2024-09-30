package com.yrl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataLoader {

    public static final String PERSONS_FILE_PATH = "data/Persons.csv";
    public static final String ITEMS_FILE_PATH = "data/Items.csv";
    public static final String SALES_FILE_PATH = "data/Sales.csv";

    public static Map<String, Person> loadPeople() {
        List<Person> result = new ArrayList<>();

        try (Scanner s = new Scanner(new File(PERSONS_FILE_PATH))) {
            s.nextLine(); // Skip header line
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] tokens = line.split(",");
                    String uuid = tokens[0];
                    String firstName = tokens[1];
                    String lastName = tokens[2];
                    String street = tokens[3];
                    String city = tokens[4];
                    String state = tokens[5];
                    String zip = tokens[6];
                    List<String> email = new ArrayList<>();
                    for (int i = 7; i < tokens.length; i++) {
                        email.add(tokens[i]);
                    }
                    Person person = new Person(uuid, firstName, lastName, street, city, state, zip, email);
                    result.add(person);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Encountered Error while loading people", e);
        }

        return personMapper(result);
    }

    public static Map<String, Person> personMapper(List<Person> persons) {
        Map<String, Person> personMap = new HashMap<>();
        for (Person person : persons) {
            personMap.put(person.getUuid(), person);
        }
        return personMap;
    }

    public static Map<String, Purchase> loadItems() {
        List<Purchase> result = new ArrayList<>();

        try (Scanner s = new Scanner(new File(ITEMS_FILE_PATH))) {
            s.nextLine(); // Skip header line
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] tokens = line.split(",");
                    String code = tokens[0];
                    char type = tokens[1].charAt(0);
                    String name = tokens[2];
                    double baseCost = Double.parseDouble(tokens[3]);
                    Purchase item = new Purchase(code, name, baseCost, type);
                    result.add(item);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Encountered Error while loading items", e);
        }

        return itemMapper(result);
    }

    public static Map<String, Purchase> itemMapper(List<Purchase> items) {
        Map<String, Purchase> itemMap = new HashMap<>();
        for (Purchase item : items) {
            itemMap.put(item.getCode(), item);
        }
        return itemMap;
    }
    


	public static final String FILE_PATH = "data/Stores.csv";

	public static Map<String, Store> loadStores(Map<String, Person> personsMap) {

		List<Store> result = new ArrayList<Store>();
		
		String line = null;

		try (Scanner s = new Scanner(new File(FILE_PATH))) {
			line = s.nextLine();
			while(s.hasNextLine()) {
				//storeCode,managerUUID,street,city,state,zip
				line = s.nextLine();
				if (!line.trim().isEmpty()) {
					Store e = null;
					String tokens[] = line.split(",");
					String storeCode = tokens[0];
					String managerUUID = tokens[1];
					String street = tokens[2];
					String city = tokens[3];
					String state = tokens[4];
					String zip = tokens[5];
										
					e = new Store(storeCode, managerUUID, street, city, state, zip, personsMap);

					result.add(e);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Encountered Error on line " + line, e);
		}

		return storeMapper(result);
	}
	
	
	public static Map<String, Store> storeMapper(List<Store> stores) {
        Map<String, Store> storeMap = new HashMap<>();
        for (Store store : stores) {
            storeMap.put(store.getStoreCode(), store);
        }
        return storeMap;
    }

    public static Map<String, Sale> loadSales(Map<String, Person> personsMap, Map<String, Store> storesMap) {
        List<Sale> result = new ArrayList<>();

        try (Scanner s = new Scanner(new File(SALES_FILE_PATH))) {
            s.nextLine(); // Skip header line
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] tokens = line.split(",");
                    String saleCode = tokens[0];
                    String storeCode = tokens[1];
                    String customerUuid = tokens[2];
                    String salesPersonUuid = tokens[3];
                    String date = tokens[4];
                    Sale sale = new Sale(saleCode, storeCode, customerUuid, salesPersonUuid, date, personsMap, storesMap);
                    result.add(sale);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Encountered Error while loading sales", e);
        }

        return saleMapper(result);
    }

    public static Map<String, Sale> saleMapper(List<Sale> sales) {
        Map<String, Sale> saleMap = new HashMap<>();
        for (Sale sale : sales) {
            saleMap.put(sale.getSaleCode(), sale);
        }
        return saleMap;
    }
}
