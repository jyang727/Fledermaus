package com.juntest.mitbbs;

import java.util.ArrayList;
import java.util.List;

import com.juntest.algorithms.util.RandomSeqNumberGenerator;

public class Q065_LongestIncreasingSeq {

	/*
	void longest_increasing_subsequence2(List<Integer> a, List<Integer> b){
		
		List<Integer> p = new ArrayList<Integer>(a.size());
		int u, v;
		
		if (a.isEmpty())
			return;
		
		b.add(0);
		
		for (int i = 1; i < a.size(); i++){
			
			// If next element a[i] is greater than last element of current longest
			// subsequence a[b.back()], just push it at back of "b" and continue
			if (a.get(b.get(b.size()-1)) < a.get(i))
			{
				p.add(i) = b.back();
				b.push_back(i);
				continue;
			}
			
			// Binary search to find the smallest element referenced by b which is just bigger than a[i]
			// Note : Binary search is performed on b (and not a). Size of b is always <=k
			// and hence contributes O(log k) to complexity.
			for (u = 0, v = b.size()-1; u < v;)
			{
				int c = (u + v) / 2;
				if (a[b[c]] < a[i])
					u = c + 1;
				else
					v = c;
			}
			// Update b if new value is smaller then previously referenced value
			if (a[i] < a[b[u]])
			{
				if (u > 0)
					p[i] = b[u-1];
				b[u] = i;
			}
		}
	
			for (u = b.size(), v = b.back(); u--; v = p[v])
				b[u] = v;
		}
*/
	
	public static void longest_increasing_subsequence(List<Integer> v) {
		
		List<Integer> counts = new ArrayList<Integer>();
		counts.add(1);
		
		List<Integer> prev = new ArrayList<Integer>();
		prev.add(0);
		
		int all_max = -1; 
		int last_index = -1; 
		
		for (int i = 1; i < v.size(); ++i) { 
			
			int max = 1; 
			int index = i; 
			
			for (int j = 0; j < i; ++j) { 
				if (v.get(j) <= v.get(i) && counts.get(j) + 1 > max) {
					max = counts.get(j) + 1;
					index = j;
				}
			}
			
			counts.add(max);
			prev.add(index);
			
			if (max > all_max) {
				all_max = max;
				last_index = i;
			}
			
		}
		
		while (true) {
			
			System.out.println(v.get(last_index) + " ");
			
			if (prev.get(last_index)==last_index){
				break;
			}
			
			last_index = prev.get(last_index);
		}
	}
			
	public static void main(String[] args){
		
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(20);
		
		List<Integer> list = gen.getSequence();		
		System.out.println(gen.toString());
		
		Q065_LongestIncreasingSeq.longest_increasing_subsequence(list);
		
	}
}
