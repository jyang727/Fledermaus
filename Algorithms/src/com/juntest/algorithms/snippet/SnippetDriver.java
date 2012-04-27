package com.juntest.algorithms.snippet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SnippetDriver {

	private static final String path = "/home/hduser/Downloads/pg19699.txt";
	
	public static void main(String[] args){
		
		Collection<String> query = new ArrayList<String>();
		
		Iterator<String> itr = QueryTerms.getInstance().getRawQuery().iterator();
		
		while (itr.hasNext()){
			query.add(itr.next());
		}
				
		SnippetGen sg = new SnippetGen();
		
		sg.generateMinSnippet(path, query);
		
	}
}
