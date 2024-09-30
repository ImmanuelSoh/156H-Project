/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is a class that loads in data from 
 * the project database through the use of the JDBC library.
 */
package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yrl.DatabaseInfo;

public class DataLoader {

	public static Map<String, Person> loadPeopleSQL() {

		Map<String, Person> peopleMap = new HashMap<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT p.uuid, p.firstName, p.lastName, a.street, a.city, s.state, z.zip, e.emailId, e.emailAddress "
					+ "FROM Person p " + "JOIN Address a ON p.addressId = a.addressId "
					+ "JOIN State s ON a.stateId = s.stateId " + "JOIN Zip z ON a.zipId = z.zipId "
					+ "LEFT JOIN Email e ON p.personId = e.personId";
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			try {

				while (rs.next()) {
					String uuid = rs.getString("uuid");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String street = rs.getString("street");
					String city = rs.getString("city");
					String state = rs.getString("state");
					String zip = rs.getString("zip");

					Address address = new Address(street, city, state, zip);

					String email = rs.getString("emailAddress");
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
			} finally {
				 
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load people from the database", e);
		}
		return peopleMap;

	}

	public static Map<String, Item> loadItemsSQL(Map<String, Person> personsMap) {
		
		Map<String, Item> itemsMap = new HashMap<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			
			String query = "SELECT si.saleitemId, i.code, i.type, i.name, i.baseCost, " + "si.beginningLeaseDate, si.endingLeaseDate, "
					+ "si.serviceHours, p.uuid AS employeeID, " + "si.vpNumber, si.vpDays, "
					+ "si.boughtGB FROM Item i JOIN SaleItem si ON i.itemId = si.itemId LEFT JOIN Person p ON si.employeeID = p.personId";
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			try {
				
				while (rs.next()) {
					int saleItemId = rs.getInt("saleitemId");
					String code = rs.getString("code");
					String type = rs.getString("type").substring(0, 1);
					String name = rs.getString("name");
					double baseCost = rs.getDouble("baseCost");

					Item item;
					switch (type) {
					case "P":
						item = new Purchase(code, name, baseCost, type.charAt(0));
						if (rs.getString("beginningLeaseDate") != null) {
							String startDate = rs.getString("beginningLeaseDate");
							String endDate = rs.getString("endingLeaseDate");
							item = new Lease(code, name, baseCost, type.charAt(0), startDate, endDate);
						} else {
							item = new Purchase(code, name, baseCost, type.charAt(0));
						}
						break;
					case "S":
						double hours = rs.getDouble("serviceHours");
						String employeeUuid = rs.getString("employeeID");
						item = new Service(code, name, baseCost, type.charAt(0), hours, personsMap.get(employeeUuid));
						break;
					case "V":
						String phoneNumber = rs.getString("vpNumber");
						int numDays = rs.getInt("vpDays");
						item = new VoicePlan(code, name, baseCost, type.charAt(0), phoneNumber, numDays);
						break;
					case "D":
						double gb = rs.getDouble("boughtGB");
						item = new DataPlan(code, name, baseCost, type.charAt(0), gb);
						break;
					default:
						throw new IllegalArgumentException("Invalid item type: " + type);
					}
					itemsMap.put(String.valueOf(saleItemId), item);
//					Item addedItem = itemsMap.get(code);
				}
			} finally {
				 
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load items from the database", e);
		}

		return itemsMap;
	}

	public static Map<String, Store> loadStores(Map<String, Person> personsMap) {
		
		Map<String, Store> storesMap = new HashMap<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT s.storeId, s.storeCode, p.uuid AS personId, a.street, a.city, st.state, z.zip FROM Store s JOIN Person p ON s.personId = p.personId JOIN Address a ON s.addressId = a.addressId Join State st ON a.stateId = st.stateId JOIN Zip z ON z.zipId = a.zipId;\r\n";
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			try {
		
				while (rs.next()) {
					String storeCode = rs.getString("storeCode");
					String managerUUID = rs.getString("personId");
					String street = rs.getString("street");
					String city = rs.getString("city");
					String state = rs.getString("state");
					String zip = rs.getString("zip");

					Address address = new Address(street, city, state, zip);
					Store store = new Store(storeCode, personsMap.get(managerUUID), address);
					storesMap.put(storeCode, store);
				}
			} finally {
				 
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to load stores from the database", e);
		}

		return storesMap;
	}

	public static Map<String, Sale> loadSales(Map<String, Person> personsMap, Map<String, Store> storesMap) {
		
		List<Sale> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT s.saleCode, st.storeCode, c.uuid AS customerId, sp.uuid as salespersonId, s.saleDate FROM Sale s JOIN Store st ON s.storeId = st.storeId JOIN Person sp ON s.employeeId = sp.personId JOIN Person c ON s.customerId = c.personId;";
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			try {

				while (rs.next()) {
					String saleCode = rs.getString("saleCode");
					String storeCode = rs.getString("storeCode");
					String customerUuid = rs.getString("customerId");
					String salesPersonUuid = rs.getString("salespersonId");
					String date = rs.getString("saleDate");
					Sale sale = new Sale(saleCode, storeCode, customerUuid, salesPersonUuid, date, personsMap,
							storesMap);
					storesMap.get(storeCode).addSale(sale);
					result.add(sale);
				}
			} finally {
				 
				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
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

	public static void loadSaleItemsSQL(Map<String, Person> personsMap, Map<String, Item> itemsMap,
			Map<String, Store> storesMap, Map<String, Sale> salesMap) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT si.saleitemId, s.saleCode, i.code AS itemCode FROM SaleItem si JOIN Sale s ON si.saleId = s.saleId JOIN Item i ON i.itemId = si.itemId;";

			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int saleItemId = rs.getInt("saleitemId");
				String saleCode = rs.getString("saleCode");
				String itemCode = rs.getString("itemCode");

				Sale sale = salesMap.get(saleCode);

				Item item = itemsMap.get(String.valueOf(saleItemId));

				if (sale != null && item != null) {
					sale.addItem(item);
				} else {
					System.out.println("Sale or item not found for saleCode: " + saleCode + " and itemCode: " + itemCode);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
