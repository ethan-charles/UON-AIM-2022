package com.aim.MultiMeme;

public interface LocalSearchHeuristic {

	String applySteepestDescentHC(String currentSolution);
	
	String applyNextDescentHC(String currentSolution);
	
	String applyDavissBitHC(String currentSolution);
	
	String applyRandomMutationHC(String currentSolution);
	
	String applyCombiningAlgorithmHC(String currentSolution);
	
}
