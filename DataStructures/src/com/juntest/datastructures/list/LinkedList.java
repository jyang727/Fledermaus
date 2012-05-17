package com.juntest.datastructures.list;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LinkedList<T> {

	private Node<T> head;
	
	public LinkedList (){
		
	}
	
	public void insert(T val){
		
		if (head==null){
			head = new Node<T>(val);
			head.next = null;
		}
		else {			
			Node<T> tail = findTail();
			
			Node<T> newTail = new Node<T>(val);
			
			tail.next = newTail;
			newTail.next = null;
		}
	}
	
	public Node<T> findTail(){
	
		if (head==null){
			return null;
		}
		
		Node<T> prev = head;
		Node<T> p = head;
		
		while (p!=null){
			prev = p;
			p = p.next;						
		}
		
		return prev;
	}
	
	public void printList(){
		Node<T> p = head;
		
		while (p!=null){
			System.out.print(p.value + " ");
			p=p.next;
		}
		
		System.out.println();
	}
	
	public void printListReverse(){
		printListReverse(head);
		
		System.out.println();
	}
	
	private void printListReverse(Node<T> n){

		if (n==null){
			return;
		}
		
		printListReverse(n.next);
		System.out.print(n.value + " ");		
	}
	
	public void printListReverseUsingStack(){
		
		Node<T> p = head;
		
		if (head==null){
			return;
		}
		
		Stack<T> s = new Stack<T>();
		
		while (p!=null){			
			s.push(p.value);
			p = p.next;			
		}
		
		while (!s.isEmpty()){
			System.out.print(s.pop() + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * This method reverse the linked list in place.
	 * 
	 */
	public void reverseList(){
		
		Node<T> remain = head.next;
		head.next = null;
				
		while (remain!=null) {
			
			Node<T> tmp = remain;
			remain = remain.next;
			
			tmp.next = head;
			head = tmp;
		}
		
	}
	
	public static class Node<T> {
		
		public Node(T val){
			value = val;
		}
		
		public T value;
		public Node<T> next;
	}
	
	public static void main(String[] args){
		
		Integer[] in = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		List<Integer> list = Arrays.asList(in);
		
		LinkedList<Integer> ll = new LinkedList<Integer>(); 
		
		for (Integer i : list){
			ll.insert(i);			
		}
		
		ll.printList();
		
		//ll.printListReverse();
		
		//ll.printListReverseUsingStack();
		
		ll.reverseList();
		ll.printList();
	}
}
