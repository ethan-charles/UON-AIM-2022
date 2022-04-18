package com.aim;

import java.util.List;


public class MemeticAlgorithm {
	
	private final Problem problem;
	private TournamentSelection tournamentSelection;
	private InitializeMeme initializeMeme;
	private int populationSize;
	
	public MemeticAlgorithm(Problem problem) {
		
		this.problem = problem;
		this.populationSize = Problem.populationSize;
		this.tournamentSelection = tournamentSelection;

	}
	
	public List<String> applyMemeticAlgorithm(List<String> solutionList, List<String> memeList, int applyTime) {
		
		int parent1, parent2, child1, child2;
		for(int i = 0; i < populationSize / 2; i++) {
			parent1 = tournamentSelection.applyHeuristic(3, solutionList);
			parent2 = tournamentSelection.applyHeuristic(3, solutionList);
			while (parent1 == parent2) {
				parent2 = tournamentSelection.applyHeuristic(3, solutionList);
			}
			String child1solution = new String(solutionList.get(parent1));
			String child2solution = new String(solutionList.get(parent2));
			
			child1 = populationSize + i * 2;
			child2 = populationSize + i * 2 + 1;
			
			solutionList.set(child1, child1solution);
			solutionList.set(child2, child2solution);
			
			memeList = performMemeticInheritance(parent1, parent2, child1, child2, solutionList, memeList);
			//此处出现了重复的两个meme
			for(int m = 0; m < problem.getApplyTimes(Problem.IOM); m ++) {
				
				int meme0 = Character.getNumericValue(memeList.get(child1).charAt(0));
				int meme1 = Character.getNumericValue(memeList.get(child1).charAt(1));
				
				solutionList = initializeMeme.applyCrossoverMeme(child1, child2, meme0, meme1, solutionList);
			}
			for(int m = 0; m < problem.getApplyTimes(Problem.IOM); m ++) {
				
				int child1meme2 = Character.getNumericValue(memeList.get(child1).charAt(2));
				int child1meme3 = Character.getNumericValue(memeList.get(child1).charAt(3));
				int child2meme2 = Character.getNumericValue(memeList.get(child2).charAt(2));
				int child2meme3 = Character.getNumericValue(memeList.get(child2).charAt(3));
				
				child1solution = initializeMeme.applyMutationMeme(child1meme2, child1meme3, child1solution);
				child2solution = initializeMeme.applyMutationMeme(child2meme2, child2meme3, child2solution);
				solutionList.set(child1, child1solution);
				solutionList.set(child2, child2solution);
			}
			for(int m = 0; m < problem.getApplyTimes(Problem.DOS); m++) {
				
				int child1meme4 = Character.getNumericValue(memeList.get(child1).charAt(4));
				int child1meme5 = Character.getNumericValue(memeList.get(child1).charAt(5));
				int child2meme4 = Character.getNumericValue(memeList.get(child2).charAt(4));
				int child2meme5 = Character.getNumericValue(memeList.get(child2).charAt(5));
				
				child1solution = initializeMeme.applyMutationMeme(child1meme4, child1meme5, child1solution);
				child2solution = initializeMeme.applyMutationMeme(child2meme4, child2meme5, child2solution);
				solutionList.set(child1, child1solution);
				solutionList.set(child2, child2solution);
			}
			
			memeList = performMutationOfMemeplex(child1, memeList);
			memeList = performMutationOfMemeplex(child2, memeList);
		}
		
		solutionList = doReplacement(solutionList, memeList);
		
		printResult(solutionList, applyTime);
		
		return SolutionList;
	}
	
	
	
	private List<String> performMemeticInheritance(int parent1, int parent2, int child1, int child2, List<String> solutionList, List<String> memeList) {
		double fparent1 = problem.getSolutionValue((int[]) listListCurrent.get(parent1Index));
		double fparent2 = gettheResult((int[]) listListCurrent.get(parent2Index));
		Random rnd = new Random();
		if (perform1 == perform2) {
			if (rnd.nextDouble() < 0.5) {
				memeListCurrent.set(child1Index, copyList((int[]) memeListCurrent.get(parent1Index)));
				memeListCurrent.set(child2Index, copyList((int[]) memeListCurrent.get(parent1Index)));
			}
			else {
				memeListCurrent.set(child1Index, copyList((int[]) memeListCurrent.get(parent2Index)));
				memeListCurrent.set(child2Index, copyList((int[]) memeListCurrent.get(parent2Index)));
			}
		}
		else if (perform1 > perform2) {
			memeListCurrent.set(child1Index, copyList((int[]) memeListCurrent.get(parent1Index)));
			memeListCurrent.set(child2Index, copyList((int[]) memeListCurrent.get(parent1Index)));
		}
		else {
			memeListCurrent.set(child1Index, copyList((int[]) memeListCurrent.get(parent2Index)));
			memeListCurrent.set(child2Index, copyList((int[]) memeListCurrent.get(parent2Index)));
		}
		
		return memeListCurrent;
	}
	
	private List<String> performMutationOfMemeplex(int solutionIndex, List<String> memeList){
		
	}
	
	private List<String> doReplacement(List<String> solutionList, List<String> memeList){
		
	}
	
	private void printResult(List<String> solutionList, int applyTime) {
		
	}
	
	

}
