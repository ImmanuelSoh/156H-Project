/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is a connection test used to test the connection to the project database.
 */
package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.Test.None;

import com.yrl.DatabaseInfo;

public class ConnectionTest {

	@Test(expected = None.class)
	public void databaseConnectionTest() {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
