package com.juntest.algorithms.queens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NQueens {

	private static int numSolutions = 0;
	private int n;
	
	public NQueens(int n){
		this.n = n;
	}
		
	public void getSolutions(){
	
		List<Pos> queens = new ArrayList<Pos>();
		findNextPositions(queens);
	}	
	
	/*
	 * Given the list of Queens already chosen, try to find all possible slots for the next Queen 
	 */
	private void findNextPositions(List<Pos> queens){
		
		// my row number depends on how many queens already chosen (one queen per row).
		int myRow = queens.size();
		
		// my row has n slots
		int[] row = new int[n];
		for (int i=0; i<n; i++){
			row[i] = 1;
		}
					
		// strike out slots under attack by the existing queens
		for (Pos q : queens){
			
			int qRow = q.getRow();
			int qCol = q.getCol(); 
			
			// first, column is gone
			row[q.getCol()] = 0;
			
			// diagonals, left ...
			while (true){				
				qRow++;
				qCol--;
				
				// off board
				if (qRow >= n || qCol < 0) {
					break;
				}
				
				// intersect
				if (qRow == myRow){
					row[qCol] = 0;
					break;
				}
			}
			
			qRow = q.getRow();
			qCol = q.getCol(); 			
			// diagonals, right ...
			while (true){				
				qRow++;
				qCol++;
				
				// off board
				if (qRow >= n || qCol >= n) {
					break;
				}
				
				// intersect
				if (qRow == myRow){
					row[qCol] = 0;
					break;
				}
			}
		}
		
		List<Pos> slots = new ArrayList<Pos>(); 
		
		for (int i=0; i<n; i++){
			if (row[i]==1){
				Pos p = new Pos(myRow, i);
				slots.add(p);
			}
		}
		
		// this is the recursion stopper
		// find a solution?
		if (slots.size()>0 && queens.size()==n-1){
			
			for (Pos p : slots){

				List<Pos> r = new ArrayList<Pos>();
				r.addAll(queens);
				r.add(p);

				numSolutions++;
				
				Solution s = new Solution(n);
				s.addQueens(r);
				s.printSolution();					
			}			
		}
		// or not
		else {
			for (Pos p : slots){
				List<Pos> r = new ArrayList<Pos>();
				r.addAll(queens);
				r.add(p);		
				
				findNextPositions(r);
			}
		}
	}
	
	// representing a position on the chess board
	public static class Pos {
		
		private int row;
		private int col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
		public String toString(){
			return "(" + row + ", " + col + ")";  
		}
	}
	
	// representing a solution
	public static class Solution {
		private int n;
		
		private Map<String, String> queens;
		
		public Solution(int d){
			n = d;
			queens = new HashMap<String, String>();
		}
		
		public void addQueens(List<Pos> queens){
			for (Pos q : queens){
				String key = makeKey(q.getRow(), q.getCol());
				this.queens.put(key, "Q");
			}			
		}
		
		public void addQueen(int row, int col){
			String key = makeKey(row, col);
			queens.put(key, "Q");
		}
		
		public void printSolution(){
			
			if (numSolutions>101) {
				return;	
			}
			else if (numSolutions==101){
				System.out.println("Too many solutions, stop printing after first 100.");
				return;
			}
		
			System.out.println("Solution #" +  numSolutions + ":");
			
			for (int i=0; i<n; i++){
				for (int j=0; j<n; j++){
					
					String key = makeKey(i, j);
					if (queens.get(key)==null)
						System.out.print("x   ");
					else
						System.out.print("Q   ");
				}
				System.out.println("\n");
			}
		}
		
		private String makeKey(int i, int j){
			return  i+ "-" + j;
		}		
	}
	
	public static void main(String[] args){
		
		int n = 8;
		
		NQueens nq = new NQueens(n);
		
		long t1 = System.currentTimeMillis();
		nq.getSolutions();
		long t2 = System.currentTimeMillis();
		
		System.out.println("# of solutions found for n=" + n + ": " + numSolutions + ", time taken: " + (t2-t1) + " milliseconds");		
		
	}
	
}
