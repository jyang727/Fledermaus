package com.juntest.algorithms.snippet;

import java.util.HashSet;
import java.util.Set;

public class Snippet implements Comparable<Snippet> {

	private int startPos;
	private int endPos;
	
	private String startTerm;
	private String endTerm;
	
	private Set<String> matchedTerms;
	
	public Snippet(){
		startPos = 0;
		endPos = 0;
		
		startTerm = null;
		endTerm = null;
		
		matchedTerms = new HashSet<String>();
	}
	
	@Override
	public String toString(){
		return "Snippet: Length=" + (endPos-startPos) + " <" + startPos + ", " + endPos + ">" + " Start term: " + startTerm + " End term: " + endTerm ;
	}
	
	public void setStartPos(int pos){
		startPos = pos;
	}
	
	public void setEndPos(int pos){
		endPos = pos;
	}
	
	public int getStartpos(){
		return startPos;
	}

	public int getEndPos(){
		return endPos;
	}	
	
	public void setStartTerm(String term){
		startTerm = term;
	}
	
	public void setEndTerm(String term){
		endTerm = term;
	}
	
	public String getStartTerm(){
		return startTerm;
	}

	public String getEndTerm(){
		return endTerm;
	}
	
	public boolean alreadyMatched(String term){
		return matchedTerms.contains(term);
	}
	
	public void addMatchedTerms(String term){
		matchedTerms.add(term);
	}
	
	public boolean isCompleted(){
		return QueryTerms.getInstance().getRawQuery().equals(matchedTerms);
	}

	@Override
	public int compareTo(Snippet s) {
		// TODO Auto-generated method stub
		if ((endPos-startPos) < (s.getEndPos()-s.getStartpos())){
			return -1;
		}
		else if ((endPos-startPos) > (s.getEndPos()-s.getStartpos())){
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
