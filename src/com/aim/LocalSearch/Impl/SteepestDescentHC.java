package com.aim.LocalSearch.Impl;

import java.util.List;

import com.aim.Problem;
import com.aim.LocalSearch.localSearchHeuristic;

public abstract class SteepestDescentHC implements localSearchHeuristic {
	
	private Problem problem;
	private String currentSolution;
	
	public SteepestDescentHC(Problem problem) {
		
		this.problem = problem;
		this.currentSolution = currentSolution;
		
	}
	
	
	public String applySteepestDescentHC(String currentSolution) {
		
		String bestSolution = currentSolution;
		int bestEval = problem.getSolutionValue(bestSolution);
		Boolean improved = false;
		int bestIndex = 0;
		
//		// select a random bit in the solution

		for(int j = 0; j < Problem.itemNum; j++) {
			// flip the bit
			problem.bitFlip(j,currentSolution);
			int tempEval = problem.getSolutionValue(currentSolution);
			
			if(tempEval < bestEval) {
				
				bestIndex = j;
				bestEval = tempEval;
				improved = true;
				
			}
			
			problem.bitFlip(j,currentSolution);
			
		}
		
		if(improved) {
			
			problem.bitFlip(bestIndex,currentSolution);
			
		}
		
		return currentSolution;
	}


}
