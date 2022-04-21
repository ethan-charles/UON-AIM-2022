package com.aim.MultiMeme;

import java.util.Random;

import com.aim.ProblemInitialization;
import com.aim.Instance;

public class LocalSearchHeuristicImpl {
	
	/**
	 * Achieve local search
	 * @author Yichen Lu
	 */
	private ProblemInitialization problem;
	
	private String currentSolution;
	private int itemNum;
	private double delta;
	
	
	/**
	 * Save all data read from file
	 * @param instance all data read from file
	 */
	public LocalSearchHeuristicImpl(Instance instance) {
		this.itemNum = instance.getItemNum();
		this.problem = new ProblemInitialization(instance);
	}
	
	/**
	 * STEEPEST DESCENT HILL CLIMBING
	 * @param currentSolution the input solution
	 */
	public String applySteepestDescentHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Boolean improved = false;
		int bestIndex = 0;
		
		// select a random bit in the solution
		for(int i = 0; i < itemNum; i++) {
			// flip the bit
			bestSolution = problem.bitFlip(i,bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			delta = tempEval - bestEval;
			if(delta > 0) {
				bestIndex = i;
				bestEval = tempEval;
				improved = true;
			}
			bestSolution = problem.bitFlip(i,bestSolution);
		}
		
		if(improved) {
			bestSolution = problem.bitFlip(bestIndex,bestSolution);
		}
		return bestSolution;
	}
	

	/**
	 * NEXT DESCENT HILL CLIMBING
	 * @param currentSolution the input solution
	 */
	public String applyNextDescentHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		
		for (int i = 0; i < itemNum; i ++) {
			bestSolution = problem.bitFlip(i, bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			delta = tempEval - bestEval;
			
			if(delta > 0) {
				bestEval = tempEval;
			} else {
				bestSolution = problem.bitFlip(i, bestSolution);
			}
		}
		return bestSolution;
	}
	

	/**
	 * DAVISS BIT HILL CLIMBING
	 * @param currentSolution the input solution
	 */
	public String applyDavissBitHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < itemNum; i ++) {
			bestSolution = problem.bitFlip(random.nextInt(itemNum), bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			delta = tempEval - bestEval;
			
			if(delta > 0) {
				bestEval = tempEval;
			}else {
				bestSolution = problem.bitFlip(i, bestSolution);
			}
		}
		return bestSolution;
	}


	/**
	 * RANDOM MUTATION HILL CLIMBING
	 * @param currentSolution the input solution
	 */
	public String applyRandomMutationHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < random.nextInt(itemNum); i ++) {
			bestSolution = problem.bitFlip(random.nextInt(itemNum), bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			delta = tempEval - bestEval;
			
			if(delta > 0) {
				bestEval = tempEval;
			}else {
				bestSolution = problem.bitFlip(i, bestSolution);
			}
		}
		return bestSolution;
	}


	/**
	 * Combining Random mutation with steepest hill climbing
	 * @param currentSolution the input solution
	 */
	public String applyCombiningAlgorithmHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < itemNum; i ++) {
			bestSolution = problem.bitFlip(random.nextInt(itemNum), bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			delta = tempEval - bestEval;
			
			if(delta > 0) {	
				bestEval = tempEval;
			}else {
				applySteepestDescentHC(bestSolution);
			}
		}
		return bestSolution;
	}
}
