package com.aim.LocalSearch;

import java.util.List;

public interface localSearchHeuristic {

	String applySteepestDescentHC(List<String> solutionList);
	
	String applyDavissBitHC(List<String> solutionList);
}
