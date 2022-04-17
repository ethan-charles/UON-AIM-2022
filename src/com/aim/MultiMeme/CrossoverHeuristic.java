package com.aim.MultiMeme;

import java.util.List;

public interface CrossoverHeuristic {

	List<String> applyCrossoverHeuristic1PTX(int child1Index, int child2Index, List<String> solutionList);
	
	List<String> applyCrossoverHeuristic2PTX(int child1Index, int child2Index, List<String> solutionList);
	
	List<String> applyCrossoverHeuristicUX(int child1Index, int child2Index, List<String> solutionList);
	
	List<String> applyCrossoverHeuristicOX(int child1Index, int child2Index, List<String> solutionList);
	
	List<String> applyCrossoverHeuristicSEC(int child1Index, int child2Index, List<String> solutionList);
	
}
