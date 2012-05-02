package com.juntest.mitbbs;

import java.util.List;

import com.juntest.algorithms.util.RandomSeqNumberGenerator;

public class Q09_Partition {
	
	/**
	 * Partition the list by the value v so that all the values smaller than than v are left to
	 * all the values that are greater than v.
	 * 
	 * Note that the list may not contain v.
	 * 
	 * @param a
	 * @param v
	 */
	public void partition(Integer[] a, int v){
		
		int n = a.length;				
		int p1 = 0;
		int p2 = n-1;
		
		while (true){
			
			while (a[p1]<v){
				p1++;
				if (p1==n){
					break;
				}
			}
			
			while (a[p2]>v){
				p2--;
				if (p2==0){
					break;
				}				
			}
			
			if (p1>=p2){
				break;
			}
			
			swap(a, p1, p2);
		}
		
		printArray(a);
	}
	
	private void swap(Integer[] a, int i, int j){
		Integer temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public void printArray(Integer[] a){
		System.out.print("[");
		for (int i=0; i<a.length; i++){
			System.out.print(a[i] + ", ");
		}
		System.out.println("]");
	}
	
	public static void main(String[] args){
		
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(20);
		
		List<Integer> list = gen.getSequence();		
		System.out.println(gen.toString());
		
		Integer[] a = new Integer[list.size()];
		list.toArray(a);
		
		Q09_Partition q9 = new Q09_Partition();
		
		q9.partition(a, 0);
	}
	

}
