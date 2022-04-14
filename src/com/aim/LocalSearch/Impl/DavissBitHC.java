package com.aim.LocalSearch.Impl;

import java.util.List;
import java.util.Random;

import com.aim.Problem;
import com.aim.LocalSearch.localSearchHeuristic;

public abstract class DavissBitHC implements localSearchHeuristic {
	
	private Problem problem;
	private String currentSolution;
	
	public DavissBitHC(Problem problem, String currentSolution) {
		
		this.problem = problem;
		this.currentSolution = currentSolution;
		
	}

	public String applyDavissBitHC(String currentSolution) {
		
		String bestSolution = currentSolution;
		int bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < Problem.itemNum; i ++) {
			
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
