package com.juntest.algorithms.snippet;

import java.util.HashSet;
import java.util.Set;

public class QueryTerms {

	private static Set<String> rawQuery = new HashSet<String>();
	
	private static final QueryTerms QUERY_TERMS = new QueryTerms();
	
	public static QueryTerms getInstance(){
		return QUERY_TERMS;
	}
	
	private QueryTerms(){
		rawQuery.add("building");
		rawQuery.add("many");
		rawQuery.add("means");
	}
	
	public Set<String> getRawQuery(){
		return rawQuery;
	}
}
