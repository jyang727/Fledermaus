package com.juntest.datastructures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Sample tree:
 * 						5
 * 				2				4
 * 			6		1		3		9
 * 				  7				  8  10
 * 
 * 
 * @author Jun Yang
 *
 * @param <T>
 */

public class BinaryTree<T extends Comparable<T>> {
	
	private TreeNode<T> root;
	
	public BinaryTree(){
	}
	
	public BinaryTree(TreeNode<T> root){
		this.root = root;
	}
	
	public TreeNode<T> SearchTreeNodeWithAncestors(TreeNode<T> r, T value, List<TreeNode<T>> ancestors)
	{
		if (r == null) 
			return null;
	
		if (r.value.compareTo(value)==0)
		{
			ancestors.add(r);
			return r;
		}
		else
		{
			// assume this node is in the ancestors path 
			ancestors.add(r);
			TreeNode<T> tn = SearchTreeNodeWithAncestors(r.left, value, ancestors);
			if (tn != null)
				return tn;
			
			tn = SearchTreeNodeWithAncestors(r.right, value, ancestors);
			if (tn != null)
				return tn;
			
			// now we cannot find the value in its subtree, so our assumption is wrong, lets remove it from ancestors list
			ancestors.remove(r);
			
			return null;
		}
	}	
	
	public TreeNode<T> findLowestCommonAncestor(TreeNode<T> r, T val1, T val2)
	{
		List<TreeNode<T>> ancestor1 = new ArrayList<TreeNode<T>>();
		TreeNode<T> n1 = SearchTreeNodeWithAncestors(r, val1, ancestor1);
		
		List<TreeNode<T>> ancestor2 = new ArrayList<TreeNode<T>>();
		TreeNode<T> n2 = SearchTreeNodeWithAncestors(r, val2, ancestor2);
	
		int minLen = Math.min(ancestor1.size(), ancestor2.size());
		
		for (int i = 1; i < minLen; ++i)
		{
			if (ancestor1.get(i) != ancestor2.get(i))
				return ancestor1.get(i-1);
		}
		
		return ancestor1.size() > ancestor2.size() ? ancestor2.get(minLen - 1) : ancestor1.get(minLen - 1);
	}
	
	
	public void createTreeInorderPreorder(List<T> inorder, List<T> preorder){
		
		if (inorder==null || preorder==null){
			return;
		}
		
		if (inorder.size() != preorder.size()){
			return;
		}		
		
		root = buildTreeInorderPreorder(inorder, preorder);
		
		System.out.println("\nIn-order:");
		printInOrder(root);
		
		System.out.println("\nPre-order");		
		printPreOrder(root);
		
		System.out.println("\nPost-order");		
		printPostOrder(root);
		
		System.out.println("\nLevel-order");		
		printLevelOrder(root);
		
	}
	
	public void createTreeInorderPostorder(List<T> inorder, List<T> postorder){
		
		if (inorder==null || postorder==null){
			return;
		}
		
		if (inorder.size() != postorder.size()){
			return;
		}		
		
		root = buildTreeInorderPostorder(inorder, postorder);
		
		System.out.println("\nIn-order:");
		printInOrder(root);
		
		System.out.println("\nPre-order");		
		printPreOrder(root);
		
		System.out.println("\nPost-order");		
		printPostOrder(root);
		
	}	
	
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
		TreeNode<T> r = new TreeNode<T>();
		r.value = preorder.get(0);
		
		// divide the in-order array based on root: all the nodes on the left sub tree of root must be
		// left to the root element in the in-order array
		List<T> leftInorder = new ArrayList<T>();
		List<T> rightInorder = new ArrayList<T>();
		
		divide(inorder, r.value, leftInorder, rightInorder);
		
		List<T> leftPreorder = extract(preorder, leftInorder);
		List<T> rightPreorder = extract(preorder, rightInorder);

		r.left = buildTreeInorderPreorder(leftInorder, leftPreorder);
		r.right = buildTreeInorderPreorder(rightInorder, rightPreorder);
		
		return r;
	}
	
	private void divide(List<T> origin, T value, List<T> left, List<T> right){
		
		int i = origin.indexOf(value);
			
		List<T> tmp = origin.subList(0, i);
		left.addAll(tmp);
		
		
		right.addAll(origin.subList(i+1, origin.size()));
		
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
	private TreeNode<T> buildTreeInorderPostorder(List<T> inorder, List<T> postorder){

		if (postorder.size()==0 || inorder.size()==0){
			return null;
		}
		
		// the root is always the first element in pre-order list		
		TreeNode<T> r = new TreeNode<T>();
		r.value = postorder.get(postorder.size()-1);
		
		// divide the in-order array based on root: all the nodes on the left sub tree of root must be
		// left to the root element in the in-order array
		List<T> leftInorder = new ArrayList<T>();
		List<T> rightInorder = new ArrayList<T>();
		
		divide(inorder, r.value, leftInorder, rightInorder);
		
		List<T> leftPostorder = extract(postorder, leftInorder);
		List<T> rightPostorder = extract(postorder, rightInorder);

		r.left = buildTreeInorderPostorder(leftInorder, leftPostorder);
		r.right = buildTreeInorderPostorder(rightInorder, rightPostorder);
		
		return r;		
		
	}
	
	private void printInOrder(TreeNode<T> r){
		
		if (r==null){
			return;
		}
		
		printInOrder(r.left);
		System.out.print(" " + r.value);
		printInOrder(r.right);
	}
	
	private void printPreOrder(TreeNode<T> r){
		
		if (r==null){
			return;
		}
		
		System.out.print(" " + r.value);
		
		printPreOrder(r.left);
		printPreOrder(r.right);
	}	
	
	private void printPostOrder(TreeNode<T> r){
		
		if (r==null){
			return;
		}
				
		printPostOrder(r.left);
		printPostOrder(r.right);
		
		System.out.print(" " + r.value);
	}		
	
	private void printLevelOrder(TreeNode<T> r){
		
		if (r==null){
			return;
		}
				
		Queue<TreeNode<T>> q = new ArrayBlockingQueue<TreeNode<T>>(100);
		
		q.add(r);
		
		while (!q.isEmpty()){
			
			TreeNode<T> tn = q.remove();
			
			System.out.print(" " + tn.value);
			
			if (tn.left!=null){
				q.add(tn.left);
			}
			
			if (tn.right!=null){
				q.add(tn.right);
			}			
		}
		
		System.out.println();
	}		
	
	public static class TreeNode<T> {
		
		public T value;
		public TreeNode<T> left;
		public TreeNode<T> right;		
		
		@Override
		public String toString(){
			
			String s = "Node: Value=" + value;
			
			if (left!=null){
				s += " left=" + left.value;
			}
			
			if (right!=null){
				s+= " right=" + right.value;
			}
			
			return s;
		}
	}
	
	public static void main(String[] args){
		
		Integer[] in_order = new Integer[] {6,2,7,1,5,3,4,8,9,10};
		Integer[] pre_order = new Integer[] {5,2,6,1,7,4,3,9,8,10};
		Integer[] post_order = new Integer[] {6,7,1,2,3,8,10,9,4,5};
		
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		
		bt.createTreeInorderPreorder(Arrays.asList(in_order), Arrays.asList(pre_order));		
		
		//bt.createTreeInorderPostorder(Arrays.asList(in_order), Arrays.asList(post_order));
		
		BinaryTree.TreeNode<Integer> tn = bt.findLowestCommonAncestor(bt.root, new Integer(7), new Integer(8));
		
		System.out.println("Lowest common ancestor is: " + tn);
	}
}
