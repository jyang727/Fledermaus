package com.juntest.algorithms.subsum;

import java.util.List;

import com.juntest.algorithms.util.RandomSeqNumberGenerator;

/**
 * Brutal force solution is O(n*n*n).
 *  
 * @author jyang
 *
 */

public class MaxSubSum {
	
	
	/**
	 * this is a O(n*n*n) solution
	 */
	public void getMaxSubSumBigON3(List<Integer> a){
		
		int size = a.size();
		int start = 0;
		int end = size-1;
		
		int maxSum = Integer.MIN_VALUE;
		
		for (int i=0; i<size; i++) {
			for (int j=i; j<size; j++) {
				
				int sum = 0;
				for (int k=i; k<=j; k++) {
				    sum+=a.get(k);
				}
				
				if (sum>maxSum){
				   	maxSum = sum;
				   	start = i;
				   	end = j;
				}
				    
			}
		}
	
		System.out.println("Start index = " + start + ", value =  " + a.get(start));
		System.out.println("End   index = " + end   + ", value =  " + a.get(end));
		System.out.println("Max sum =" + maxSum); 
	}	
	
	/**
	 * this is a O(n*n) solution
	 */
	public void getMaxSubSumBigON2(List<Integer> a){
		
		int size = a.size();
		int start = 0;
		int end = size-1;
		
		int maxSum = Integer.MIN_VALUE;
		for (int i=0; i<size; i++){
			
			int sum = 0;
			for (int j=i; j<size; j++){
				sum+=a.get(j);
				
				if (sum>maxSum){
					maxSum = sum;
					start = i;
					end = j;
				}
			}
		}
	
		System.out.println("Start index = " + start + ", value =  " + a.get(start));
		System.out.println("End   index = " + end   + ", value =  " + a.get(end));
		System.out.println("Max sum =" + maxSum); 
	}
	
	/**
	 * this is a O(n) solution
	 */
	public void getMaxSubSumBigON(List<Integer> a){
		
		int size = a.size();
		int start = 0;
		int end = size-1;
		
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		
		
		for (int i=0, j=0; j<size; j++){
			
			sum += a.get(j);
				
			if (sum > maxSum){
				maxSum = sum;
				start = i;
				end = j;
			}
			else if (sum <0){
				i = j+1;
				sum=0;
			}
		}
	
		System.out.println("Start index = " + start + ", value =  " + a.get(start));
		System.out.println("End   index = " + end   + ", value =  " + a.get(end));
		System.out.println("Max sum =" + maxSum); 
	}	
	
	public static void main(String[] args){
	
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(20);
		
		List<Integer> list = gen.getSequence();
		
		System.out.println(gen.toString());
		
		MaxSubSum sum = new MaxSubSum()	;
		
		sum.getMaxSubSumBigON3(list);
		
		sum.getMaxSubSumBigON2(list);
		
		sum.getMaxSubSumBigON(list);
	}
	
	
	
}
