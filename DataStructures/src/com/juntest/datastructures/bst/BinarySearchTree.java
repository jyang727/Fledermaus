package com.juntest.datastructures.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	
	private Node<Key, Value> root;
	
	public Value get(Key key) {
		return get(root, key);		
	}
	
	private Value get(Node<Key, Value> x, Key k){

		if (x==null){
			return null;
		}
		
		int c = x.key.compareTo(k);
		
		if (c<0){
			return get(x.left, k);
		}
		else if (c>0){
			return get(x.right, k);
		}
		else {
			return x.value;
		}
	}
	
	public void put(Key k, Value v){	
		root = put(root, k, v);
	}
	
	private Node<Key, Value> put(Node<Key, Value> x, Key k, Value v){
				
		if (x==null){
			return new Node<Key, Value>(k, v, 1);
		}
		
		int c = k.compareTo(x.key);
		
		if (c<0){
			x.left = put(x.left, k, v);
			x.left.depth = x.depth + 1;
		}
		else if (c>0){
			x.right = put(x.right, k, v);
			x.right.depth = x.depth + 1;
		}
		else {
			x.value = v;
		}
		
		x.size = size(x.left) + size(x.right) + 1;
		
		return x;
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(Node<Key, Value> x){
		
		if (x==null){
			return 0;
		}
		else {
			return x.size;
		}		
	}
	
	public int depth(){
		return depth(root);
	}
	
	private int depth(Node<Key, Value> x){
		
		if (x==null){
			return 0;
		}
		else {
			return x.depth;
		}		
	}	
	
	static class Node<Key, Value> {
		
		public Key key;
		public Value value;
		
		public Node<Key, Value> left;
		public Node<Key, Value> right;
		
		public int size;
		public int depth;
		
		public Node(Key k, Value v, int s){
			key = k;
			value = v;
			size = s; 
		}
		
		public String toString(){
			return "[" + key + ":" + value + "] d=" + depth;
		}
	}
	
	public void printInOrder(){
		printInOrder(root);
	}
	
	private void printInOrder(Node<Key, Value> x){
		
		if (x==null){
			return;
		}
		
		printInOrder(x.left);
		System.out.print(" " + x.value);
		printInOrder(x.right);
	}
	
	public void printLevelOrder(){
		
		printLevelOrder(root);				
	}
	
	private void printLevelOrder(Node<Key, Value> x){
		
		if (x==null){
			return;
		}
		
		Queue<Node<Key, Value>> q = new ArrayBlockingQueue<Node<Key, Value>>(x.size);
		q.add(x);
		
		int d = x.depth;
		
		while (!q.isEmpty()){
			
			Node<Key, Value> n = (Node<Key, Value>)q.remove();			
			
			if (d!=n.depth){
				System.out.println();
				d = n.depth;
			}
				
			System.out.print(" " + n.value);			
			
			if (n.left!=null){
				q.add(n.left);
				System.out.print("[" + n.left.value + "]");
			}
			
			if (n.right!=null){
				q.add(n.right);
				System.out.print("[" + n.right.value + "]");
			}
		}
	}	
	
	private void printLevelOrder2(Node<Key, Value> x){
		
		if (x==null){
			return;
		}
		
		List<List<Node<Key, Value>>> a = new ArrayList<List<Node<Key, Value>>>();
				
		int depth = 0;
		
		List<Node<Key, Value>> l1 = new ArrayList<Node<Key, Value>>();
		l1.add(x);
		
		a.add(depth, l1);
		
		while (depth <= a.size()){
			
			List<Node<Key, Value>> l2 = a.get(depth);
			
			depth++;
			
			List<Node<Key, Value>> l3 = new ArrayList<Node<Key, Value>>();
			
			for (Node<Key, Value> n : l2){
				System.out.print(" " + n.value);
				
				if (n.left!=null){
					l3.add(n.left);
					System.out.print("(" + n.left.value + ")");
				}
				
				if (n.right!=null){
					l3.add(n.right);
					System.out.print("(" + n.left.right + ")");
				}
				
				a.add(depth, l3);
			}
			System.out.println();
		}
	}		

}
