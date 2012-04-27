package com.juntest.algorithms.dp;

/**
 * There are 3 ways you can climb stairs:
 * 
 * 1, 2, or 3 steps at a time.
 * 
 * If you need to climb n stairs, how many ways you can do that? 
 * 
 * Classic DP problem
 * 
 * @author jyang
 *
 */

public class Stairs {

	/**
	 * 
	 * @param steps total steps of the stair
	 * @param paces different paces you can use to climb the stairs
	 * @return the number of different ways you can climb the stairs 
	 */
	public long maxCount(int steps, int[] paces) {
		
		// this array stores the count of different ways of climbing for EACH number of steps in the stairs
		// use 1 based array for convenience
		long[] counts = new long[steps+1];
		
		counts[0] = 1;
		
		for (int i=1; i<=steps; i++){
			
			counts[i] = 0;
			
			for (int j=0; j<paces.length; j++){
				
				if (i>=paces[j]){
					counts[i] += counts[i-paces[j]];
				}
			}
		}
		
		return counts[steps];
	}
	
	public static void main(String[] args){
		
		int steps = 20;
		int[] paces = new int[]{1, 2, 3};
		
		Stairs s = new Stairs();
		long t = s.maxCount(steps, paces);
		
		System.out.println("There are totally " + t + " ways climbing " + steps + " steps in the stairs");		
	}
	
}
