package com.aim.MultiMeme.Impl;

import java.util.Random;

import com.aim.Problem;
import com.aim.MultiMeme.MutationHeuristic;

public class MutationHeuristicImpl implements MutationHeuristic {

	private final Problem problem;
	private String currentSolution;
	private Random random;
	
	public MutationHeuristicImpl(Problem problem, String currentSolution, Random random) {
		
		this.problem = problem;
		this.currentSolution = currentSolution;
		this.random = random;
	}

	@Override
	public String applyRandomBitFlip(String solution) {
		
		problem.bitFlip(random.nextInt(Problem.itemNum), solution);
		return solution;
		
	}

	@Override
	public String applyExchangeBitFlip(String solution) {
		int bit1 = random.nextInt(Problem.itemNum);
		int bit2 = random.nextInt(Problem.itemNum);
		while(bit1 == bit2) {
			bit2 = random.nextInt(Problem.itemNum);
		}
		StringBuffer solutionBuffer = new StringBuffer(solution);
		char temp = solutionBuffer.charAt(bit1);
		solutionBuffer.setCharAt(bit1, solutionBuffer.charAt(bit2));
		solutionBuffer.setCharAt(bit2, temp);
		return solutionBuffer.toString();
	}

	@Override
	public String applyInversionBitFlip(String solution) {
		int start = random.nextInt(Problem.itemNum / 2);
		int end = random.nextInt(Problem.itemNum / 2) + Problem.itemNum / 2;
		StringBuffer solutionBuffer = new StringBuffer(solution);
		for(int i = 0; i < (end - start) / 2 + 1; i++) {
			char temp = solutionBuffer.charAt(start + i);
			solutionBuffer.setCharAt(start + i, solutionBuffer.charAt(end - i));
			solutionBuffer.setCharAt(end - i, temp);
		}
		return solutionBuffer.toString();
	}
	
	@Override
	public String applyBoundaryBitFlip(String solution) {
		
		int start = random.nextInt(Problem.itemNum / 2);
		int end = random.nextInt(Problem.itemNum / 2) + Problem.itemNum / 2;
		StringBuffer solutionBuffer = new StringBuffer(solution);
		for(int i = 0; i < Problem.itemNum - end + start; i++) {
			char temp = solutionBuffer.charAt(i);
			solutionBuffer.setCharAt(i, solutionBuffer.charAt(Problem.itemNum - i));
			solutionBuffer.setCharAt(Problem.itemNum - i, temp);
		}
		return solutionBuffer.toString();
	}

	@Override
	public String applyCircleBitFlip(String solution) {
		
		StringBuffer solutionBuffer = new StringBuffer(solution);
		char first = solutionBuffer.charAt(0);
		for(int i = 1; i < Problem.itemNum; i++) {
			solutionBuffer.setCharAt(i-1, solutionBuffer.charAt(i));
		}
		solutionBuffer.setCharAt(Problem.itemNum - 1, first);
		return solutionBuffer.toString();
		
	}
	

}
