package com.juntest.algorithms.snippet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SnippetGen {

	private List<Snippet> snippets = new ArrayList<Snippet>();
	
	private List<String> allWords = new ArrayList<String>();
	
	/**
	 * 
	 * @param path path to the document on disk 
	 * @return
	 */
	public Snippet generateMinSnippet(String path, Collection<String> query){
		
		Snippet winner = null; 
		
		try{
			  FileInputStream fstream = new FileInputStream(path);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  
			  String strLine;
			  
			  int wordCursor = 0;			 
			  
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
				  //System.out.println (strLine);
				  String[] words = strLine.split("\\W");
				  
				  for (String word : words){
					  //System.out.print(word + " ");
					  allWords.add(word);
					  wordCursor++;
					  
					  if (query.contains(word)){
						  // first, always start a new one
						  Snippet snippet = new Snippet();
						  snippet.setStartTerm(word);
						  snippet.setStartPos(wordCursor);
						  snippet.addMatchedTerms(word);
						  
						  snippets.add(snippet);
					  
					  	  // then, going through all the existing snippets and try to add the term 	  
						  for (Snippet s : snippets){
							  if (s.isCompleted()){
								  continue;
							  }
							  
							  s.addMatchedTerms(word);
							  if (s.isCompleted()){
								  s.setEndTerm(word);
								  s.setEndPos(wordCursor);
								  
								  System.out.println(s.toString());
							  }
						  }						  					  
					  }					  					  
				  }
				  
			  }
			  			  
			  int minLength = Integer.MAX_VALUE;
			  for (Snippet s : snippets){
				  
				  if (!s.isCompleted()){
					  continue;
				  }
				  
				  int length = s.getEndPos() - s.getStartpos();
				  if (length < minLength){
					  minLength = length;
					  winner = s;
				  }
			  }
			  
			  if (winner!=null){
				  System.out.println("The final winner is: " + winner.toString());
				  System.out.println("And the snippet is:");
				  for (int i=winner.getStartpos()-1; i<winner.getEndPos(); i++){
					  String word = allWords.get(i);
					  if (QueryTerms.getInstance().getRawQuery().contains(word)){
						  word = "->" + word + "<-";
					  }
					  					  
					  System.out.print(word + " ");					  
				  }
				  System.out.println();
			  }
			  else {
				  System.out.println("Unable to find the snippet matching the given query terms.");
			  }
			  
			  //Close the input stream
			  in.close();
		}
		catch (Exception e){		
			System.err.println("Error: " + e.getMessage());
		}				
	
		return winner;
	}
	
}
