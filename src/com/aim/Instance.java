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
	
	private String fileName;
	private int itemNum;
	private double capacity;
	private ArrayList<Double> ReadNumbers;

	public void readTxt() throws IOException{ // Prevent file creation or read failure by catching the error, and printing it with catch or throw
		
		while(true) {
			Scanner src = new Scanner(System.in);
			//System.out.print("Please Enter the filename: ");
			//fileName = src.next();
			fileName = "test1_4_20";
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
		
			/* Read file */
			//String pathname = "source/test1_4_20.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
		FileReader fileReader = new FileReader("source/" + fileName + ".txt");
		BufferedReader buffer = new BufferedReader(fileReader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
		
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
		buffer.close(); // 最后记得关闭文件
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public int getItemNum() {
		return this.itemNum;
	}
	
	public double getCapacity() {
		return this.capacity;
	}
	
	public ArrayList<Double> getInstance(){
		return this.ReadNumbers;
	}
	
	public double[] getItemWeightList() {
		double[] array = new double[itemNum];
		for(int i = 0; i < itemNum ; i++) {
			array[i] = ReadNumbers.get(i * 2 + 1);
		}
		return array;
	}
	
	public double[] getItemValueList() {
		double[] array = new double[itemNum];
		for(int i = 0; i < itemNum ; i++) {
			array[i] = ReadNumbers.get(i * 2);
		}
		return array;
	}
	
}
