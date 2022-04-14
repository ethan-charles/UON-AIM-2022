package com.aim.LocalSearch.Impl;

import java.util.Random;

import com.aim.Problem;
import com.aim.LocalSearch.localSearchHeuristic;

public abstract class RandomMutationHC implements localSearchHeuristic {
	
	private Problem problem;
	private String currentSolution;
	
	public RandomMutationHC(Problem problem, String currentSolution) {
		
		this.problem = problem;
		this.currentSolution = currentSolution;
		
	}
	
	public String applyRandomMutationHC(String currentSolution) {
		
		String bestSolution = currentSolution;
		int bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < problem.getApplyTimes(Problem.IOM); i ++) {
			
			problem.bitFlip(random.nextInt(Problem.itemNum), bestSolution);
			int tmpEval = problem.getSolutionValue(bestSolution);
			
			if(tmpEval < bestEval) {
				
				bestEval = tmpEval;
				
			}else {
				
				problem.bitFlip(i, bestSolution);
				
			}
		}
		
		return bestSolution;
	}

}
