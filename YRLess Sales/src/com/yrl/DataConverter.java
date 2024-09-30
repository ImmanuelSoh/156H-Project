package com.yrl;


import java.io.IOException;


/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is a DataConverter Program that converts data from csv format 
 *  		 to xml and json using libraries like xstream and gson. It then prints
 *           the results to files.
 */

public class DataConverter { 

	public static void main(String[] args) throws IOException {
		
		CSVToXML.convert();
		CSVToJSON.convert();
	}
}