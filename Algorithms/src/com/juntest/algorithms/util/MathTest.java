package com.juntest.algorithms.util;

public class MathTest {

	public static double sqrt(double c){

		if (c<0){
			return Double.NaN;
		}
		
		double err = 1e-15;
		
		double t;
		
		t = c;
		
		while ((t-c/t)>err){
			t = (t+c/t)/2;
		}
		
		return t;
	}
	
	public static int gcd(int a, int b){
		
		if (b==0){
			return a;
		}
		
		int r = a%b;
		
		return gcd(b, r); 				
	}
	
	public static boolean isPrime(int n){
		
		for (int i=2; i*i < n; i++){
			if (n%i==0){
				return false;
			}
		}
		
		return true;
	}
	
	
	public static int add_no_arithm(int a, int b) {
		
		if (b == 0) return a;
		int sum = a ^ b; // add without carrying
		int carry = (a & b) >> 1; // carry, but don’t add
		
		return add_no_arithm(sum, carry); // recurse
	}
	
	public static int multiple(int m, int n) {
		
		if (n == 1) return m;
		int k = multiple(m, n/2);
		
		return (n&1)!=0 ? k + k + m : k + k;
	}
	
	public static void main(String[] args){
		
		System.out.println(MathTest.sqrt(2.0));
		
		System.out.println(MathTest.gcd(9, 15));
		
		System.out.println(MathTest.isPrime(17));
		
		System.out.println(MathTest.add_no_arithm(5, 3));
		
		System.out.println(MathTest.multiple(15, 6));
	}
	
	
}
