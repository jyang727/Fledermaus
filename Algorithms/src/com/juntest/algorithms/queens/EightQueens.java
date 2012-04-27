package com.juntest.algorithms.queens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EightQueens {

	// dimension of the board, also the number of queens to be placed
	private int d;
	private List<Solution> solutions;
	private List<Integer> killedRows;
	private List<Integer> killedCols;
	
	public EightQueens(int di){
		d = di;
		solutions = new ArrayList<Solution>();
	}
	
	public void getSolution(){

		Position seed;
					
		for (int i=0; i<d; i++){			
			for (int j=0; j<d; j++){
				
				seed = new Position(i, j);
				
				// reset
				killedRows = new ArrayList<Integer>();
				killedCols = new ArrayList<Integer>();
				
				killedRows.add(i);
				killedCols.add(j);
				
				Solution s = getSolutionWithSeed(seed);
				if (s!=null){
					solutions.add(s);
				}
				
			}
		}
			
		
	}
	
	private Solution getSolutionWithSeed(Position seed){

		int placedQueens = 1;
		int i, j;
				
		List<Position> queens = new ArrayList<Position>(d);
		queens.add(seed);
		
		for (i=0; i<d;){		
			
			for (j=0; j<d;){
				
				if (killedRows.contains(i)){
					//i++;
					//j=0;
					//continue;
					break;
				}
				else if (killedCols.contains(j)){
					j++;
					continue;
				}
				else {
					
					Position p = new Position(i, j);
				
					if (!underAttack(p, queens) && placedQueens<d){
						queens.add(p);
						killedRows.add(i);
						killedCols.add(j);
						placedQueens++;

						// solution found
						if (placedQueens>=d){
							return populateSolution(queens);
						}
					}
					
					j++;
				}	
			}
			
			i++;	
		}	
		
		return null;
	}
	
	private Solution populateSolution(List<Position> queens){
		
		Solution s = new Solution(d);
		
		for (Position p : queens){
			s.addQueen(p.getI(), p.getJ());
		}
		
		return s;
	}
	
	public void printSolution(){
		for (Solution s : solutions){
			
			System.out.println("\n\n One Solution:");
			
			s.printSolution();
		}
	}
	
	/*
	 * check if a position is under attack by previously placed queens
	 * 
	 */
	private boolean underAttack(Position p, List<Position> queens){
		
		// position already taken
		if (queens.contains(p)){
			
			return false;
		}
		
		for (Position pos : queens){

			// killed by row
			if (p.getI() == pos.getI()) {
				killedRows.add(p.getI());
				return true;
			}
			
			// killed by column 
			if (p.getJ() == pos.getJ()) {
				killedCols.add(p.getJ());
				return true;
			}
			
			// killed by diagonal downward right
			int i, j;
			for (i=pos.getI(), j=pos.getJ(); i<d && j<d; i++, j++){
				if (i == p.getI() && j== p.getJ()) {
					return true;
				}
			}
			
			// killed by diagonal downward left
			for (i=pos.getI(), j=pos.getJ(); i<d && j>=0; i++, j--){
				if (i == p.getI() && j== p.getJ()) {
					return true;
				}
			}
			
			
			// killed by diagonal upward left
			for (i=pos.getI(), j=pos.getJ(); i>=0 && j>=0; i--, j--){
				if (i == p.getI() && j== p.getJ()) {
					return true;
				}
			}			
			
			// killed by diagonal upward right
			for (i=pos.getI(), j=pos.getJ(); i>=0 && j<d; i--, j++){
				if (i == p.getI() && j== p.getJ()) {
					return true;
				}
			}	
					
		}
		
		return false;
		
	}
	
	public static class Position {
		
		private int i, j;
		
		public Position(int i, int j){
			this.i = i;
			this.j = j;
		}
		
		public int getI(){
			return i;
		}

		public int getJ(){
			return j;
		}
		
	}	 
	
	public static class Solution {
		private int dimension;
		
		private Map<String, String> queens;
		
		public Solution(int d){
			dimension = d;
			queens = new HashMap<String, String>();
		}
		
		public void addQueen(int i, int j){
			String key = makeKey(i, j);
			queens.put(key, "Q");
		}
		
		public void printSolution(){
			for (int i=0; i<dimension; i++){
				for (int j=0; j<dimension; j++){
					
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
	
	
	public static void main (String[] args){
		
		int nQueens = 8;
		
		System.out.println("Start processing: " + nQueens + " queens");
		
		EightQueens eightQueens = new EightQueens(nQueens);
		
		eightQueens.getSolution();
		
		eightQueens.printSolution();
		
		System.out.println("Done processing: " + nQueens + " queens");
	}
}
