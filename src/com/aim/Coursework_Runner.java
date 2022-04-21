package com.aim;

import java.io.IOException;

public class Coursework_Runner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Instance read = new Instance();
		read.readTxt();
		
		ProblemInitialization problem = new ProblemInitialization(read);
		MemeInitialization meme = new MemeInitialization(read);
		
		MemeticAlgorithm memeticAlgorithm = new MemeticAlgorithm();
		for(int i = 1; i <= problem.trial; i++) {
			memeticAlgorithm.multiMemeticAlgorithm(read, problem, meme, i);
		}
	}

}
