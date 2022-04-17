package com.aim;

import java.util.Random;

public class BitMutation {
	
	private final Problem problem;
	private double MUTATION_RATE;

	public BitMutation(Problem problem) {
		
		this.problem = problem;
		
	}
	
	public String applyHeuristic(String solution) {
		
		Random random = new Random();

		for(int i = 0; i < problem.getApplyTimes(Problem.IOM); i++) {
			
			problem.bitFlip(random.nextInt(Problem.itemNum), solution);
			
		}

		return solution;
	}
	
}
