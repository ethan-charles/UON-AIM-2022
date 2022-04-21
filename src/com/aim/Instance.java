package com.aim;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Instance {
	
	/**
	 * Read all data from the txt file, provide functions to get
	 * @author Yichen Lu
	 */
	
	private String fileName;
	private int itemNum;
	private double capacity;
	private ArrayList<Double> ReadNumbers;


	/**
	 * Main function
	 */
	public void readTxt() throws IOException{ 
		// Prevent file creation or read failure by catching the error, and printing it with throw
		
		// Check the file if exists
		while(true) {
			Scanner src = new Scanner(System.in);
			System.out.print("Please Enter the filename: ");
			fileName = src.next();
			
			File file = new File("source/" + fileName + ".txt");
			if(file.exists()) {
				src.close();
				break;
			}else {
				System.out.println("File doesn't exist!\n");
			}
			
		}

		String line = "";
		ArrayList<Double> ReadNumbers = new ArrayList<Double>();
		
		// Read file from source folder
		FileReader fileReader = new FileReader("source/" + fileName + ".txt");
		BufferedReader buffer = new BufferedReader(fileReader);
		
		line = buffer.readLine();
	    for(int i = 0; i < line.length(); i++) {
	       	if(line.substring(i,i+1).equals(" ")) {
	        	itemNum = Integer.valueOf(line.substring(0,i)).intValue();
	        	capacity = Double.parseDouble(line.substring(i+1,line.length()));
	        }
	    }
	    
	    line = buffer.readLine();
		while (line != null) {
			for(int i = 0; i < line.length(); i ++) {
				if(line.substring(i,i+1).equals(" ")) {
					ReadNumbers.add(Double.parseDouble(line.substring(0,i)));
					ReadNumbers.add(Double.parseDouble(line.substring(i+1,line.length())));
					line = buffer.readLine(); //Read one line data once
					break;
				}
			}
		}
		this.ReadNumbers = ReadNumbers;
		fileReader.close();
		buffer.close();
		// Close file
	}
	
	/**
	 * Get filename
	 */
	public String getFileName() {
		return this.fileName;
	}
	
	/**
	 * Get item number
	 */
	public int getItemNum() {
		return this.itemNum;
	}
	
	/**
	 * Get capacity
	 */
	public double getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Get all item
	 */
	public ArrayList<Double> getInstance(){
		return this.ReadNumbers;
	}
	
	/**
	 * Get all item's weight
	 */
	public double[] getItemWeightList() {
		double[] array = new double[itemNum];
		for(int i = 0; i < itemNum ; i++) {
			array[i] = ReadNumbers.get(i * 2 + 1);
		}
		return array;
	}
	
	/**
	 * Get all item's value
	 */
	public double[] getItemValueList() {
		double[] array = new double[itemNum];
		for(int i = 0; i < itemNum ; i++) {
			array[i] = ReadNumbers.get(i * 2);
		}
		return array;
	}
	
}
