package com.yrl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/** 
 * Author: Immanuel Soh
 * 
 * Date: 2024/03/08
 * 
 * Purpose: This is a class that handles the printing of data to a file.
 * 
 */

public class PrintToFile {
	
	public static void write(List<String> DataStrings, List<String> DestinationStrings) throws IOException {
		
		PrintWriter pw = null;
		File destination = null;
		
		/**
		 *  Automating the writing of converted data
		 */
		for(int i=0; i<DataStrings.size(); i++) {
			destination = new File(DestinationStrings.get(i));
			destination.createNewFile();
			pw = new PrintWriter(destination);
			pw.println(DataStrings.get(i));
			pw.close();
		}
	}
	
	public static void print(List<String> DataStrings, List<String> DestinationStrings) throws IOException {
		
		/**
		 * Automating the printing of converted data
		 */
		for(int i=0; i<DataStrings.size(); i++) {
			System.out.println(DataStrings.get(i));
		}
	}
}