package com.aim.MultiMeme.Impl;

import java.util.List;
import java.util.Random;

import com.aim.Problem;
import com.aim.MultiMeme.CrossoverHeuristic;

public class CrossoverHeuristicImpl implements CrossoverHeuristic {
	
	private Random random;
	private final Problem problem;
	
	public CrossoverHeuristicImpl(Random random, Problem problem) {
		this.problem = problem;
		this.random = random;
	}

	@Override
	public List<String> applyCrossoverHeuristic1PTX(int child1Index, int child2Index, List<String> solutionList) {
		
		StringBuffer child1 = new StringBuffer(solutionList.get(child1Index));
		StringBuffer child2 = new StringBuffer(solutionList.get(child2Index));
		int start = random.nextInt(Problem.itemNum);
		
		for(int i = start; i < Problem.itemNum; i++) {
			char temp1 = child1.charAt(i);
	        char temp2 = child2.charAt(i);
	        child1.setCharAt(i,temp2);
	        child2.setCharAt(i,temp1);
		}
		
		solutionList.set(child1Index, child1.toString());
		solutionList.set(child2Index, child2.toString());
		return solutionList;
		
	}

	@Override
	public List<String> applyCrossoverHeuristic2PTX(int child1Index, int child2Index, List<String> solutionList) {

		StringBuffer child1 = new StringBuffer(solutionList.get(child1Index));
		StringBuffer child2 = new StringBuffer(solutionList.get(child2Index));
		int start = random.nextInt(Problem.itemNum / 2);
		int end = random.nextInt(Problem.itemNum / 2) + Problem.itemNum / 2;
		
		for(int i = start; i < end; i ++) {
			char temp1 = child1.charAt(i);
	        char temp2 = child2.charAt(i);
	        child1.setCharAt(i,temp2);
	        child2.setCharAt(i,temp1);
		}
		
		solutionList.set(child1Index, child1.toString());
		solutionList.set(child2Index, child2.toString());
		return solutionList;
	}

	@Override
	public List<String> applyCrossoverHeuristicUX(int child1Index, int child2Index, List<String> solutionList) {

		StringBuffer child1 = new StringBuffer(solutionList.get(child1Index));
		StringBuffer child2 = new StringBuffer(solutionList.get(child2Index));
		
		for(int i = 0; i < Problem.itemNum; i ++) {
			if(random.nextDouble() < 0.5) {
				char temp1 = child1.charAt(i);
				char temp2 = child2.charAt(i);
				child1.setCharAt(i,temp2);
				child2.setCharAt(i,temp1);
			}
		}
		
		solutionList.set(child1Index, child1.toString());
		solutionList.set(child2Index, child2.toString());
		return solutionList;
	}

	@Override
	//Order Crossover
	public List<String> applyCrossoverHeuristicOX(int child1Index, int child2Index, List<String> solutionList) {
		
		StringBuffer parent1 = new StringBuffer(solutionList.get(child1Index));
		StringBuffer parent2 = new StringBuffer(solutionList.get(child2Index));
		StringBuffer child1 = new StringBuffer(parent1);
		StringBuffer child2 = new StringBuffer(parent2);
		int start = random.nextInt(Problem.itemNum / 2);
		int end = random.nextInt(Problem.itemNum / 2) + Problem.itemNum / 2;
		
		//Take out each part that needs to crossover
		StringBuffer commonPart1 = new StringBuffer(parent1.substring(start, end)); 
		StringBuffer commonPart2 = new StringBuffer(parent2.substring(start, end));
		
		int m = 0, n = 0;
		for(int i = 0; i < Problem.itemNum; i++) {
			
			if(i >= start && i <= end) {
				//Skip the common part
				continue;
			}
			
			while(m < Problem.itemNum - 1 && commonPart1.indexOf(parent2.substring(m)) != -1) {
				//Search the position of the gene selected in the first step in the parent2
				m ++;
			}
			//The remaining genes of parent2 are placed sequentially in child1
			child1.setCharAt(i, parent2.charAt(m));
			m ++;
			
			while(n < Problem.itemNum - 1 && commonPart2.indexOf(parent1.substring(n)) != -1) {
				//Search the position of the gene selected in the first step in the parent1
				n ++;
			}
			//The remaining genes of parent2 are placed sequentially in child1
			child2.setCharAt(i, parent1.charAt(n));
			n ++;
		}
		
		solutionList.set(child1Index, child1.toString());
		solutionList.set(child2Index, child2.toString());
		return solutionList;
	}

	@Override
	//Subtour Exchange Crossover
	public List<String> applyCrossoverHeuristicSEC(int child1Index, int child2Index, List<String> solutionList) {

		StringBuffer parent1 = new StringBuffer(solutionList.get(child1Index));
		StringBuffer parent2 = new StringBuffer(solutionList.get(child2Index));
		StringBuffer child1 = new StringBuffer(parent1);
		StringBuffer child2 = new StringBuffer(parent2);
		
		int start = random.nextInt(Problem.itemNum / 2);
		int end = random.nextInt(Problem.itemNum / 2) + Problem.itemNum / 2;
		StringBuffer child1Part = new StringBuffer(parent1.substring(start, end));
		int[] child2PartIndex = new int[child1Part.length()];

		//Search the position of chosen genes in the parent2
		for(int j = 0; j < child1Part.length(); j++) {
			for(int i = 0; i < Problem.itemNum; i++) {
				if(child1Part.charAt(j) == parent2.charAt(i)) {
					child2PartIndex[j] = i;
				}
			}
		}
		
		//Exchange of the positions of genes in the chromosomes of the two parents
		for(int i = 0; i < child1Part.length(); i++) {
			char temp1 = child1.charAt(i+start);
			char temp2 = child2.charAt(child2PartIndex[i]);
			child1.setCharAt(child2PartIndex[i],temp2);
			child2.setCharAt(i+start,temp1);
		}
		
		solutionList.set(child1Index, child1.toString());
		solutionList.set(child2Index, child2.toString());
		return solutionList;
	}

	
}
