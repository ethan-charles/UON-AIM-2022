package com.aim;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MemeticAlgorithm {
	
	private Instance instance;
	private ProblemInitialization problem;
	private MemeInitialization memeBuilder;
	
	private int populationSize, memeListNum, memeNum, trial, numberOf, itemNum;
	private int currentBestSolutionValue = 0, backupBestSolutionValue = 0;
	private int bestSolutionIndex = -1, worstSolutionIndex = -1;
	private double innovationRate;
	private double IOM, DOS;
	private String fileName;
	private List<String> solutionList;
	private List<String> memeList;

	
	public void multiMemeticAlgorithm(Instance instance, ProblemInitialization problem, MemeInitialization memeBuilder, int trial) throws IOException {
		
		this.problem = problem;
		this.instance = instance;
		this.memeBuilder = memeBuilder;
		
		this.IOM = problem.IOM;
		this.DOS = problem.DOS;
		this.numberOf = problem.numberOf;
		this.memeListNum = problem.memeListNum;
		this.memeNum = problem.memeNum;
		this.innovationRate = problem.innovationRate;
		
		this.fileName = instance.getFileName();
		
		this.populationSize = problem.populationSize;
		this.itemNum = instance.getItemNum();
		
		this.trial = trial;
		
		solutionList = problem.initialSolution();
		memeList = memeBuilder.initializeMeme();
		
		for(int i = 0; i < numberOf; i++) {
			solutionList = applyMemeticAlgorithm(solutionList,memeList,i);
		}
		finalPrint(solutionList);
	}
	
	
	public List<String> applyMemeticAlgorithm(List<String> solutionList, List<String> memeList, int applyTime) throws IOException {
		
		int parent1, parent2, child1, child2;
		for(int i = 0; i < populationSize / 2; i++) {
			parent1 = applyTournamentSelection(populationSize / 2, solutionList);
			parent2 = applyTournamentSelection(populationSize / 2, solutionList);
			while (parent1 == parent2) {
				parent2 = applyTournamentSelection(populationSize / 2, solutionList);
			}
			String child1solution = new String(solutionList.get(parent1));
			String child2solution = new String(solutionList.get(parent2));
			
			child1 = populationSize + i * 2;
			child2 = populationSize + i * 2 + 1;
			
			solutionList.set(child1, child1solution);
			solutionList.set(child2, child2solution);
			
			memeList = performMemeticInheritance(parent1, parent2, child1, child2, solutionList, memeList);
			//Crossover
			for(int m = 0; m < problem.getApplyTimes(IOM); m ++) {
				
				int meme0 = Character.getNumericValue(memeList.get(child1).charAt(0));
				int meme1 = Character.getNumericValue(memeList.get(child1).charAt(1));
				
				solutionList = memeBuilder.applyCrossoverMeme(child1, child2, meme0, meme1, solutionList);
			}
			
			//Mutation
			for(int m = 0; m < problem.getApplyTimes(IOM); m ++) {
				
				int meme2 = Character.getNumericValue(memeList.get(child1).charAt(2));
				int meme3 = Character.getNumericValue(memeList.get(child1).charAt(3));
			
				child1solution = memeBuilder.applyMutationMeme(meme2, meme3, child1solution);
				child2solution = memeBuilder.applyMutationMeme(meme2, meme3, child2solution);
				solutionList.set(child1, child1solution);
				solutionList.set(child2, child2solution);
			}
			
			//Localsearch
			for(int m = 0; m < problem.getApplyTimes(DOS); m++) {
				
				int meme4 = Character.getNumericValue(memeList.get(child1).charAt(4));
				
				child1solution = memeBuilder.applyLocalSearchMeme(meme4, child1solution);
				child2solution = memeBuilder.applyLocalSearchMeme(meme4, child2solution);
				solutionList.set(child1, child1solution);
				solutionList.set(child2, child2solution);
			}
			
			memeList = performMutationOfMemeplex(child1, memeList);
			memeList = performMutationOfMemeplex(child2, memeList);
		}
		solutionList = doReplacement(solutionList, memeList);
		
		printResult(solutionList, applyTime);
		
		return solutionList;
	}
	
	
	public int applyTournamentSelection(int tournamentSize, List<String> solutionList) {
		int bestIndex = -1;
		double bestFitness = Integer.MIN_VALUE;
		
		List<Integer> list = IntStream.range(0, populationSize).boxed().collect(Collectors.toList());
		Collections.shuffle(list);	//create list of random indices
		
		//select tournamentSize elements
		for(int i = 0; i < tournamentSize; i++) {
			int index = list.get(i);
			double fitness = problem.getSolutionValue(solutionList.get(index));
			
			if(fitness > bestFitness) {
				bestFitness = fitness;
				bestIndex = index;
			}
		}
		
		return bestIndex;
	}
	
	private List<String> performMemeticInheritance(int parent1, int parent2, int child1, int child2, List<String> solutionList, List<String> memeList) {
		double fparent1 = problem.getSolutionValue(solutionList.get(parent1));
		double fparent2 = problem.getSolutionValue(solutionList.get(parent2));
		Random random = new Random();
		String parent1Meme = new String(memeList.get(parent1));
		String parent2Meme = new String(memeList.get(parent2));
		
		if (fparent1 == fparent2) {
			if (random.nextDouble() < 0.5) {
				memeList.set(child1, parent1Meme);
				memeList.set(child2, parent1Meme);
			}
			else {
				memeList.set(child1, parent2Meme);
				memeList.set(child2, parent2Meme);
			}
		}
		else if (fparent1 > fparent2) {
			memeList.set(child1, parent1Meme);
			memeList.set(child2, parent1Meme);
		}
		else {
			memeList.set(child1, parent2Meme);
			memeList.set(child2, parent2Meme);
		}
		
		return memeList;
	}
	
	private List<String> performMutationOfMemeplex(int solutionIndex, List<String> memeList){
		
		Random random = new Random();
		
		for(int i = 0; i < memeListNum; i++) {
			if(random.nextDouble() < innovationRate) {
				int option = -1;
				do {
					option = random.nextInt(memeNum);
				}while(option == Character.getNumericValue(memeList.get(solutionIndex).charAt(i)));
			}
		}
		return memeList;
	}
	
	private List<String> doReplacement(List<String> solutionList, List<String> memeList){
		
		int bestSolutionIndex = problem.getBestSolutionIndex(solutionList);
		int worstSolutionIndex = problem.getWorstIndex(solutionList);
		String bestSolution = new String(solutionList.get(bestSolutionIndex));
		String bestMeme = new String(memeList.get(bestSolutionIndex));
		
		if(bestSolutionIndex >= populationSize) {
			for(int i = 0;i < populationSize; i++) {
				solutionList.set(i, solutionList.get(i + populationSize));
				memeList.set(i, memeList.get(i + populationSize));
			}
		}else {
			for(int i = 0; i < populationSize; i++) {
				if(worstSolutionIndex == i + populationSize) {
					solutionList.set(i, bestSolution);
					memeList.set(i, bestMeme);
					continue;
				}
				solutionList.set(i, solutionList.get(i + populationSize));
				memeList.set(i, memeList.get(i + populationSize));
			}
		}
		return solutionList;
		
	}

	public void finalPrint(List<String> solutionList) throws IOException {
		String resultPath = "source/out/" + fileName + "_Result.txt";
        File ResultFile = new File(resultPath);
        if(ResultFile.exists()) {
        	ResultFile.delete();
        	ResultFile.createNewFile();
        }
        StringBuffer message = new StringBuffer();
        message.append("Trial#" + trial + ": \n" + "Best Solution : " + currentBestSolutionValue + " \nThe solution is : "
        		+ solutionList.get(problem.getBestSolutionIndex(solutionList)) + "\n\n");
        System.out.print(message);
        
        FileWriter fw = new FileWriter(ResultFile,true);
        fw.write(message.toString());
        fw.flush();
        fw.close();
        
	}
	
	public void printResult(List<String> solutionList, int applyTime) throws IOException {
		
		String outputPath = "source/out/" + fileName + "_trial" + trial + "_output.txt";
        File outputFile = new File(outputPath);
        if(outputFile.exists()) {
        	outputFile.delete();
        	outputFile.createNewFile();
        }
        
        StringBuffer message = new StringBuffer();
        String bestSolution = solutionList.get(problem.getBestSolutionIndex(solutionList));
        String worstSolution = solutionList.get(problem.getWorstSolutionIndex(solutionList));
        message.append("Generations #" + applyTime + ": \n" + "Best Solution : " + 
        problem.getSolutionValue(bestSolution) + " The solution is : " + bestSolution);

        message.append("\nWorst Solution : " + problem.getSolutionValue(worstSolution) +
        		" The solution is : " + worstSolution + "\n\n");

        FileWriter fw = new FileWriter(outputFile,true);
        fw.write(message.toString());
        fw.flush();
        fw.close();    
	}
	

	

}
