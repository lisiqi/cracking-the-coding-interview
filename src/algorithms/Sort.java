package algorithms;

import java.util.*;

public class Sort {
	
	/* Merge Sort */	
	public void mergesort(int[] array){
		int[] helper = new int[array.length];
		mergesort(array, helper, 0, array.length-1);
	}
	private void mergesort(int[] array, int[] helper, int low, int high){
		if(low < high){ // very important
			int middle = (low + high) /2;
			mergesort(array, helper, low, middle);
			mergesort(array, helper, middle+1, high);
			merge(array, helper, low, middle, high);
		}
		
	}
	private void merge(int[] array, int[] helper, int low, int middle, int high){
		for(int i = low; i<=high; i++){
			helper[i] = array[i];
		}
		int helperLeft = low;
		int helperRight = middle+1;
		int current = low;
		
		while(helperLeft <= middle && helperRight <= high){
			if(helper[helperLeft]<=helper[helperRight]){
				array[current] = helper[helperLeft];
				helperLeft++;
			} else {
				array[current] = helper[helperRight];
				helperRight++;
			}
			current++;
		}
		
		// copy the remaining part in the left half. 
		// No need to copy the remaining part in the right half since it's already there
		int remaining = middle - helperLeft;
		for(int i = 0; i<=remaining; i++){
			array[current+i] = helper[helperLeft+i];
		}
	}
	
	/* Quick Sort */
	private int partition(int[] arr, int left, int right){
		int pivot = arr[(left+right)/2];
		while(left<=right){
			while(arr[left] < pivot) left++;
			while(arr[right] > pivot) right--; // no =
			if(left<=right){
				swap(arr, left, right);
				left++;
				right--;
			}
		}
		return left;
	}
	private void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	public void quicksort(int[]arr, int left, int right){
		int index = partition(arr, left, right);
		if(left<index-1){
			quicksort(arr, left, index-1);
		}
		if(index<right){
			quicksort(arr, index, right);
		}
	}
	
	/* Q10.1: Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the
	end to hold B. Write a method to merge B into A in sorted order.	*/	
	
	// assumption: two arrays are sorted in increasing order
	// insert (large) element into the back of A
	public int[] sortedMerge(int[] a, int[] b, int lastA, int lastB){
		int indexA = lastA; // index of last element in A
		int indexB = lastB; // index of last element in B
		int indexMerged = lastA + lastB + 1; // index of last element in merged array
		
		while(indexA >= 0 && indexB >= 0){
			if(a[indexA] >= b[indexB]){
				a[indexMerged] = a[indexA];
				indexA--;
				
			}else{
				a[indexMerged] = b[indexB];
				indexB--;
				
			}
			indexMerged--;
		} // end point: at least one reaches -1
		// if indexB reaches -1 first, finish merging
		// if indexA reaches -1 first, copy the remaining part of B into A
		if(indexA<0 && indexB>=0){
			while(indexB>=0){
				a[indexMerged] = b[indexB];
				indexB--;
				indexMerged--;
			}
		}
		return a;
	}
	
	/* Q10.2
	Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to
	each other.
	*/
	/* An alternative solution : AnagramComparator */
	
	// use HashMapList. If after sorting, two strings are the same. then put together
	// key -> sorted string, list -> strings 
	public void sort(String[] array){
		HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> list;
		for(String s:array){
			String key = sortChars(s);
			if(mapList.containsKey(key)){
				list = mapList.get(key);
			} else{
				list = new ArrayList<String>();
			}
			list.add(s);		
			mapList.put(key, list);
		}
		
		// convert hashtable to array
		int index = 0;
		for(String key : mapList.keySet()){
			ArrayList<String> strs = mapList.get(key);
			for(String s:strs){
				array[index] = s;
				index++;
			}	
		}
	}
	private String sortChars(String s){
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sort s = new Sort();
		int[] array = {1, 8, 9, 2, 4, 5};
		int[] array2 = {1, 4, 5, 2, 8, 9};
		
//		System.out.println("Merge sort");
//		s.mergesort(array);
//		for(int i=0;i<array.length; i++){
//			System.out.println(array[i]);
//		}
//		s.mergesort(array2);
//		for(int i=0;i<array2.length; i++){
//			System.out.print(array2[i]);
//		}
		
//		System.out.println("Quick sort");
//		s.quicksort(array, 0, array.length-1);
//		for(int i=0;i<array.length; i++){
//			System.out.println(array[i]);
//		}
//		
//		s.quicksort(array2, 0, array2.length-1);
//		for(int i=0;i<array2.length; i++){
//			System.out.print(array2[i]);
//		}
		
		String[] strs = {"abc", "cde", "bca", "xyz", "opq", "yzx"};
		s.sort(strs);
		for(int i=0;i<strs.length; i++){
			System.out.println(strs[i]);
		}
		
//		System.out.println(s.sortChars("bca"));
		
		
	}

}
