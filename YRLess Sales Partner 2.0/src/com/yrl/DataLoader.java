package com.yrl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yrl.DatabaseInfo;


public class DataLoader {

    public static final String PERSONS_FILE_PATH = "data/Persons.csv";
    public static final String ITEMS_FILE_PATH = "data/Items.csv";
    public static final String SALES_FILE_PATH = "data/Sales.csv";
    
    
    
	public static Map<String, Person> loadPeopleSQL() {
		
		
		
		
        Map<String, Person> peopleMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD)) {
            String query = "SELECT p.uuid, p.firstName, p.lastName, a.street, a.city, s.state, z.zip, e.emailId, e.email " +
                           "FROM Person p " +
                           "JOIN Address a ON p.addressId = a.addressId " +
                           "JOIN State s ON a.stateId = s.stateId " +
                           "JOIN Zip z ON a.zipId = z.zipId " +
                           "LEFT JOIN Email e ON p.personId = e.personId";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String street = rs.getString("street");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String zip = rs.getString("zip");

                    Address address = new Address(street, city, state, zip);

                    String email = rs.getString("email");
                    int emailId = rs.getInt("emailId");
                    Email emailObj = new Email(emailId, email);

                    Person person = peopleMap.get(uuid);
                    if (person == null) {
                        List<Email> emails = new ArrayList<>();
                        if (email != null) {
                            emails.add(emailObj);
                        }
                        person = new Person(uuid, firstName, lastName, address, emails);
                        peopleMap.put(uuid, person);
                    } else {
                        person.getEmails().add(emailObj);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load people from the database", e);
        }
        return peopleMap;


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//        Map<String, Person> peopleMap = new HashMap<>();
//        Connection conn = null;
//
//        try {
//            conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
//
//            // Execute SQL query to fetch data from Person table
//            String sql = "SELECT * FROM Person";
//            try (Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(sql)) {
//
//                // Process the result set
//                while (rs.next()) {
//                    // Retrieve data from the result set and create a Person object
//                    String uuid = rs.getString("personUUID");
//                    String firstName = rs.getString("firstName");
//                    String lastName = rs.getString("lastName");
//                    // You may need to retrieve other columns such as addressId and construct the Person object accordingly
//
//                    // Create a new Person object and put it directly into the map
//                    Person person = new Person(uuid, firstName, lastName, null, null, null, null, null);
//                    peopleMap.put(uuid, person);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("SQLException: ");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            // Close the connection
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        return peopleMap;
//
//		

//		Person p = null;
//		
//        List<Person> result = new ArrayList<>();
//
//		Connection conn = null;
//
//		try {
//			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
//			
//			
//			//TODO: load in person table into the person object, then convert the arraylist of objects to a map
//			
//			
//			
//		} catch (SQLException e) {
//			System.out.println("SQLException: ");
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		
//		return null;
	}


//    public static Map<String, Person> loadPeople() {
//        List<Person> result = new ArrayList<>();
//
//        try (Scanner s = new Scanner(new File(PERSONS_FILE_PATH))) {
//            s.nextLine(); // Skip header line
//            while (s.hasNextLine()) {
//                String line = s.nextLine();
//                if (!line.trim().isEmpty()) {
//                    String[] tokens = line.split(",");
//                    String uuid = tokens[0];
//                    String firstName = tokens[1];
//                    String lastName = tokens[2];
//                    String street = tokens[3];
//                    String city = tokens[4];
//                    String state = tokens[5];
//                    String zip = tokens[6];
//                    List<String> email = new ArrayList<>();
//                    for (int i = 7; i < tokens.length; i++) {
//                        email.add(tokens[i]);
//                    }
//                    Person person = new Person(uuid, firstName, lastName, street, city, state, zip, email);
//                    result.add(person);
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Encountered Error while loading people", e);
//        }
//
//        return personMapper(result);
//    }

//    public static Map<String, Person> personMapper(List<Person> persons) {
//        Map<String, Person> personMap = new HashMap<>();
//        for (Person person : persons) {
//            personMap.put(person.getUuid(), person);
//        }
//        return personMap;
//    }
	
	
//public static Map<String, Purchase> loadItemsSQL() {
//    Map<String, Item> itemsMap = new HashMap<>();
//    Connection conn = null;
//
//    try {
//        conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
//
//        // Execute SQL query to fetch data from Item table
//        String sql = "SELECT * FROM Item i JOIN ";
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            // Process the result set
//            while (rs.next()) {
//                // Retrieve data from the result set and create an Item object
//                int itemId = rs.getInt("itemId");
//                String code = rs.getString("itemCode");
//                String type = rs.getString("type");
//                String itemName = rs.getString("name");
//                double itemBaseCost = rs.getDouble("baseCost");
//
//                // Create a new Item object and put it directly into the map
//                Item item = createItem(code, itemName, itemBaseCost, type);
//                itemsMap.put(code, item);
//            }
//        }
//    } catch (SQLException e) {
//        System.out.println("SQLException: ");
//        e.printStackTrace();
//        throw new RuntimeException(e);
//    } finally {
//        // Close the connection
//        try {
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    return itemsMap;
//}
//
//private static Item createItem(String code, String itemName, double itemBaseCost, String type) {
//    // Depending on the type, create and return a specific subclass of Item
//    switch (type) {
//        case "P":
//            return new Product(code, itemName, itemBaseCost);
//        case "S":
//            return new Service(code, itemName, itemBaseCost);
//        case "D":
//            return new DigitalItem(code, itemName, itemBaseCost);
//        case "V":
//            return new VoiceItem(code, itemName, itemBaseCost);
//        default:
//            throw new IllegalArgumentException("Invalid item type: " + type);
//    }
//}


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
