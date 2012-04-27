package com.juntest.datastructures.bst;

import java.util.List;

import com.juntest.algorithms.util.RandomSeqNumberGenerator;



public class Driver {

	public static void main(String[] args){
		
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(20);
		
		List<Integer> list = gen.getSequence();
		
		System.out.println(gen.toString());
		
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();
			
		for (Integer key : list){
			bst.put(key, key.toString());
		}
		
		System.out.println("Size of the tree is " + bst.size());
				
		System.out.println("\nIn order traversal:");
		bst.printInOrder();
		System.out.println("\nLevel order traversal:");
		bst.printLevelOrder();
	}
	
}
