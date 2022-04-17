package com.aim;

import java.util.Arrays;
import java.util.List;

public class InitializeSolution {
	
	private final Problem problem;
	private int[] valueList;
	private int[] weightList;
	private int[] positionList;
	
	public InitializeSolution(Problem problem) {
		
		this.problem = problem;
		this.valueList = problem.getItemValueList();
		this.weightList = problem.getItemWeightList();
		this.positionList = new int[Problem.itemNum];

	}
	
	public List<String> applyInitialize(){
		
		
	}
	
	private List<String> largestProfit() {
		
		int totalWeight = 0;
		int i = 0, currentWeight = 0;
		sort(valueList, weightList);
		while(totalWeight <= Problem.capacity && i < Problem.itemNum) {
			
			if(weightList[i] < Problem.capacity - totalWeight) {
				totalWeight += weightList[i];
				positionList[i] = 1;
			}
			i ++;
		}
		for(i = 0; i < Problem.itemNum; i++) {
			if(positionList[i] == 1) {
				
			}
		}
		
		
	}
	
	private void sort(int[] array, int [] position) {
		
	    int tmp = 0;
	    for (int i = 0; i < Problem.itemNum - 1; i++)
	    {
	        for (int j = 1; j < Problem.itemNum - i; j++)
	        {
	            if (array[j] > array[j-1])    /* 从大到小排序，把较小的交换到后面来 */
	            {
	                tmp = array[j-1];
	                array[j-1] = array[j];
	                array[j] = tmp;
	                /* 记录位置 */
	                tmp = position[j-1];
	                position[j-1] = position[j];
	                position[j] = tmp;
	            }
	        }
	    }

	}

}
