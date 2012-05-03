package com.juntest.mitbbs;

import java.util.Stack;

/**
 * Using 2 stacks to implement a FIFO queue.
 * 
 * Amortized time complexity is O(1). 
 * 
 * 
 * @author jyang
 *
 * @param <T>
 */
public class Q031_TwoStacksQueue<T> {

	private Stack<T> s1;
	private Stack<T> s2;
	
	public Q031_TwoStacksQueue(){
		s1 = new Stack<T>();
		s2 = new Stack<T>();
	}
	
	public void add(T val) {
		s1.push(val);		
	}
	
	/**
	 * If s2 is not empty, pop from s2; Otherwise pop all entries from s1 and push them into s2. 
	 * 
	 * 
	 * @return
	 */
	public T remove(){
		
		if (s2.isEmpty()){
			while (!s1.isEmpty()){
				s2.push(s1.pop());
			}
		}
		
		return s2.pop();
	}
	
	
	
	public static void main(String[] args){
		
		Q031_TwoStacksQueue<Integer> q031 = new Q031_TwoStacksQueue<Integer>();
		
		q031.add(1);
		q031.add(2);
		q031.add(3);
		
		System.out.println("Pop: " + q031.remove());
		
		q031.add(4);
		q031.add(5);			
		
		
		System.out.println("Pop: " + q031.remove());
		System.out.println("Pop: " + q031.remove());
		System.out.println("Pop: " + q031.remove());
		System.out.println("Pop: " + q031.remove());
		
	}
	
	
}
