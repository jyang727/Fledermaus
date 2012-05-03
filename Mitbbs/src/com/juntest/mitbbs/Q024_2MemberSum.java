package com.juntest.mitbbs;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.juntest.algorithms.util.RandomSeqNumberGenerator;

public class Q024_2MemberSum {
	
	public boolean checkSubSum(Integer[] a, int target){
		
		Arrays.sort(a);
		
		if (target < (a[0] + a[1])) {
			return false;
		}
			
		if (target > (a[a.length-1] + a[a.length-2])){
			return false;
		}
			
		for (int i=0; i<a.length; i++){
			for (int j=a.length -1; j>= 0; j--){
				
				if (a[i] + a[j] > target){
					continue;
				}
				else if (a[i] + a[j] == target){
					System.out.println("found: i=" + a[i] + ", j=" + a[j]);		
					return true;
				}
				else {
					break;
				}
			}
		}
		
		return false;
	}

	
	public static void main(String[] args){
		
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(20);
		
		List<Integer> list = gen.getSequence();		
		System.out.println(gen.toString());
		
		Integer[] a = new Integer[list.size()];
		list.toArray(a);
		
		Random r = new Random();
		
		int i = r.nextInt(20);
		int j = i;
		
		while (j==i) {
			j = r.nextInt(20);
		}
		
		System.out.println("i=" + a[i] + ", j=" + a[j] + " target=" + (a[i] + a[j]));		
		
		Q024_2MemberSum q24 = new Q024_2MemberSum();
		
		boolean b = q24.checkSubSum(a, a[i] + a[j]);
		
		System.out.println(b);
	}
	
}
