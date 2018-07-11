package algorithms;

import java.util.*;

public class Search {
	
	// array is sorted in increasing order
	// return only one position; there might be more than one position
	public int binarySearch(int[] array, int x){
		int low = 0;
		int high = array.length - 1;
		int middle;
		
		while(low<=high){
			middle = (low+high)/2;
			if(x < array[middle]){ // left
				high = middle-1;
			}else if(x > array[middle]){ // right
				low = middle + 1;
			}else{
				return middle;
			}
		}
		return -1;
	}
//	public ArrayList<Integer> binarySearch(int[] array, int x){
//		int low = 0;
//		int high = array.length - 1;
//		int middle;
//		ArrayList<Integer> positions = new ArrayList<Integer>();
//		while(low<=high){
//			middle = (low+high)/2;
//			if(x < array[middle]){ // left
//				high = middle-1;
//			}else if(x > array[middle]){ // right
//				low = middle + 1;
//			}else{
//				//return middle;
//				positions.add(middle);
//			}
//		}
//		return null;
//	}
	
	/* Q10.3
	 * Search in Rotated Array: 
	 * Given a sorted array of n integers that has been rotated an unknown number of times, 
	 * write code to find an element in the array. 
	 * You may assume that the array was originally sorted in increasing order.
	 * */
	// The question is the same as "rotate an ascending array at some unknown pivot"
	
	// After rotation, one half of the array must be ordered normally (in increasing order)
	
	public ArrayList<Integer> searchRotatedArray(int[] arr, int x){
		int low = 0;
		int high = arr.length - 1;
		int mid = (low + high) / 2;
		ArrayList<Integer> index = new ArrayList<Integer>(); // positions of target value
		
		// 4 basic conditions:
		// left half normal, x not in the range, search from right half
		// right half normal, x not in the range, search from left half
		// left half normal, x in the range, search from left (normal) half, apply binary search
		// right half normal, x in the range, search from right (normal) half, apply binary search
		
		if(arr[low] < arr[mid]){ // left half normal
			if(arr[low] <= x && x <= arr[mid]){ // x in the range
				// binary search from left half
				
				// copy the array
				int[] temp = new int[mid+1];
				for(int i = low;i<=mid;i++){
					temp[i] = arr[i];
				}
				index.add(binarySearch(temp, x)); 
			}else{
				// search from right half // NOT EFFICIENT
				for(int i = mid + 1;i <= high;i++){
					if(arr[i]==x){
						index.add(i);
					}
				}
			}	
		}
		else if(arr[mid] < arr[high]){ // right half normal
			if(arr[mid] <= x && x <= arr[high]){ // x in the range
				// binary search from right half
				
				// copy the array
				int[] temp = new int[high-mid+1];
				int j = 0;
				for(int i = mid;i<=high;i++){
					temp[j] = arr[i];
					j++;
				}
//				ArrayList<Integer> results = binarySearch(temp, x);
//				for(int i = 0;i<results.size();i++){
//					index.add(results.get(i)+mid);
//				}
				index.add(binarySearch(temp, x)+mid);
			}else{
				// search from left half
				for(int i = low;i < mid;i++){
					if(arr[i]==x){
						index.add(i);
					}
				}
			}			
		}
		else if(arr[mid] == arr[high] || arr[mid] == arr[low]){ // need to go through all positions
			for(int i = low;i <= high;i++){
				if(arr[i]==x){
					index.add(i);
				}
			}
		} 
		else {
			return null;
		}
		return index;
	}
	// provided solution
	// follow the same idea as binary search
	// return only one position
	public int searchRotatedArray(int[] arr, int x, int left, int right){
		int mid = (left+right)/2;
		if(arr[mid]==x) return mid; // found element
		if(right<left) return -1; // VERY IMPORTMENT!!!
		
		if(arr[left] < arr[mid]){ // left half normal
			if(arr[left] <= x && x < arr[mid]){ // x in the range, search left
				return searchRotatedArray(arr, x, left, mid-1);
			}else{ // search right
				return searchRotatedArray(arr, x, mid+1, right);
			}
		}
		else if(arr[mid] < arr[right]){ // right half normal
			if(arr[mid] < x && x <= arr[right]){ // x in the range, search right
				return searchRotatedArray(arr, x, mid+1, right);
			}else{ // search left
				return searchRotatedArray(arr, x, left, mid-1);
			}
		}
		else if (arr[mid] == arr[left] || arr[mid] == arr[right]){ // search both halves
			int result = searchRotatedArray(arr, x, left, mid-1);
			if(result==-1){
				return searchRotatedArray(arr, x, mid+1, right);
			}else{
				return result;
			}
		}
		return -1;
	}
	
	/* Q10.5
	 * Sparse Search: Given a SORTED array of strings that is interspersed with empty strings,
	 * write a method to find the location of a given string.
	 * */
	// modification of binary search
	public int sparseSearch(String[] strs, String s, int left, int right){
		if(strs == null || s == null || s==""){ // handle errors
			return -1;
		}
		int mid = (left + right) / 2;
		if(left>right) return -1;
		
		if(strs[mid].equals(s)) return mid; // found element
		
		while(strs[mid].isEmpty() && mid < right){ // move towards right
			mid++;
		}
		if(!strs[mid].isEmpty()){
			if(strs[mid].equals(s)){
				return mid;
			}
			else if(strs[mid].compareTo(s) < 0){ // search right
				return sparseSearch(strs, s, mid +1, right);
			}else{ // search left
				return sparseSearch(strs, s, left, mid - 1);
			}
		}else{ // all "" on the right
			while(strs[mid].isEmpty() && mid > left){ // move towards left
				mid--;
			}
			if(!strs[mid].isEmpty()){
				return sparseSearch(strs, s, left, mid);
			}else {
				return -1;
			}		
		}
	}
	
	/* Q10.9
	 * Sorted Matrix Search: 
	 * Given an M x N matrix in which each row and each column is sorted in ascending order, 
	 * write a method to find an element.*/
	public int[] sortedMatrixSearch(int[][] matrix, int e, int low, int high, int left, int right){
		int[] position = new int[2]; //(x,y)
		int mid1 = (low + high) / 2; // row
		int mid2 = (left + right) / 2; //column
		if(low > high || left>right){
			position[0] = -1;
			position[1] = -1;
			return position;  // VERY IMPORTANT
		}
		if(e == matrix[mid1][mid2]){
			position[0] = mid1;
			position[1] = mid2;
			return position;
		}
		
		if(e < matrix[mid1][mid2]){ // search on top left sub-matrix
			return sortedMatrixSearch(matrix, e, low, mid1, left, mid2);
		} else{
			// search on top right
			position = sortedMatrixSearch(matrix, e, low, mid1, mid2+1, right);
//			int[] result = sortedMatrixSearch(matrix, e, low, mid1, mid2+1, right);
//			position = Arrays.copyOf(result, result.length);
			if(position[0]== -1){ // didn't find on top right
				// search on bottom left
				position = sortedMatrixSearch(matrix, e, mid1+1, high, left, mid2);
				if(position[0]== -1){ // didn't find on bottom left
					// search on bottom right
					position = sortedMatrixSearch(matrix, e, mid1+1, high, mid2+1, right);
					if(position[0]== -1){ // didn't find on bottom right
						position[0] = -1;
						position[1] = -1;
					}
				}
			}
		}
		return position;
	}
	
	// Method 2: start from the top right element
	public int[] sortedMatrixSearch2(int[][] matrix, int e, int M, int N, int x, int y){ // the position of the top right element is (x,y)
		int[] position = new int[2]; 
		if(x>=M || y<0){
			position[0] = -1;
			position[1] = -1;
			return position;
		}
		if(e == matrix[x][y]){
			position[0] = x;
			position[1] = y;
		} else if(e < matrix[x][y]){ // not in the column y
			y--;
			return sortedMatrixSearch2(matrix, e, M, N, x, y);
		} else { // not in the row x
			x++;
			return sortedMatrixSearch2(matrix, e, M, N, x, y);
		}
		
		return position;
	}
	
	
	public static void main(String[] args) {
		
//		int[] array = {1, 1, 1, 2, 4, 5, 8, 9};
//		Search s = new Search();
//		System.out.println(s.binarySearch(array, 8));
//		System.out.println(s.binarySearch(array, 10));
//		System.out.println(s.binarySearch(array, 1));
		
		int[] array = {1, 4, 5, 1, 1, 1, 1};
		Search s = new Search();
//		ArrayList<Integer> indexs = s.searchRotatedArray(array,1);
//		for(int i = 0; i< indexs.size(); i++){
//			System.out.println(indexs.get(i));
//		}

//		System.out.println(s.searchRotatedArray(array, 8, 0, array.length-1));
//		String str = "ball";
//		String str2 = "math";
//		String str3 = "alice";
//		String str4 = "xya";
//		System.out.println(str.compareTo(str4)); 
		
		// String compareTo: subtraction on ASCII value
		// - small; + big; 0 equal
		
//		String[] strings = {"at", "", "", "", "ball", "" , "", "car", "", "dad", "", "", ""};
//		System.out.println(s.sparseSearch(strings, "ball", 0, strings.length-1));
		
		int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
//		int[] position = s.sortedMatrixSearch(matrix, 13, 0, 2, 0, 4);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
		
		int[] position2 = s.sortedMatrixSearch2(matrix, 20, 3, 5, 0, 4);
		System.out.println(position2[0]);
		System.out.println(position2[1]);
//		System.out.println(matrix[2][1]);
	}
		

}
