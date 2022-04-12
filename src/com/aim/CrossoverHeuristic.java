package com.aim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossoverHeuristic {
	
	private Problem problem;
	
	public CrossoverHeuristic(Problem problem) {
		
		this.problem = problem;
		
	}
	
	public List<String> applyHeuristic(int parent1Index, int parent2Index,
			int child1Index, int child2Index, List<String> solutionList){
		
		StringBuffer child1buffer = new StringBuffer(solutionList.get(parent1Index));
		StringBuffer child2buffer = new StringBuffer(solutionList.get(parent2Index));
		
		int j = 0;
		Random random = new Random();
		
		for (int i = 0; i < problem.itemNum; i++) {
			if (random.nextDouble() < 0.5) {
				
		        char temp1 = child1buffer.charAt(i);
		        char temp2 = child2buffer.charAt(i);
		        child1buffer.setCharAt(i,temp2);
		        child2buffer.setCharAt(i,temp1);
		        
			}
			j++;
		}
		
		solutionList.set(child1Index, child1buffer.toString());
		solutionList.set(child2Index, child2buffer.toString());
		
		return solutionList;
		
	}

}
