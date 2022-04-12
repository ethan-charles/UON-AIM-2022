package com.aim;

public class Problem {
	
	private String solution; 
	static Instance instance = new Instance();
	static final int capacity = instance.ReadTxt().get(1),
			itemNum = instance.ReadTxt().get(0);

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
}
