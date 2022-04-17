package com.aim.MultiMeme.Impl;

import java.util.Random;

import com.aim.Problem;
import com.aim.MultiMeme.LocalSearchHeuristic;

public class LocalSearchHeuristicImpl implements LocalSearchHeuristic {
	
	private final Problem problem;
	private String currentSolution;
	
	public LocalSearchHeuristicImpl(Problem problem, String currentSolution) {
		
		this.problem = problem;
		this.currentSolution = currentSolution;
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public String applyCombiningAlgorithmHC(String currentSolution) {
		
		String bestSolution = currentSolution;
		int bestEval = problem.getSolutionValue(bestSolution);
		Random random = new Random();
		
		for (int i = 0; i < problem.getApplyTimes(Problem.IOM); i ++) {
			
			problem.bitFlip(random.nextInt(Problem.itemNum), bestSolution);
			int tmpEval = problem.getSolutionValue(bestSolution);
			
			if(tmpEval < bestEval) {
				
				bestEval = tmpEval;
				
			}else {
				
				applySteepestDescentHC(bestSolution);
				
			}
		}
		
		return bestSolution;
	}
	
}
