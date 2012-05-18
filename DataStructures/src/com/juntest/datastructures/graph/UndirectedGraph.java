package com.juntest.datastructures.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class UndirectedGraph {
	
	private int v;
	private int e;
	
	private List<Integer>[] adj;

	public UndirectedGraph(int v){
		this.v = v;
		this.e = 0;
		
		init();
	}
	
	private void init(){
		adj = (List<Integer>[]) new ArrayList[v];
		
		for (int i=0; i<v; i++){
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	public UndirectedGraph(String fileName){
			
		try {
			
			FileReader r = new FileReader(fileName);
			
			BufferedReader reader = new BufferedReader(r);
			
			v = Integer.parseInt(reader.readLine());				
			e = Integer.parseInt(reader.readLine());
			
			init();
			
			String line = reader.readLine();
			
			while (line!=null){
				
				String[] edge = line.split(" ");
				
				int s = Integer.parseInt(edge[0]);
				int d = Integer.parseInt(edge[1]);
				
				addEdge(s, d);
				
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEdge(int s, int d){
		adj[s].add(d);
		adj[d].add(s);
		e++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int V(){
		return v;
	}
	
	public int E(){
		return e;
	}
	
	public static void main(String[] args){
	
		String file = "D:/Repos/Fledermaus/DataStructures/src/com/juntest/datastructures/graph/tinyG.txt";
		
		UndirectedGraph g = new UndirectedGraph(file);
		
		System.out.println("V=" + g.V() + " E=" + g.E());
		
		int s = 0;
		DepthFirstSearch search = new DepthFirstSearch(g, s);
		
		for (int v=0; v<g.V(); v++){
			if (search.marked(v)){
				System.out.println(v + " ");
			}
		}
		
		System.out.println();
		
		if (search.count()!=g.V()){
			System.out.println("NOT ");
		}
		System.out.println("connected");
		
		// DFS
		DepthFirstPath dPath = new DepthFirstPath(g, s);
		
		for (int v=0; v<g.V(); v++){
			System.out.print(s + " to " + v + ": ");
			if (dPath.hasPathTo(v)){
				for (int x : dPath.pathTo(v)){
					if (x==s)
						System.out.print(x);
					else 
						System.out.print("-" + x);
				}
			}
			System.out.println();
		}
		
		// BFS
		BreadthFirstPath bPath = new BreadthFirstPath(g, s);
		
		for (int v=0; v<g.V(); v++){
			System.out.print(s + " to " + v + ": ");
			if (bPath.hasPathTo(v)){
				for (int x : bPath.pathTo(v)){
					if (x==s)
						System.out.print(x);
					else 
						System.out.print("-" + x);
				}
			}
			System.out.println();
		}
	}
	
	public static class DepthFirstSearch {
		
		private boolean[] marked;
		private int count;
		
		public DepthFirstSearch(UndirectedGraph g, int s){
			marked = new boolean[g.V()];
			dfs(g, s);
		}
		
		private void dfs(UndirectedGraph g, int v){
			
			marked[v] = true;
			count++;
			
			for (int w : g.adj[v]){
				if (!marked[w]){
					dfs(g, w);
				}
			}
			
		}
		
		public boolean marked(int w){
			return marked[w];
		}
		
		public int count(){
			return count;
		}
		
	}
	
	public static class DepthFirstPath {
		
		private boolean marked[];
		private int[] edgeTo;
		private int s;
		
		public DepthFirstPath(UndirectedGraph g, int s){
			marked = new boolean[g.V()];
			edgeTo = new int[g.V()];
			this.s = s;
			
			dfs(g, s);
		}
		
		public void dfs(UndirectedGraph g, int v){
			
			marked[v] = true;
			for (int w : g.adj[s]){
				if (!marked[w]){
					edgeTo[w] = v;
					dfs(g, w);
				}
			}
		}
		
		public boolean hasPathTo(int v){
			return marked[v];
		}
		
		public Iterable<Integer> pathTo(int v){
			if (!hasPathTo(v)){
				return null;
			}
			
			Stack<Integer> path = new Stack<Integer>();
			
			for (int x=v; x!=s; x=edgeTo[x]){
				path.push(x);
			}
			
			path.push(s);
			return path;
		}
		
	}
	
	public static class BreadthFirstPath {
		
		private boolean marked[];
		private int[] edgeTo;
		private int s;
		
		public BreadthFirstPath(UndirectedGraph g, int s){
			marked = new boolean[g.V()];
			edgeTo = new int[g.V()];
			this.s = s;
			
			bfs(g, s);
		}
		
		public void bfs(UndirectedGraph g, int v){

			Queue<Integer> q = new ArrayBlockingQueue<Integer>(g.V());
			
			marked[v] = true;
			
			q.add(v);
			
			while (!q.isEmpty()){
				
				int x = q.remove();
				
				for (int w : g.adj(x)){
					if (!marked[w]){
						marked[w] = true;
						edgeTo[w] = x;
						q.add(w);
					}
				}
			}			
		}
		
		public boolean hasPathTo(int v){
			return marked[v];
		}
		
		public Iterable<Integer> pathTo(int v){
			if (!hasPathTo(v)){
				return null;
			}
			
			Stack<Integer> path = new Stack<Integer>();
			
			path.push(s);
			
			for (int x=v; x!=s; x=edgeTo[x]){
				path.push(x);
			}		
			
			return path;
		}
		
	}
}
