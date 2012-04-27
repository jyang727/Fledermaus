package com.juntest.algorithms.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coins {
	
	private static int count = 0;
	
	private Map<Integer, Integer> coinsUsed = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> lastCoins = new HashMap<Integer, Integer>();
	
	/**
	 * Very in-efficient recursive solution
	 * 
	 * @param coins
	 * @param change
	 * @return
	 */
	int makeChange(int[] coins, int change ){
		
		int minCoins = change;
		
		for (int i = 0; i < coins.length; i++){
			if (coins[i]==change){
				return 1;
			}
		}
		
		for (int j=1; j<=change/2; j++){
			int thisCoins = makeChange(coins, j) + makeChange(coins, change-j);
			
			System.out.println("Number of coins used = " + thisCoins);
			
			count++;
			
			if (thisCoins < minCoins){
				minCoins = thisCoins;
			}
		}
		
		return minCoins;
	}
	
	/**
	 * DP version
	 */
	void makeChangeDP(int[] coins, int change ){
		
		coinsUsed.put(0, 0);
		lastCoins.put(0, 0);
		
		for (int cents = 1; cents <= change; cents++){
			
			int minCoins = change, newCoin = 0;
			
			for (int j = 0; j < coins.length; j++) {
				
				if (coins[j] > cents) {
					continue;
				}
				
				int m = coinsUsed.get(cents - coins[j]);
				
				if (m+1 < minCoins) {
					minCoins = m+1;
					newCoin = coins[j];
				}
			}
			
			coinsUsed.put(cents, minCoins);
			lastCoins.put(cents, newCoin);
			
		}
		
	}
	
	public int getMinCoins(int c){
		return coinsUsed.get(Integer.valueOf(c));
	}
	
	public int getLastCoin(int c){
		return lastCoins.get(Integer.valueOf(c));
	}	
	
	public void printSolution(int c){
		
		List<Integer> s = new ArrayList<Integer>();
		
		int key = c;
		int lastCoin;
		
		while (true) {
			
			lastCoin = lastCoins.get(Integer.valueOf(key));
			
			if (lastCoin <=0) {
				break;
			}
			
			s.add(0, lastCoin);
			key -= lastCoin;			
		}		
		
		System.out.print("[");
		for (Integer i : s){
			System.out.print(i + ", ");
		}
		System.out.println("]");
	}
	
	public static void main(String[] args){
		
		int change = 63;
		int[] coins = new int[] {1, 5, 10, 21, 25};
		
		Coins c = new Coins();
		c.makeChangeDP(coins, change);
		
		for (int cents = 1; cents <= change; cents++){
			
			System.out.println("Minimum number of coins for: " + cents + " cents: " + c.getMinCoins(cents));		
			c.printSolution(cents); 
			System.out.println();
		}
		
		//System.out.println("Minimum number of coins = " + c.getMinCoins(change));
		//System.out.println("Total number of combinations = " + count);
		
	}
	
}
