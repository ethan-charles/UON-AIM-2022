package com.aim.MultiMeme;

import java.util.Random;

import com.aim.ProblemInitialization;
import com.aim.Instance;

public class MutationHeuristicImpl {

	private ProblemInitialization problem;
	
	private int itemNum;
	
	public MutationHeuristicImpl(Instance instance) {
		this.itemNum = instance.getItemNum();
		this.problem = new ProblemInitialization(instance);
	}


	public String applyRandomBitFlip(String solution) {
		Random random = new Random();
		
		solution = problem.bitFlip(random.nextInt(itemNum), solution);
		return solution;
		
	}


	public String applyExchangeBitFlip(String solution) {
		Random random = new Random();
		int bit1 = random.nextInt(itemNum);
		int bit2 = random.nextInt(itemNum);
		
		while(bit1 == bit2) {
			bit2 = random.nextInt(itemNum);
		}
		StringBuffer solutionBuffer = new StringBuffer(solution);
		char temp = solutionBuffer.charAt(bit1);
		solutionBuffer.setCharAt(bit1, solutionBuffer.charAt(bit2));
		solutionBuffer.setCharAt(bit2, temp);
		return solutionBuffer.toString();
	}
	

	public String applyInversionBitFlip(String solution) {
		Random random = new Random();
		int start = random.nextInt(itemNum / 2);
		int end = random.nextInt(itemNum / 2) + itemNum / 2;
		StringBuffer solutionBuffer = new StringBuffer(solution);
		
		for(int i = 0; i < (end - start) / 2 + 1; i++) {
			char temp = solutionBuffer.charAt(start + i);
			solutionBuffer.setCharAt(start + i, solutionBuffer.charAt(end - i));
			solutionBuffer.setCharAt(end - i, temp);
		}
		return solutionBuffer.toString();
	}
	
	
	public String applyBoundaryBitFlip(String solution) {
		Random random = new Random();
		int mid = random.nextInt(itemNum - 1);
		
		StringBuffer solutionBuffer = new StringBuffer(solution);
		for(int i = 0; i < itemNum - mid; i++) {
			char temp = solutionBuffer.charAt(i);
			solutionBuffer.setCharAt(i, solutionBuffer.charAt(itemNum - i - 1));
			solutionBuffer.setCharAt(itemNum - i - 1, temp);
		}
		return solutionBuffer.toString();
	}
	

	public String applyCircleBitFlip(String solution) {
		StringBuffer solutionBuffer = new StringBuffer(solution);
		char first = solutionBuffer.charAt(0);
		
		for(int i = 1; i < itemNum; i++) {
			solutionBuffer.setCharAt(i-1, solutionBuffer.charAt(i));
		}
		solutionBuffer.setCharAt(itemNum - 1, first);
		return solutionBuffer.toString();
	}
	
}
