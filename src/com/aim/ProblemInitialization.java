package com.aim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProblemInitialization {
	
	/**
	 * Initialize solution list
	 * @author Yichen Lu
	 */
	//Config Area
	public static final int trial = 5;
	public static final int numberOf = 100;
	public static final int bestSolution = 0;
	public static final int populationSize = 6; // ±ØÊÇÅ¼Êý
	public static final double IOM = 0.4, DOS = 0.6;
	public static final double mutationPossibility = 0.5,
			crossoverPossibility = 0.5,
			innovationRate = 0.5;
	public static final int memeListNum = 5, memeNum = 5;
	
	private String solution; 
	private double[] valueList;
	private double[] weightList;
	private int itemNum;
	private double capacity;
	
	/**
	 * Save all data read from file
	 * @param instance all data read from file
	 */
	public ProblemInitialization(Instance instance) {
		this.valueList = instance.getItemValueList();
		this.weightList = instance.getItemWeightList();
		this.itemNum = instance.getItemNum();
		this.capacity = instance.getCapacity();
	}
	
	
	/**
	 * Main function
	 */
	public List<String> initialSolution(){
		List<String> solutionList = new ArrayList();
		Random random = new Random();
		
		for(int i = 0; i < populationSize; i++) {
			String solution = new String("0").repeat(itemNum);
			for (int j = 0; j < itemNum; j++) {
				solution = bitFlip(random.nextInt(itemNum),solution);
			}
			solutionList.add(solution);
		}
		
		for(int i = 0; i < populationSize; i++) {
			String solution = new String("0").repeat(itemNum);
			solutionList.add(solution);
		}
		return solutionList;
	}
	
	
	/**
	 * Get solution value
	 * @param solution solutions from solution list
	 */
	public double getSolutionValue(String solution) {
		double totalvalue = 0, totalweight = 0;
		int choice = 0;
		
		for (int i = 0; i < itemNum; i++) {
			choice = Character.getNumericValue(solution.charAt(i));
			
			if (choice == 1) {
				totalvalue += valueList[i]; 
				totalweight += weightList[i];
			}
		}
		if(totalweight > capacity) {
			return capacity - totalweight;
		}else {
			return totalvalue;
		}
	}
	
	
	/**
	 * Get best solution in solution list
	 * @param solutionList solution list
	 */
	public int getBestSolutionIndex(List<String> solutionList) {
		int bestSolution = -1;
        double bestValue = Integer.MIN_VALUE;
        
        for(int i = 0; i < populationSize * 2; i++) {
        	double temp = getSolutionValue(solutionList.get(i));
        	if(temp > bestValue) {
        		bestValue = temp;
        		bestSolution = i;
        	}
        }
        return bestSolution;
	}
	
	
	/**
	 * Get worst solution which is workable
	 * @param solutionList solution list
	 */
	public int getWorstSolutionIndex(List<String> solutionList) {
        int worstSolution = -1;
        double worstValue = Integer.MAX_VALUE;
        
        for(int i = 0; i < populationSize * 2; i++) {
        	double temp = getSolutionValue(solutionList.get(i));
        	if(temp < worstValue && temp > 0) {
        		worstValue = temp;
        		worstSolution = i;
        	}
        }
        return worstSolution;
	}
	
	
	/**
	 * Get worst solution from offspring
	 * @param solutionList solution list
	 */
	public int getWorstIndex(List<String> solutionList) {
		int worstSolution = -1;
        double worstValue = Integer.MAX_VALUE;
        
        for(int i = 0; i < populationSize * 2; i++) {
        	double temp = getSolutionValue(solutionList.get(i));
        	if(temp < worstValue && i >= populationSize) {
        		worstValue = temp;
        		worstSolution = i;
        	}
        }
        return worstSolution;
	}
	
	
	/**
	 * Translate IOM and DOS to the integer apply times
	 * @param IOMorDOS IOM or DOS
	 */
	public int getApplyTimes(double IOMorDOS) {
		int applyTimes = 0;
		
		if (IOMorDOS >= 0 && IOMorDOS <= 1) {
			applyTimes = (int) (IOMorDOS / 0.2) + 1;
		}
		return applyTimes;
		
		// Origin function
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
	
	
	/**
	 * flip bits in solution
	 * @param position index in solution
	 * @param solution solution
	 */
	public String bitFlip(int position, String solution) {
		StringBuffer solutionBuffer = new StringBuffer(solution);

		if (solutionBuffer.charAt(position) == '0') {
			solutionBuffer.setCharAt(position, '1');
		}else {
			solutionBuffer.setCharAt(position, '0');
		}
		return solutionBuffer.toString();
	}
	
}
