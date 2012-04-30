package com.juntest.mitbbs;

import java.util.ArrayList;
import java.util.List;

public class Q4_SubsetComboDP {
	
	private List<List<String>> results = new ArrayList<List<String>>();
	
	public void printAllSubset(String[] input){
		
		List<String> tmp = new ArrayList<String>();
		
		for (int i=0; i<input.length; i++){
			tmp.add(input[i]);
		}
		
		results.add(0, tmp);
		
		printAllSubsetInternal(input);
	}
	
	private void printAllSubsetInternal(String[] input){
		
		int size = input.length;
		
		for (int i=1; i<size; i++){
			
			List<String> prevCombos = results.get(i-1);
			
			List<String> tmp = new ArrayList<String>();
			
			for (int j=0; j<size; j++){
				
				String a = input[j];
				
				for (int k=j; k<prevCombos.size(); k++){
					String s = prevCombos.get(k);
					
					boolean bool = false;
					
					for (String t : tmp){
						if (t.contains(a)){
							bool = true;
							break;
						}
					}
					
					if (bool){
						continue;
					}
					
					if (!s.contains(a)){
						String b = a+s;
						tmp.add(b);
					}				
				}
//				
//				for (String s : prevCombos){
//					if (!s.contains(a)){
//						String b = s+a;
//						tmp.add(b);
//					}
//				}
			}
			
			results.add(i, tmp);
		}
		
		for (List<String> l1 : results){
			for (String s : l1){
				System.out.println(s);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		
		String[] input = new String[]{"A", "B", "C"};
		
		Q4_SubsetComboDP sc = new Q4_SubsetComboDP();
		
		sc.printAllSubset(input);
	}

}
