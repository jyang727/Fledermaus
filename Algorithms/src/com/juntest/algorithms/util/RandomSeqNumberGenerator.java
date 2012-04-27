package com.juntest.algorithms.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomSeqNumberGenerator {

	private int size;
	
	private List<Integer> sequence;
	
	public RandomSeqNumberGenerator(int size){	
		this.size = size;
	}
	
	public List<Integer> getSequence(){
		
		generateSeq();
		
		return sequence;
	}
	
	private void generateSeq(){
		
		Random rand = new Random();
		
		List<Double> temp = new ArrayList<Double>(size);
		sequence = new ArrayList<Integer>(size);
		
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		
		for (int i=0; i<size; i++){
			
			//double r = Math.abs(new Double(rand.nextDouble()));
			double r = new Double(rand.nextDouble());
			
			if (r < min){
				min = r; 
			}
			
			if (r > max){
				max = r; 
			}
			
			temp.add(r);
		}
		
		for (Double j : temp){
			int m = (int)(((j - min)/(max-min)*100.0d) - 50.0d);
			sequence.add(m);
		}

		
		/*
		for (int i=0; i<size; i++){
			int r = new Integer(rand.nextInt());
			List<Integer> digits = extractDigits(r);
			
			int sum = 0;
			for (Integer j : digits){
				sum += j;
			}
			
			sequence.add(sum);
		}
		*/
	}
	
	private List<Integer> extractDigits(int n){
	
		List<Integer> hold = new ArrayList<Integer>();
		
		while (true){
			
			int d = n % 10;
			hold.add(0, d);
			
			n = n/10;
			
			if (n < 10){
				hold.add(0, n);
				break;
			}
		}
		
		return hold;		
	}
	
	@Override
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		
		Iterator<Integer> itr = sequence.iterator();
		
		sb.append("[");
		while (itr.hasNext()){
			sb.append(itr.next());
			sb.append(", ");
			
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public static void main(String[] args){
		int n = -1675317130;
		
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(10);
		
		List<Integer> digits = gen.extractDigits(n);
		
		for (Integer i : digits){
			System.out.print(i);
		}
		
		System.out.println();
		
	}
}
