package com.juntest.algorithms.dp;

public class Fibonacci {

	// complexity is exponential (2^n)
	public long getFibRecursive(int n){
		
		if (n<=1){
			return n;
		}
		
		return getFibRecursive(n-1) + getFibRecursive(n-2);
	}
	
	// O(n)
	public long getFibIteration(int n){
		
		long f1 = 0;
		long f2 = 1;
		
		long f = 0;
		
		for (int i=1; i<n; i++){
			f = f1 + f2;
			f1 = f2;
			f2 = f;
		}
		
		return f;
	}
	
	// O(n)
	public long getFibDP(int n){
		
		long[] results = new long[n+1];
		
		results[0]=0;
		results[1]=1;
		
		
		for (int i=2; i<=n; i++){
			results[i] = results[i-1] + results[i-2];			
		}
		
		return results[n];
	}
	
	public static void main(String[] args){
		
		int n = 8;
		
		Fibonacci f = new Fibonacci();
		
		long f1 = f.getFibRecursive(n);
		
		long f2 = f.getFibIteration(n);
		
		long f3 = f.getFibDP(n);
		
		System.out.println("Fibonacci number of " + n + " th. is " + f1);
		System.out.println("Fibonacci number of " + n + " th. is " + f2);
		System.out.println("Fibonacci number of " + n + " th. is " + f3);
		
	}
}
