package com.aim;

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
}
