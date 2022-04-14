package com.aim.LocalSearch.Impl;

import com.aim.Problem;
import com.aim.LocalSearch.localSearchHeuristic;

public abstract class NextDescentHC implements localSearchHeuristic {
	
	private Problem problem;
	private String currentSolution;
	
	public NextDescentHC(Problem problem, String currentSolution) {
		
		this.problem = problem;
		this.currentSolution = currentSolution;
		
	}
	
	public String applyNextDescentHC(String currentSolution) {
		
		String bestSolution = currentSolution;
		int bestEval = problem.getSolutionValue(bestSolution);
		
		for (int i = 0; i < Problem.itemNum; i ++) {
			
			problem.bitFlip(i, bestSolution);
			int tmpEval = problem.getSolutionValue(bestSolution);
			
			if(tmpEval < bestEval) {
				
				bestEval = tmpEval;
				
			} else {
				
				problem.bitFlip(i, bestSolution);
				
			}
		}
		
		return bestSolution;
	}

}
