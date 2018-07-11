package algorithms;

import java.util.*;

/* An alternative solution to Q10.2: Group Anagrams: 
 * Write a method to sort an array of strings so that all the anagrams are next to each other.*/
// This comparator will be used to indicate that two strings which are anagrams of each other are equivalent.
public class AnagramComparator implements Comparator<String>{
	private String sortChars(String s){
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
	
	@Override
	public int compare(String o1, String o2) {	
		return sortChars(o1).compareTo(sortChars(o2));
	}
	
	public static void main(String[] args){
		String[] strs = {"abc", "cde", "bca", "xyz", "opq", "yzx", "poq", "cinema", "iceman"};
		Arrays.sort(strs, new AnagramComparator());
		for(int i=0;i<strs.length; i++){
			System.out.println(strs[i]);
		}
	}

}
