package com.juntest.mitbbs;

import java.util.List;

import com.juntest.algorithms.util.RandomSeqNumberGenerator;

public class Q22_MinMaxFinder {
	
	private int min, max;
	
	public void findMinMax(Integer[] a){
		
		if (a.length==0){
			return;
		}
		
		if (a.length==1){
			min = a[0];
			max= a[0];
			return;
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		int tmin, tmax;
		
		// need to handle special case of having odd number of elements in the array.
		for (int i=0; i<a.length; i+=2){
			
			boolean b =  a[i] >= a[i+1];
			
			if (b){
				tmax = a[i];
				tmin = a[i+1];
			}
			else{
				tmin = a[i];
				tmax = a[i+1];				
			}
			
			if (tmin<min){
				min = tmin;
			}
			
			if (tmax > max){
				max = tmax;
			}			
		}
		
		System.out.println("min=" + min + " max=" + max);
	}
	
	public static void main(String[] args){
		
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(20);
		
		List<Integer> list = gen.getSequence();		
		System.out.println(gen.toString());
		
		Integer[] a = new Integer[list.size()];
		list.toArray(a);
		
		Q22_MinMaxFinder q22 = new Q22_MinMaxFinder();
		
		q22.findMinMax(a);
	}
	
}
