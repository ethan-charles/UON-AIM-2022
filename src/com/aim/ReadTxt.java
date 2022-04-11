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
			String pathname = "source/test1_4_20.txt"; // ����·�������·�������ԣ������Ǿ���·����д���ļ�ʱ��ʾ���·��
			FileReader fileReader = new FileReader(pathname);
			BufferedReader buffer = new BufferedReader(fileReader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
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
			buffer.close(); // ���ǵùر��ļ�
			System.out.print(ReadNumbers.get(9));
 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
