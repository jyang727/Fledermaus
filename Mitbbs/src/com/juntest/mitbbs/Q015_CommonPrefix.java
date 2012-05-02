package com.juntest.mitbbs;

import java.util.Arrays;
import java.util.List;

/**
 * This class finds the longest common prefix for a group of strings in the input
 * 
 * Google interview question
 * 
 * @author jyang
 *
 */

public class Q015_CommonPrefix {

	public String findCommonPrefix(List<String> in){
		
		if (in==null || in.isEmpty()){
			return null;
		}
		
		String prefix = null;
		
		String first = in.get(0);
		
		for (int i=1; i<=first.length(); i++){
			
			String sp = first.substring(0, i);
			
			boolean b = false;
			
			for (String s : in){
				if (!s.startsWith(sp)){
					b = true;
					break;
				}
			}
			
			if (!b){
				prefix = sp;
			}
			else {
				break;
			}
		}
		
		return prefix;
	}
	
	
	
	public static void main(String[] args){
		
		String[] words = new String[]{"abcdef", "abc", "abcde", "abcd", "abcdefg"};
		
		Q015_CommonPrefix q15 = new Q015_CommonPrefix();
		
		String prefix = q15.findCommonPrefix(Arrays.asList(words));
		
		System.out.println(prefix);
	}
	
	
}
