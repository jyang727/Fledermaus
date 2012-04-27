package com.juntest.algorithms.sort;

import java.util.List;
import java.util.Random;

import com.juntest.algorithms.util.RandomSeqNumberGenerator;

public class Sort {

	public void BubbleSort(Integer[] a) {
		
		System.out.println("This is Bubble Sort");
		printArray(a);
		
		int size = a.length;
		
		int count = 0;
		
		for (int i=0; i < size; i++){
			// note the cutoff index for j: we don't need to go to the end (already sorted),
			// this cut the total number of iterations by half 
			for (int j=0; j < size-1-i; j++){
				count++;
				if (a[j] > a[j+1]){
					swap(a, j, j+1);	
				}
			}
		}
		
		printArray(a);
		
		System.out.println(count + " iterations.");		
	}
	
	public void InsertionSort(Integer[] a) {
		
		System.out.println("This is Insertion Sort");
		printArray(a);
		
		int size = a.length;
		
		for (int i=0; i < size; i++){
			
			// starting from the beginning of the array, pick one element
			int t = a[i];
			int j;
			
			// look from this position and to the left
			for (j=i; j>0; j--){
				
				if (t < a[j-1]){
					// shift the elements to the right if the element is greater than this picked element 
					a[j] = a[j-1];
				}
				// find the right place for the picked element
				else {
					break;
				}
			}
			
			// put the picked element to the right place
			a[j]=t;
		}
		
		
		printArray(a);
	}	
	
	
	public void InsertionSort2(Integer[] a) {
		
		System.out.println("This is Insertion Sort 2");
		printArray(a);
		
		int count = 0;
		
		int size = a.length;
		
		for (int i=0; i < size; i++){			
			for (int j=i; j>0 && a[j] < a[j-1]; j--){		
				count++;
				// keep bubble down until it can't - all elements before it are in sorted order already
				swap(a, j, j-1);
			}
		}
		
		printArray(a);
		
		System.out.println(count + " iterations.");	
	}		
	
	public void SelectionSort(Integer[] a){
		
		System.out.println("This is Selection Sort");		
		printArray(a);
		
		int size = a.length;
		
		for (int i=0; i < size; i++){
			
			int min = a[i];
			int minPos = i;
			
			for (int j=i; j < size; j++) {
				
				if (a[j] < min){
					min = a[j];
					minPos = j;
				}
				
			}
			
			swap(a, i, minPos);			
		}
		
		printArray(a);
	}
	
	public void ShellSort(Integer[] a){
		
		System.out.println("This is Shell Sort");		
		printArray(a);
		
		int size = a.length;
		
		for (int gap = size/2; gap >0; gap = (gap == 2? 1 : (int)(gap/2.2))){
			
			for (int i=gap; i<size; i++){
				
				Integer tmp = a[i];
								
				for (int j=i; j>= gap && tmp < a[j-gap]; j-= gap){
					swap(a, j, j-gap);
				}
				
				//a[j] = tmp;				
			}			
		}
		
		printArray(a);
	}
	
	public void mergeSort(Integer[] a, Integer[] ta, int left, int right){
		
		if (left < right){
			
			int mid = (left+right)/2;
			
			mergeSort(a, ta, left, mid);
			mergeSort(a, ta, mid+1, right);
			
			merge(a, ta, left, mid+1, right);
		}
		
	}
	
	public void mergeSort(Integer[] a){
		
		System.out.println("This is Merge Sort");		
		printArray(a);
		
		Integer[] ta = new Integer[a.length];
		mergeSort(a, ta, 0, a.length-1);
		
		printArray(a);
	}
	
	public void quickSort(Integer[] a){
		
		System.out.println("This is Quick Sort");				
		shuffle(a);		
		printArray(a);
		
		quickSort(a, 0, a.length-1);
		
		printArray(a);
	}
	
	private void quickSort(Integer[] a, int low, int high){
		
		if (low >= high) {
			return;
		}
		
		int p = partition(a, low, high);
		
		quickSort(a, low, p-1);
		quickSort(a, p+1, high);
	}
	
	private int partition(Integer[] a, int low, int high){
		
		int v = a[low];
		
		int p1 = low;
		int p2 = high+1;
			
		while (true){
		
			while (a[++p1] < v ) {
				if (p1==high){
					break;
				}
			}
			
			while (v < a[--p2] ) {
				if (p2==low){
					break;
				}
			}
				
			if (p1>= p2){
				break;
			}
			
			swap(a, p1, p2);
		}
		
		swap(a, low, p2);
		
		return p2;
	}
	
	private void shuffle(Integer[] a){
		
		Random r = new Random();
		
		for (int i=a.length-1; i>0; i--){
			swap(a, i, r.nextInt(i));
		}		
	}
	
	/**
	 * 
	 * @param a
	 * @param ta
	 * @param ls left start position
	 * @param rs right start position
	 * @param re right end position
	 */
	public void merge(Integer[] a, Integer[] ta, int ls, int rs, int re) {
		
		// left end
		int le = rs - 1;
		int ts = ls;
		
		int length = re - ls + 1;
		
		while (ls <= le && rs <= re){
			if (a[ls] <= a[rs]){
				ta[ts] = a[ls];
				ts++;
				ls++;
			}
			else {
				ta[ts] = a[rs];
				ts++;
				rs++;				
			}
		}
		
		
		while (ls<=le){
			ta[ts] = a[ls];
			ts++;
			ls++;
		}
		
		while (rs<=re){
			ta[ts] = a[rs];
			ts++;
			rs++;
		}		
		
		for (int i=0; i<length; i++){
			a[re] = ta[re];
			re--;
		}
	}
	
	public void printArray(Integer[] a){
		System.out.print("[");
		for (int i=0; i<a.length; i++){
			System.out.print(a[i] + ", ");
		}
		System.out.println("]");
	}
	
	private void swap(Integer[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j]=temp;
	}
	
	public static void main(String[] args){
		
		RandomSeqNumberGenerator gen = new RandomSeqNumberGenerator(20);
		
		List<Integer> list = gen.getSequence();		
		System.out.println(gen.toString());
		
		Sort sort = new Sort();
		
		Integer[] a = new Integer[list.size()];
		list.toArray(a);
		
		Integer[] save = a.clone();
		sort.BubbleSort(a);
		
		a = save.clone();
		sort.InsertionSort(a);

		a = save.clone();
		sort.InsertionSort2(a);	
		
		a = save.clone();
		sort.SelectionSort(a);			
		
		a = save.clone();
		sort.ShellSort(a);
		
		a = save.clone();
		sort.mergeSort(a);
		
		a = save.clone();
		sort.quickSort(a);		
	}
	
}
