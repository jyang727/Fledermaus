package com.juntest.mitbbs;

import java.util.ArrayList;
import java.util.List;

public class Q04_SubsetCombo {
	
	public void printAllSubset(List<Character> input, int i, List<Character> output) {
		
		if (i >= input.size()){
			printList(output);
		}
		else {
			output.add(input.get(i));
			printAllSubset(input, i+1, output);
			output.remove(output.size()-1);
			printAllSubset(input, i+1, output);
		}
	}
	
	private void printList(List<Character> list){
		for (Character c : list){
			System.out.print(c);
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		
		List<Character> input = new ArrayList<Character>();
		List<Character> output = new ArrayList<Character>();
		
		input.add('A');
		input.add('B');
		input.add('C');
		input.add('D');
		input.add('E');
		input.add('F');
		input.add('G');
		
		Q04_SubsetCombo sc = new Q04_SubsetCombo();
		
		sc.printAllSubset(input, 0, output);
	}
	
}
