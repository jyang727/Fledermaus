package com.juntest.datastructures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BinarySearchTree<K extends Comparable<K>, V> {
	
	private Node<K, V> root;
	
	public V get(K key) {
		return get(root, key);		
	}
	
	private V get(Node<K, V> x, K k){

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
		
	public void put(K k, V v){	
		root = put(root, k, v);
	}
	
	private Node<K, V> put(Node<K, V> x, K k, V v){
				
		if (x==null){
			return new Node<K, V>(k, v, 1);
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
	
	public V getMin(){
		return getMin(root).value;
	}
	
	private Node<K, V> getMin(Node<K, V> x){
		if (x.left==null){
			return x;
		}
		
		return getMin(x.left);
	}
	
	public void deleteMin(){
		deleteMin(root);
	}
	
	public Node<K, V> deleteMin(Node<K, V> x){
		
		if (x.left == null){
			return x.right;
		}
		
		x.left = deleteMin(x.left);
		
		return x;
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(Node<K, V> x){
		
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
	
	private int depth(Node<K, V> x){
		
		if (x==null){
			return 0;
		}
		else {
			return x.depth;
		}		
	}	
	
	
	static class Node<K, V> {
		
		public K key;
		public V value;
		
		public Node<K, V> left;
		public Node<K, V> right;
		
		public int size;
		public int depth;
		
		public Node(K k, V v, int s){
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
	
	private void printInOrder(Node<K, V> x){
		
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
	
	private void printLevelOrder(Node<K, V> x){
		
		if (x==null){
			return;
		}
		
		Queue<Node<K, V>> q = new ArrayBlockingQueue<Node<K, V>>(x.size);
		q.add(x);
		
		int d = x.depth;
		
		while (!q.isEmpty()){
			
			Node<K, V> n = (Node<K, V>)q.remove();			
			
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
	
	private void printLevelOrder2(Node<K, V> x){
		
		if (x==null){
			return;
		}
		
		List<List<Node<K, V>>> a = new ArrayList<List<Node<K, V>>>();
				
		int depth = 0;
		
		List<Node<K, V>> l1 = new ArrayList<Node<K, V>>();
		l1.add(x);
		
		a.add(depth, l1);
		
		while (depth <= a.size()){
			
			List<Node<K, V>> l2 = a.get(depth);
			
			depth++;
			
			List<Node<K, V>> l3 = new ArrayList<Node<K, V>>();
			
			for (Node<K, V> n : l2){
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
