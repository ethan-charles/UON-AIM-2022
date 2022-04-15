package com.aim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem {
	
	private String solution; 
	static Instance instance = new Instance();
	static final int capacity = instance.ReadTxt().get(1);
	public static final int itemNum = instance.ReadTxt().get(0);
	public static final double IOM = 0,
			DOS = 0;

	public Problem() {
		
	}
	
	public int getSolutionValue(String solution) {
		int value = 0, weight = 0;
		int choice = 0;
		
		for (int i = 0; i < solution.length(); i++) {
			choice = Character.getNumericValue(solution.charAt(i));
			if (choice == 1) {
				value += instance.ReadTxt().get(2 + 2 * i);
				weight += instance.ReadTxt().get(3 + 2 * i);
			}
		}
		
		if(weight > capacity) {
			return 0;
		}else {
			return value;
		}
	}
	
	public int getApplyTimes(double IOMorDOS) {
		
		int applyTimes = 0;
		
		if (IOMorDOS >= 0 && IOMorDOS <= 1) {
			applyTimes = (int) (IOMorDOS / 0.2) + 1;
		}
		return applyTimes;
		
		/* if (IOMorDOS == 1) {
			return 6;
		}else if(IOMorDOS >= 0.8) {
			return 5;
		}else if(IOMorDOS >= 0.6) {
			return 4;
		}else if(IOMorDOS >= 0.4) {
			return 3; 
		}else if(IOMorDOS >= 0.2) {
			return 2;
		}else {
			return 1;
		}
		*/
	}
	
	public void bitFlip(int position, String solution) {
		
		StringBuffer solutionBuffer = new StringBuffer(solution);

		if (solutionBuffer.charAt(position) == '0') {
			solutionBuffer.setCharAt(position, '1');
		}else {
			solutionBuffer.setCharAt(position, '1');
		}
	}
	
	public List<String> initialSolution(){
		
		String solution1 = new String("000000");
		String solution2 = new String("000000");
		String solution3 = new String("000000");
		String solution4 = new String("000000");
		String solution5 = new String("000000");
		String solution6 = new String("000000");
		String solutionChild = new String("000000");
		
		Random random = new Random();
		
		for (int i = 0; i < random.nextInt(itemNum); i++) {
			bitFlip(random.nextInt(itemNum),solution1);
		}
		for (int i = 0; i < random.nextInt(itemNum); i++) {
			bitFlip(random.nextInt(itemNum),solution2);
		}
		for (int i = 0; i < random.nextInt(itemNum); i++) {
			bitFlip(random.nextInt(itemNum),solution3);
		}
		for (int i = 0; i < random.nextInt(itemNum); i++) {
			bitFlip(random.nextInt(itemNum),solution4);
		}
		for (int i = 0; i < random.nextInt(itemNum); i++) {
			bitFlip(random.nextInt(itemNum),solution5);
		}
		for (int i = 0; i < random.nextInt(itemNum); i++) {
			bitFlip(random.nextInt(itemNum),solution6);
		}
		
		List<String> solutionList = new ArrayList();
		solutionList.add(solution1);
		solutionList.add(solution2);
		solutionList.add(solution3);
		solutionList.add(solution4);
		solutionList.add(solution5);
		solutionList.add(solution6);
		solutionList.add(solutionChild);
		solutionList.add(solutionChild);
		solutionList.add(solutionChild);
		solutionList.add(solutionChild);
		solutionList.add(solutionChild);
		solutionList.add(solutionChild);
		
		return solutionList;
	}
	
}
