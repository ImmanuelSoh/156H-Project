/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is a class containing credentials necessary to connect to the project database.
 */

package com.yrl;

/**
 * Database connection configuration
 */
public class DatabaseInfo {

	/**
	 * User name used to connect to the SQL server
	 */
	public static final String USERNAME = "isoh2";

	/**
	 * Password used to connect to the SQL server
	 */
	public static final String PASSWORD = "oolaibahv3Ni";

	/**
	 * Connection parameters that may be necessary for server configuration
	 * 
	 */
	public static final String PARAMETERS = "";

	/**
	 * SQL server to connect to
	 */
	public static final String SERVER = "cse-linux-01.unl.edu";

	/**
	 * Fully formatted URL for a JDBC connection
	 */
	public static final String URL = String.format("jdbc:mysql://%s/%s?%s", SERVER, USERNAME, PARAMETERS);

}
