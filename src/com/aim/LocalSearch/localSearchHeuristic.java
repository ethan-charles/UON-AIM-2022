package com.aim.LocalSearch;

import java.util.List;

public interface localSearchHeuristic {

	String applySteepestDescentHC(String currentSolution);
	
	String applyNextDescentHC(String currentSolution);
	
	String applyDavissBitHC(String currentSolution);
	
	String applyRandomMutationHC(String currentSolution);
	
}
