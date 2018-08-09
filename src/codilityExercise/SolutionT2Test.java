package codilityExercise;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

public class SolutionT2Test {
	class SolutionT2 {
		public int[] solution(int K, int M, int[] A) { // O(N+M)
	    	int N = A.length;
	        int[] count = new int[M+2];
	        int[] result;
	        int[] leaders = new int[M+2]; // mark the value of leaders to avoid sorting
	        
	        //count element occurrences in the first new array, as well as the leader
	        for(int i=0;i<K;i++) {
	        	A[i]++;
	        }
	        for(int i=0;i<N;i++){ // O(N)
	        	count[A[i]]++;
	        	if(count[A[i]]> N/2) {
	        		leaders[A[i]] = 1 ;
	        	}
	        }
	        
	        for(int i =1;i<=N-K;i++){ // O(N)
	        	// modify count {only the head(before K) and tail(the last of K) of the K elements}
	        	count[A[i-1]+1]--;
	        	count[A[i-1]]++;
	        	count[A[i+K-1]]--;
	        	count[A[i+K-1]+1]++;
	        	
	        	// if there is new leader, it can only come from either head or tail 
	        	if(count[A[i-1]]>N/2) {
	        		leaders[A[i-1]]=1;
	        	}
	        	if(count[A[i+K-1]]>N/2) {
	        		leaders[A[i+K-1]]=1;
	        	}
	        }
	        
	        ArrayList<Integer> leaderList = new ArrayList<Integer>();
	        for(int i=0;i<leaders.length;i++){ // O(M)
	        	if(leaders[i]==1) {
	        		leaderList.add(i);
	        	}
	        }
	        if(leaderList.size()>0) {
	        	result = new int[leaderList.size()];
	        	for(int i=0;i<leaderList.size();i++){
	        		result[i]=leaderList.get(i);
	        	}
	        }else {
	        	return result = new int[0];
	        }
	        return result;  
	    }
	}
	
	@Test
	public void testNormalExample1() {
		SolutionT2 tester = new SolutionT2();
		int[] A = {1, 2, 2, 2, 3, 3, 1, 2, 2};
    	int[] result = tester.solution(3, 5, A);
    	int[] ans = {2,3};
    	assertArrayEquals(result, ans);
	}
	
	@Test
	public void testNormalExample2() {
		SolutionT2 tester = new SolutionT2();
		int[] A = {2, 1, 3, 1, 2, 2, 3};
    	int[] result = tester.solution(3, 5, A);
    	int[] ans = {2};
    	assertArrayEquals(result, ans);
	}
	@Test
	public void testEmptyResult() {
		SolutionT2 tester = new SolutionT2();
		int[] A = {1,2,3,4};
    	int[] result = tester.solution(3, 5, A);
    	int[] ans = {};
    	assertArrayEquals(result, ans);
	}
}
