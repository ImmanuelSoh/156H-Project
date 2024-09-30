package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 *	Authors: Immanuel Soh (156H) and Jaden Miller (156)
 *				
 *			 isoh2@huskers.unl.edu and jmiller144@huskers.unl.edu
 *
 *	Date: 2024/04/26
 *
 */
public class SalesData {

	/**
	 * Removes all records from all tables in the database.
	 */
	public static void clearDatabase() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM Email";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM SaleItem";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM Sale";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM Store";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM Person";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM Item";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM Address";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM Zip";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "DELETE FROM State";
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Method to add a person record to the database with the provided data.
	 *
	 * @param personUuid
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static void addPerson(String personUuid, String firstName, String lastName, String street, String city,
			String state, String zip) {
		if (getPersonId(personUuid, firstName, lastName, street, city, state, zip) == -1) {
			int addressId = getAddressId(street, city, state, zip);
			if (addressId == -1) {
				addAddress(street, city, state, zip);
				addressId = getAddressId(street, city, state, zip);
			}
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
				String query = "INSERT INTO Person (uuid, firstName, lastName, addressId) VALUES (?, ?, ?, ?)";

				ps = conn.prepareStatement(query);

				ps.setString(1, personUuid);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setInt(4, addressId);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
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

	/**
	 * Used to get a personId to use for queries in addPerson()
	 * 
	 * @param personUuid
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	public static int getPersonId(String personUuid, String firstName, String lastName, String street, String city,
			String state, String zip) {
		int personId = -1;
		int addressId = getAddressId(street, city, state, zip);
		if (addressId == -1) {
			return -1;
		} else {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
				String query = "SELECT personId FROM Person WHERE personUuid = ? AND firstName = ? AND lastName = ? AND addressId = ?";
				ps = conn.prepareStatement(query);
				ps.setString(1, personUuid);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setInt(4, addressId);
				rs = ps.executeQuery();

				if (rs.next()) {
					personId = rs.getInt("personId");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return personId;
	}

	/**
	 * Creates addresses for addPerson() to properly handle data
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static void addAddress(String street, String city, String state, String zip) {
		if (getAddressId(street, city, state, zip) == -1) {
			addState(state);
			addZip(zip);
			int stateId = getStateId(state);
			int zipId = getZipId(zip);

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
				String query = "INSERT INTO Address (street, city, stateId, zipId) VALUES (?, ?, ?, ?)";

				ps = conn.prepareStatement(query);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setInt(3, stateId);
				ps.setInt(4, zipId);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
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

	/**
	 * Used to supply addPerson() with information about addresses to properly handle data
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static int getAddressId(String street, String city, String state, String zip) {
		int addressId = -1;
		int stateId = getStateId(state);
		int zipId = getZipId(zip);
		if (stateId == -1 || zipId == -1) {
			return -1;
		} else {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
				String query = "SELECT addressId FROM Address WHERE street = ? AND city = ? AND stateId = ? AND zipId = ?";
				ps = conn.prepareStatement(query);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setInt(3, stateId);
				ps.setInt(4, zipId);
				rs = ps.executeQuery();

				if (rs.next()) {
					addressId = rs.getInt("addressId");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return addressId;

	}

	/**
	 * Creates states for addAddress() to properly handle data
	 * @param state
	 */
	public static void addState(String state) {
		if (getStateId(state) == -1) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

				String query = "INSERT INTO State (state) VALUES (?)";
				ps = conn.prepareStatement(query);
				ps.setString(1, state);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
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

	/**
	 * Used to supply addAddress() and getAddress()Id with information about states to properly handle data
	 * @param state
	 * @return
	 */
	public static int getStateId(String state) {
		int stateId = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT stateId FROM State WHERE state = ?";

			ps = conn.prepareStatement(query);
			ps.setString(1, state);
			rs = ps.executeQuery();

			if (rs.next()) {
				stateId = rs.getInt("stateId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stateId;

	}

	/**
	 * Creates zips for addAddress() to properly handle data
	 * @param zip
	 */
	public static void addZip(String zip) {

		if (getZipId(zip) == -1) {

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

				String query = "INSERT INTO Zip (zip) VALUES (?)";
				ps = conn.prepareStatement(query);
				ps.setString(1, zip);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
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

	/**
	 * Used to supply addAddress() and getAddress()Id with information about zips to properly handle data
	 * @param zip
	 * @return
	 */
	public static int getZipId(String zip) {
		int zipId = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT zipId FROM Zip WHERE zip = ?";

			ps = conn.prepareStatement(query);
			ps.setString(1, zip);
			rs = ps.executeQuery();

			if (rs.next()) {
				zipId = rs.getInt("zipId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return zipId;

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personUuid</code>
	 *
	 * @param personUuid
	 * @param email
	 */
	public static void addEmail(String personUuid, String email) {
		int personId = getPersonIdOnKey(personUuid);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO Email (personId, emailAddress) VALUES (?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			ps.setString(2, email);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static int getPersonIdOnKey(String personUuid) {
		int personId = -1;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT personId FROM Person WHERE uuid = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, personUuid);
			rs = ps.executeQuery();

			if (rs.next()) {
				personId = rs.getInt("personId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return personId;
	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 *
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip) {
		
		int managerId = getPersonIdOnKey(managerCode);
		int addressId = getAddressId(street, city, state, zip);
		if (addressId == -1) {
			addAddress(street, city, state, zip);
			addressId = getAddressId(street, city, state, zip);
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO Store (storeCode, personId, addressId) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			ps.setInt(2, managerId);
			ps.setInt(3, addressId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static int getStoreIdOnKey(String storeCode) {
		int storeId = -1;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT storeId FROM Store WHERE storeCode = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				storeId = rs.getInt("storeId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return storeId;
	}

	/**
	 * Adds an item record to the database of the given <code>type</code> with the
	 * given <code>code</code>, <code>name</code> and <code>basePrice</code>.
	 *
	 * Valid values for the <code>type</code> will be <code>"Product"</code>,
	 * <code>"Service"</code>, <code>"Data"</code>, or <code>"Voice"</code>.
	 *
	 * @param itemCode
	 * @param name
	 * @param type
	 * @param basePrice
	 */
	public static void addItem(String code, String name, String type, double basePrice) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO Item (code, type, name, baseCost) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, code);
			ps.setString(2, type);
			ps.setString(3, name);
			ps.setDouble(4, basePrice);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static int getItemIdOnKey(String itemCode) {
		int itemId = -1;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT itemId FROM Item WHERE code = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, itemCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				itemId = rs.getInt("itemId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return itemId;
	}

	/**
	 * Adds an Sale record to the database with the given data.
	 *
	 * @param saleCode
	 * @param storeCode
	 * @param customerPersonUuid
	 * @param salesPersonUuid
	 * @param saleDate
	 */
	public static void addSale(String saleCode, String storeCode, String customerPersonUuid, String salesPersonUuid,
			String saleDate) {

		int storeId = getStoreIdOnKey(storeCode);
		int customerId = getPersonIdOnKey(customerPersonUuid);
		int employeeId = getPersonIdOnKey(salesPersonUuid);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO Sale (storeId, customerId, employeeId, saleCode, saleDate) VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, storeId);
			ps.setInt(2, customerId);
			ps.setInt(3, employeeId);
			ps.setString(4, saleCode);
			ps.setString(5, saleDate);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static int getSaleIdOnKey(String saleCode) {
		int saleId = -1;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			String query = "SELECT saleId FROM Sale WHERE saleCode = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, saleCode);
			rs = ps.executeQuery();

			if (rs.next()) {
				saleId = rs.getInt("saleId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return saleId;
	}

	/**
	 * Adds a particular product (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>).
	 *
	 * @param saleCode
	 * @param itemCode
	 */
	public static void addProductToSale(String saleCode, String itemCode) {

		int saleId = getSaleIdOnKey(saleCode);
		int itemId = getItemIdOnKey(itemCode);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO SaleItem (itemId, saleId) VALUES (?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			ps.setInt(2, saleId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Adds a particular leased (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the start/end date
	 * specified.
	 *
	 * @param saleCode
	 * @param startDate
	 * @param endDate
	 */
	public static void addLeaseToSale(String saleCode, String itemCode, String startDate, String endDate) {

		int saleId = getSaleIdOnKey(saleCode);
		int itemId = getItemIdOnKey(itemCode);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO SaleItem (itemId, saleId, beginningLeaseDate, endingLeaseDate) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			ps.setInt(2, saleId);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the specified
	 * number of hours. The service is done by the employee with the specified
	 * <code>servicePersonUuid</code>
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param billedHours
	 * @param servicePersonUuid
	 */
	public static void addServiceToSale(String saleCode, String itemCode, double billedHours,
			String servicePersonUuid) {

		int saleId = getSaleIdOnKey(saleCode);
		int itemId = getItemIdOnKey(itemCode);
		int employeeId = getPersonIdOnKey(servicePersonUuid);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO SaleItem (itemId, saleId, serviceHours, employeeId) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			ps.setInt(2, saleId);
			ps.setDouble(3, billedHours);
			ps.setInt(4, employeeId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Adds a particular data plan (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the specified
	 * number of gigabytes.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param gbs
	 */
	public static void addDataPlanToSale(String saleCode, String itemCode, double gbs) {

		int saleId = getSaleIdOnKey(saleCode);
		int itemId = getItemIdOnKey(itemCode);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO SaleItem (itemId, saleId, boughtGB) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			ps.setInt(2, saleId);
			ps.setDouble(3, gbs);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Adds a particular voice plan (identified by <code>itemCode</code>) to a
	 * particular sale (identified by <code>saleCode</code>) with the specified
	 * <code>phoneNumber</code> for the given number of <code>days</code>.
	 *
	 * @param saleCode
	 * @param itemCode
	 * @param phoneNumber
	 * @param days
	 */
	public static void addVoicePlanToSale(String saleCode, String itemCode, String phoneNumber, int days) {

		int saleId = getSaleIdOnKey(saleCode);
		int itemId = getItemIdOnKey(itemCode);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);

			String query = "INSERT INTO SaleItem (itemId, saleId, vpNumber, vpDays) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			ps.setInt(2, saleId);
			ps.setString(3, phoneNumber);
			ps.setInt(4, days);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
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