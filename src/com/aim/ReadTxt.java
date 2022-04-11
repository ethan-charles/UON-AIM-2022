package com.aim;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;


public class ReadTxt {

	public static void main(String[] args) {
		
		try { // Prevent file creation or read failure by catching the error, and printing it with catch or throw
			 
			/* Read file */
			String pathname = "source/test1_4_20.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
			FileReader fileReader = new FileReader(pathname);
			BufferedReader buffer = new BufferedReader(fileReader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = "";
			line = buffer.readLine();
			ArrayList<Integer> ReadNumbers = new ArrayList<Integer>();

			while (line != null) {
				
				for(int i = 0; i < line.length(); i ++) {
					if(line.substring(i,i+1).equals(" ")) {
						ReadNumbers.add(Integer.valueOf(line.substring(0,i)).intValue());
						ReadNumbers.add(Integer.valueOf(line.substring(i+1,line.length())).intValue());
						line = buffer.readLine(); //Read one line data once
						break;
					}
				}
			}
			fileReader.close();
			buffer.close(); // 最后记得关闭文件
			System.out.print(ReadNumbers.get(9));
 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
