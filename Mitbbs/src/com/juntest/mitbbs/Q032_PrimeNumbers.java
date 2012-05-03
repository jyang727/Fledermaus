package com.juntest.mitbbs;

/**
 * 
 * Sieve of Eratosthenes: find all the prime numbers between 0 and n. 
 * 
 * @author jyang
 *
 */
public class Q032_PrimeNumbers {
	
	private Integer[] Sieve = null;
	private int n;
	
	public Q032_PrimeNumbers(int n){
		this.n = n;
		Sieve = new Integer[n+1];
		
		for (int i=0; i<n+1; i++){
			Sieve[i]=1;
		}
	}
	
	public void getPrimeNumbers(){
		
		int i = 1;
		int j;
		
		while (++i<n){
			while (i<n+1 && Sieve[i]==0){
				i++;
			}
			
			j = i;
			
			while (true){
				j+=i;
				if (j>n){
					break;
				}
					
				Sieve[j] = 0;
			}
		}
		
		int count = 0;
		for (int k = 2; k<n+1; k++){
			if (Sieve[k]==1){
				count++;
				if (count%20==0){
					System.out.println();
				}
				else {
					System.out.print(k + " ");
				}				
			}			
		}		
	}
	
	public static void main(String[] args){
		
		Q032_PrimeNumbers q32 = new Q032_PrimeNumbers(10000);
		
		q32.getPrimeNumbers();
		
	}
}
