package com.aim.MultiMeme;

public interface MutationHeuristic {
	
	String applyRandomBitFlip(String solution);

	String applyExchangeBitFlip(String solution);
	
	String applyInversionBitFlip(String solution);

	String applyBoundaryBitFlip(String solution);

	String applyCircleBitFlip(String solution);
}
