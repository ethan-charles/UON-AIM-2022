package com.aim.MultiMeme;

import java.util.Random;

import com.aim.ProblemInitialization;
import com.aim.Instance;

public class LocalSearchHeuristicImpl {
	
	private ProblemInitialization problem;
	
	private String currentSolution;
	private int itemNum;
	
	public LocalSearchHeuristicImpl(Instance instance) {
		this.itemNum = instance.getItemNum();
		this.problem = new ProblemInitialization(instance);
	}
	
	
	public String applySteepestDescentHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Boolean improved = false;
		int bestIndex = 0;
		
//		// select a random bit in the solution
		for(int i = 0; i < itemNum; i++) {
			// flip the bit
			currentSolution = problem.bitFlip(i,currentSolution);
			double tempEval = problem.getSolutionValue(currentSolution);
			double delta = tempEval - bestEval;
			
			if(delta > 0) {
				bestIndex = i;
				bestEval = tempEval;
				improved = true;
			}
			currentSolution = problem.bitFlip(i,currentSolution);
		}
		
		if(improved) {
			currentSolution = problem.bitFlip(bestIndex,currentSolution);
		}
		return currentSolution;
	}
	

	public String applyNextDescentHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		
		for (int i = 0; i < itemNum; i ++) {
			bestSolution = problem.bitFlip(i, bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			double delta = tempEval - bestEval;
			
			if(delta > 0) {
				bestEval = tempEval;
			} else {
				bestSolution = problem.bitFlip(i, bestSolution);
			}
		}
		return bestSolution;
	}
	

	public String applyDavissBitHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < itemNum; i ++) {
			bestSolution = problem.bitFlip(random.nextInt(itemNum), bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			double delta = tempEval - bestEval;
			
			if(delta > 0) {
				bestEval = tempEval;
			}else {
				bestSolution = problem.bitFlip(i, bestSolution);
			}
		}
		return bestSolution;
	}


	public String applyRandomMutationHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < random.nextInt(itemNum); i ++) {
			bestSolution = problem.bitFlip(random.nextInt(itemNum), bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			double delta = tempEval - bestEval;
			
			if(delta > 0) {
				bestEval = tempEval;
			}else {
				bestSolution = problem.bitFlip(i, bestSolution);
			}
		}
		return bestSolution;
	}


	public String applyCombiningAlgorithmHC(String currentSolution) {
		String bestSolution = currentSolution;
		double bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < itemNum; i ++) {
			bestSolution = problem.bitFlip(random.nextInt(itemNum), bestSolution);
			double tempEval = problem.getSolutionValue(bestSolution);
			double delta = tempEval - bestEval;
			
			if(delta > 0) {	
				bestEval = tempEval;
			}else {
				applySteepestDescentHC(bestSolution);
			}
		}
		return bestSolution;
	}
}
