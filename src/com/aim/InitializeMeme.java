package com.aim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aim.MultiMeme.CrossoverHeuristic;
import com.aim.MultiMeme.LocalSearchHeuristic;
import com.aim.MultiMeme.MutationHeuristic;

public class InitializeMeme {

	private final Problem problem;
	private CrossoverHeuristic crossover;
	private LocalSearchHeuristic localSearch;
	private MutationHeuristic mutation;
	
	private Random random;
	private int memeListNum = 5;
	private int memeNum = 5;
	
	public InitializeMeme(Problem problem, Random random) {
		
		this.problem = problem;
		this.random = random;
		
	}
	
	public List<String> initializeMeme() {
		
		List<String> memeList = new ArrayList();
		for(int i = 0; i < Problem.populationSize * 2; i ++) {
			StringBuffer current = new StringBuffer(memeListNum);
			for(int j = 0; j < memeListNum; j++) {
				current.setCharAt(j, String.valueOf(random.nextInt(memeNum)).charAt(0));
			}
			memeList.add(current.toString());
		}
		return memeList;
	}
	
	
	public List<String> applyCrossoverMeme(int child1, int child2, int meme0, int meme1, List<String> solutionList) {
		
		double crossoverPossibility = Problem.crossoverPossibility;
		switch(meme0) {
		case 0:
			crossoverPossibility = crossoverPossibility + 0.1;
			break;
		case 1:
			crossoverPossibility = crossoverPossibility - 0.1;
			break;
		case 2:
			crossoverPossibility = crossoverPossibility * 2;
			break;
		case 3: 
			crossoverPossibility = crossoverPossibility / 2;
			break;
		case 4:
			break;
		default:
				System.out.println("Crossover Problem!\n");
				crossoverPossibility = 0;
				break;
		}
		if(random.nextDouble() < crossoverPossibility) {
			switch(meme1) {
			case 0:
				solutionList = crossover.applyCrossoverHeuristic1PTX(child1, child2, solutionList);
				break;
			case 1:
				solutionList = crossover.applyCrossoverHeuristic2PTX(child1, child2, solutionList);
				break;
			case 2:
				solutionList = crossover.applyCrossoverHeuristicOX(child1, child2, solutionList);
				break;
			case 3:
				solutionList = crossover.applyCrossoverHeuristicSEC(child1, child2, solutionList);
				break;
			case 4:
				solutionList = crossover.applyCrossoverHeuristicUX(child1, child2, solutionList);
				break;
			default:
				System.out.println("Crossover Problem!\n");
				crossoverPossibility = 0;
				break;
			}
		}
		return solutionList;
	}
	
	public String applyMutationMeme(int meme2, int meme3, String solution) {
		
		double mutationPossibility = Problem.mutationPossibility;
		switch(meme2) {
		case 0:
			mutationPossibility = mutationPossibility + 0.1;
			break;
		case 1:
			mutationPossibility = mutationPossibility - 0.1;
			break;
		case 2:
			mutationPossibility = mutationPossibility * 2;
			break;
		case 3:
			mutationPossibility = mutationPossibility / 2;
			break;
		case 4:
			break;
		default:
			System.out.println("Mutation Problem!\n");
			mutationPossibility = 0;
			break;
		}
		if(random.nextDouble() < mutationPossibility) {
			switch(meme3) {
			case 0:
				solution = mutation.applyBoundaryBitFlip(solution);
				break;
			case 1:
				solution = mutation.applyCircleBitFlip(solution);
				break;
			case 2:
				solution = mutation.applyExchangeBitFlip(solution);
				break;
			case 3:
				solution = mutation.applyInversionBitFlip(solution);
				break;
			case 4:
				solution = mutation.applyRandomBitFlip(solution);
				break;
			default:
				System.out.println("Mutation Problem!\n");
				mutationPossibility = 0;
				break;
			}
		}
		return solution;
	}
	
	public String applyLocalSearchMeme(int meme4, String solution) {
		switch (meme4) {
		case 0:
			solution = localSearch.applyCombiningAlgorithmHC(solution);
			break;
		case 1:
			solution = localSearch.applyDavissBitHC(solution);
			break;
		case 2:
			solution = localSearch.applyNextDescentHC(solution);
			break;
		case 3:
			solution = localSearch.applyRandomMutationHC(solution);
			break;
		case 4:
			solution = localSearch.applySteepestDescentHC(solution);
			break;
		default:
			System.out.println("LocalSearch Problem!\n");
			break;
		}
		return solution;
	}
	
}
