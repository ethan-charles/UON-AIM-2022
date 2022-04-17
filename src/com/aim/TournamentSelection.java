package com.aim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TournamentSelection {
	
	private final Problem problem;
	private int POPULATION_SIZE;
	private List<String> solutionList =  new ArrayList();
	
	public TournamentSelection(Problem problem, int POPULATION_SIZE, List<String> solutionList) {
		
		this.problem = problem;
		this.POPULATION_SIZE = POPULATION_SIZE;
		this.solutionList = solutionList;
		
	}
	
	public int tournamentSelection(int tournamentSize) {
		
		int bestIndex = -1;
		double bestFitness = Double.MAX_VALUE;
		
		List<Integer> list = IntStream.range(0, POPULATION_SIZE).boxed().collect(Collectors.toList());
		Collections.shuffle(list);	//create list of random indices
		
		//select tournamentSize elements
		for(int i = 0; i < tournamentSize; i++) {
			int sol = list.get(i);
			double fitness = problem.getSolutionValue(solutionList.get(sol));
			
			if(fitness < bestFitness) {
				bestFitness = fitness;
				bestIndex = sol;
			}
		}
		
		return bestIndex;
	}
}
