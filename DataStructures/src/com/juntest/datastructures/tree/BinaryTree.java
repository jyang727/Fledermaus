package com.juntest.datastructures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.juntest.datastructures.tree.BinarySearchTree.Node;

public class BinaryTree<T> {
	
	private TreeNode<T> root;
	
	public BinaryTree(){
	}
	
	public BinaryTree(TreeNode<T> root){
		this.root = root;
	}
	
	public void createTreeInorderPreorder(List<T> inorder, List<T> preorder){
		
		if (inorder==null || preorder==null){
			return;
		}
		
		if (inorder.size() != preorder.size()){
			return;
		}		
		
		root = buildTreeInorderPreorder(inorder, preorder);
		
		printInOrder(root);
		
	}
	
//	public void createTreeInorderPostorder(T[] inorder, T[] preorder){
//		
//		if (inorder==null || preorder==null){
//			return;
//		}
//		
//		if (inorder.length != preorder.length){
//			return;
//		}		
//		
//		root = buildTreeInorderPostorder(inorder, preorder, inorder.length);		
//	}	
	
	/**
	 * This method builds a tree based on the values specified both in in-order and pre-order traversals. 
	 * 
	 * @param inorder the in-order traversal of the tree as an array
	 * @param preorder the pre-order traversal of the tree as an array
	 * @param n number of nodes
	 * @return root of the tree
	 */
	private TreeNode<T> buildTreeInorderPreorder(List<T> inorder, List<T> preorder){
		
		if (preorder.size()==0 || inorder.size()==0){
			return null;
		}
		
		// the root is always the first element in pre-order list		
		TreeNode<T> root = new TreeNode<T>();
		root.value = preorder.get(0);
		
		// divide the in-order array based on root: all the nodes on the left sub tree of root must be
		// left to the root element in the in-order array
		List<T> leftInorder = null;
		List<T> rightInorder = null;
		
		divide(inorder, root.value, leftInorder, rightInorder);
		
		List<T> leftPreorder = extract(preorder, leftInorder);
		List<T> rightPreorder = extract(preorder, rightInorder);

		root.left = buildTreeInorderPreorder(leftInorder, leftPreorder);
		root.right = buildTreeInorderPreorder(rightInorder, rightPreorder);
		
		return root;
	}
	
	private void divide(List<T> origin, T value, List<T> left, List<T> right){
		
		int i = origin.indexOf(value);
		
		left.addAll(origin.subList(0, i-1));
		right.addAll(origin.subList(i+1, origin.size()-1));
		
	}
	
	private List<T> extract(List<T> origin, List<T> sub){
		
		List<T> r = new ArrayList<T>();
		
		for (T t : origin){
			
			if (sub.contains(t)){
				r.add(t);
			}
		}
		
		return r;
		
	}
	
	/**
	 *  This method builds a tree based on the values specified both in in-order and post-order traversals. 
	 * 
	 * @param inorder the in-order traversal of the tree as an array
	 * @param postorder the pre-order traversal of the tree as an array
	 * @param n number of nodes
	 * @return root of the tree
	 */
//	private TreeNode buildTreeInorderPostorder(T[] inorder, T[] postorder, int n){
//		
//	}
	
	private void printInOrder(TreeNode<T> r){
		
		if (r==null){
			return;
		}
		
		printInOrder(r.left);
		System.out.print(" " + r.value);
		printInOrder(r.right);
	}
	
	public static class TreeNode<T> {
		
		public T value;
		public TreeNode<T> left;
		public TreeNode<T> right;		
		
	}
	
	public static void main(String[] args){
		
		Integer[] in_order = new Integer[] {6,2,7,1,5,3,4,8,9,10};
		Integer[] pre_order = new Integer[] {5,2,6,1,7,4,3,9,8,10};
		
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.createTreeInorderPreorder(Arrays.asList(in_order), Arrays.asList(pre_order));		
		
	}
}
