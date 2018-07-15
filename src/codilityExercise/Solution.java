package codilityExercise;
import java.math.BigInteger;
import java.util.*;

public class Solution {
	/* Lession 1: BinaryGap*/
    public static int solution(int N) {
        // write your code in Java SE 8
    	
    	if (N==0) return 0;
        String str = Integer.toBinaryString(N);

        int count = 0;
        int one = 0;
        // try to use bit 
        int j=0;
        int max = 0;
        
    	for(int i = 0;i<str.length();i++){
        	if((1<<i & N) != (1<<i)){
        		continue;
        	}
        	// find the first 1
        	one = i;
        	break;
        }
    	int i = 0;
        while(one<str.length() && i<str.length()){
        	for(i = one+1;i<str.length();i++){
        		if((1<<i & N) != (1<<i)){
        			count++;
        			continue;
        		}
        		//find the next 1
        		one = i;
        		if (max < count){
        			max = count;
        		}
        		count = 0;
        		break;
        	} 
        }
        
        return max;
    	
    }
    /*Lesson 6 Sorting*/
    /*Lesson 6.1*/
    public int distinc(int[] A) {
        // write your code in Java SE 8
    	Integer[] array = new Integer[A.length];
		for (int i = 0; i < A.length; i++) {
			array[i] = Integer.valueOf(A[i]);
		}
        Set<Integer> mySet = new HashSet<Integer>(Arrays.asList(array));
        return mySet.size();
    }
    
    /*Lesson 6.2*/
 // max three production of an array
 	public int solution1(int[] A) {
         // write your code in Java SE 8
 		Arrays.sort(A);
 		int result1 = A[A.length -3]*A[A.length -2]*A[A.length -1];
 		int result2 = A[0]*A[1]*A[A.length -1];
 		return Math.max(result1, result2);
     }
 	/*Lesson 6.3*/
 	// triangular triplet an array
	 public int solution2(int[] A) {
	        // write your code in Java SE 8
 		if(A.length < 3 || A.length > 100000) return 0;
	 			// sort first
	 	Arrays.sort(A);
	 	for(int i = 2; i<A.length;i++){
	// 				if(A[i-2]+A[i-1]>A[i]){ // maybe overflow!!!!!!!!
	// 					return 1;
	// 				}
	 		if(A[i-2]>A[i]-A[i-1]){ // 
	 			return 1;
	 		}
		}
	 	return 0;
	 }
	 
	 /*Lesson 6.4*/
	 //NumberOfDiscIntersections
 		// 100% solution: https://rafal.io/posts/codility-intersecting-discs.html
 	public static int intersectingDiscs(int[] A) {
 	        // write your code in Java SE 8
// 			if(A.length>100000) return -1;
// 			int count = 0;
// 			for(int i = 0;i<A.length;i++){
// 				for(int j = i+1;j<A.length;j++){
// 					if((j-A[j]) <= (i+A[i])){
// 						count++;
// 					}
// 				}
// 			}
// 			if(count >= 10000000) return -1;
// 			return count;
 			
 			// store above j-A[j] in A1, i+A[i] in A2
 		long A1[] = new long[A.length];
 		long A2[] = new long[A.length];
 			
 		for(int i = 0;i<A.length;i++){
 			A1[i] = i-(long)A[i];
 			A2[i] = i+(long)A[i];
 		}
 		Arrays.sort(A1);
 		Arrays.sort(A2);
 		long count = 0;
 		for(long x:A1){
 				//binary search x in A2
 			int index = Arrays.binarySearch(A2,x); 
 			if(index >= 0){ // found x in A2
 				while(index >= 0 && A2[index]==x){ //IMPORTANT!!!!!!
 					index--; // binary search only return one match position!!!!
 				}
 				count += A2.length - index -1;
 					// since binary search only return one index
 			}else{
 				int insertIndex = -(index+1); // the index of first element greater than x or a.length
 				count += A2.length - insertIndex;
 			}
 		}
 			// eliminate pairs that are j<=i, since j-i <= 0 <= A[i]+A[j]
 			// those pairs are all counted in count
 		long sub = (1+(long)A.length)*(long)A.length/2;
 		count -=  sub;
 		if(count > 10000000) return -1;
 		return (int)count;
 	}
    
 	/*Lesson 10 Prime and composite numbers*/
 	/*10.4 Flags*/
 	// 100 correct, performance not good
 	public static int solution(int[] A) {
 		if(A.length<1 || A.length > 400000) return -1;
 		
 		ArrayList<Integer> peaks = new ArrayList<Integer>();
 		for(int i=0;i<A.length-2;i++){
 			if(A[i] < A[i+1] && A[i+1] > A[i+2]){
 				peaks.add(i+1);
 			}
 		}
 		if(peaks.size()==0 || peaks.size()==1){
 			return peaks.size();
 		}
 		// at least 2 peaks
 		int flag = 1; // not efficient starting from 1 until found
 		int max = 1;
 		int countFlag = 1;
 		int lastPeak = 0;
 		while(countFlag >= flag){
 			countFlag = 1;
 			lastPeak = 0;
 			for(int i=0;i<peaks.size();i++){
 				if(peaks.get(i)-peaks.get(lastPeak) >= flag){
 					lastPeak = i;
 					countFlag++;
 					if(countFlag>flag){
 						break;
 					}
 				}
 			}
 			if(max < Math.min(flag,countFlag)){
 				max = Math.min(flag,countFlag);
 			}
 			flag++;
 		}
 		return max;
 	}
 	// improve above method //O(N*logN) logN comes from half flag range
 	public static int maxFlag(int[] A) {
 		if(A.length<1 || A.length > 400000) return -1;
 		
 		ArrayList<Integer> peaks = new ArrayList<Integer>();
 		for(int i=0;i<A.length-2;i++){
 			if(A[i] < A[i+1] && A[i+1] > A[i+2]){
 				peaks.add(i+1);
 			}
 		}
 		if(peaks.size()==0 || peaks.size()==1){
 			return peaks.size();
 		}
 		// at least 2 peaks
 		int flagStart = 0; // not efficient starting from 1 until found // half the range instead
 		int flagEnd = peaks.size();
 		int flag = 2;
 		int result = flag;
 		int countFlag = 1;
 		int lastPeak = 0;
 		while(flagStart <= flagEnd){
 			flag = (flagStart+flagEnd)/2;
 			boolean suc = false;
 			countFlag = 1;
 			lastPeak = 0;
 			for(int i=0;i<peaks.size();i++){
 				if(peaks.get(i)-peaks.get(lastPeak) >= flag){
 					lastPeak = i;
 					countFlag++;
 					if(countFlag == flag){
 						suc = true;
 						break;
 					}
 				}
 			}
 			if(suc){ // flag can be larger
 				flagStart = flag+1;
 				result = flag;
 			}else{ // flag should be smaller
 				flagEnd = flag-1;
 			}
 			
 		}
 		return result;
 	}
 	
 	/* Lesson 10,11 Prime and Composite numbers */
 	//checks whether an int is prime or not.
 	public static boolean isPrime(int n) {
 	    for(int i=2;i*i<=n;i++) {
 	        if(n%i==0)
 	            return false;
 	    }
 	    return true;
 	}
 	
 	// return all the prime divisors of a given int
 	public static List<Integer> primeDivisor(int n){
 		List<Integer> list = new ArrayList<Integer>();
 		for(int i=2;i<=n;i++){
 			if(n%i==0){	
 				if(isPrime(i)){
 					list.add(i);
 				}
 			}
 		}
 		return list;
 	}
 	
 	/* Lesson 12 Euclidean algorithm: solves the problem of computing the greatest common divisor (gcd) of two positive integers. */
 	/* 12.2 CommonPrimeDivisors */
 	// O((Z * (max(A) + max(B))))
 	public int countPair(int[] A, int[] B){
 		int count = 0;
 		for(int i = 0;i<A.length;i++){
 			List<Integer> listA = primeDivisor(A[i]);
 			List<Integer> listB = primeDivisor(B[i]);
 			if(listA.equals(listB)){
 				count++;
 			}
 		}
 		return count;
 	}
 	// optimize above 
 	//Method 2
 	public boolean samePD(int a, int b){
 		for(int i=2;i<=Math.max(a,b);i++){
	 		if(a%i==0 && b%i==0){
	 			while(a%i==0){
	 				a = a/i;
	 			}
	 			while(b%i==0){
	 				b = b/i;
	 			}
	 		}
	 		if(a==b) return true;
	 	}
 		return a==b;
 	}
 	public int countPair2(int[] A, int[] B){
 		int count = 0;
 		for(int i = 0;i<A.length;i++){
 			if(samePD2(A[i], B[i])){
 				count++;
 			}
 		}
 		return count;
 	}
 	// Method 3: make use of GCD
 	// check S(M) = S(N) ===> S(M) is contained in S(N) && S(N) is contained in S(M)
 	// http://www.davidespataro.it/solution-to-the-codility-common-prime-divisors-set-problem/
 	public boolean samePD2(int m, int n){
 		// verify S(a) is contained in S(b)
 		int a = m;
 		int b = n;
// 		int gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue(); //not efficient
 		int gcd = gcd(a,b); //100% performance
		while(a!=1){
			a= a/gcd;
			if(a==1){
				break; // verify next
			}
			gcd = gcd(a,b);
			if(gcd == 1){
				return false;
			}
		}
		// verify S(b) is contained in S(a)
		a = m;
 		b = n;
//		gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
 		gcd = gcd(a,b);
		while(b!=1){
			b= b/gcd;
			if(b==1){
				break; 
			}
//			gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
			gcd = gcd(a,b);
			if(gcd == 1){
				return false;
			}
		}		
		
		return true;
 	}
    public int gcd(int a, int b) {
	    if(a % b == 0) return b;
	    return gcd(b,a%b);
	}
    
    /*lesson 11 Sieve of Eratosthenes*/
    /********************Sieve of Eratosthenes*******************/
    public static boolean[] sieve(int n){
    	boolean[] sieve = new boolean[n+1]; // all values defaults to false
    	for(int i = 0;i<sieve.length;i++){
    		sieve[i] = true;
    	}
    	sieve[0] = false;
    	sieve[1] = false;
    	int i = 2;
    	while(i*i<=n){
    		int k = i*i;
    		while(k<=n){
    			sieve[k]=false;
    			k = k+i;
    		}
    		i++;
    	}
    	return sieve;
    }
    
    /* 11.1 Count Semiprimes*/
    public static int[] countSemiprimes(int N, int[] P, int[] Q) {
    	boolean[] sieve = sieve(N);
    	int[] S = new int[N+1]; // store the number of prime divisors, not necessarily true for prime numbers.
    	for(int i = 2; i <= N; i++){ 
    		int k = i;
    		if(sieve[i]){
    			while(k <= N){
        			int temp = k/i;
        			int pow = 1; // how many i can be divided
        			while(temp%i==0){ // totally divide
        				temp = temp/i;
        				pow++;
        			}
        			S[k] += pow;
    				k +=i;
        		}
    		}

    	}
    	
    	int[] K = new int[P.length];
//    	for(int i = 0;i<P.length;i++){
//    		int cnt = 0;
//    		for(int j = P[i]; j<= Q[i]; j++){
//    			if(S[j]==2){
//    				cnt++;
//    			}
//    		}
//    		K[i] = cnt;
//    	}
    	
    	//optimize above
    	// calculate the accumulated semiprimes
    	int[] accuSemi = new int[N+1];   
    	for(int i = 1;i<accuSemi.length;i++){
    		if(S[i] == 2){
    			accuSemi[i] = accuSemi[i-1]+1;
    		}else{
    			accuSemi[i] = accuSemi[i-1];
    		}
    	}
    	for(int i = 0;i<P.length;i++){
    		K[i] = accuSemi[Q[i]] - accuSemi[P[i]-1];
    	}
    	return K;
    }
 	
    /*11.2 CountNonDivisible*/
    public int[] countNonDivisible(int[] A) {
    	int N = A.length;
        int[] F = new int[2*N+1]; //count the frequency of items in A
        int[] count = new int[2*N+1]; // count the divisors
        for(int i=0; i<N;i++){
        	F[A[i]]++;
        }
        for(int i=0;i<N;i++){
        	count[A[i]] = 0;
        	for(int j = 1;j<=Math.sqrt(A[i]);j++){
        		if(A[i]%j==0){
        			count[A[i]] += F[j];
        			if(A[i]/j !=j){
        				count[A[i]] += F[A[i]/j];
        			}
        		}
        	}
        }
        for(int i = 0; i< N;i++){
        	A[i] = N - count[A[i]];
        }
        return A;
    }
    
    /* Lesson 15 Caterpillar method */
    /* 15.4 MinAbsSumOfTwo */
    public int minAbsSumOfTwo(int[] A) {
        // write your code in Java SE 8
        int[] a = new int[A.length];
        for(int i =0;i<A.length;i++){
            a[i] = A[i];
        }
        Arrays.sort(a);
        int min=Integer.MAX_VALUE;
        for(int i = 0;i<A.length;i++){
            int index = Arrays.binarySearch(a, -A[i]); //may not found
            if(index>=0){
                return 0;
            }else{ // index<0
                int insertpoint = - (index+1);//the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key.
                if(insertpoint == 0){
                    //current // no -1
                    min = Math.min(min, Math.abs(A[i]+a[insertpoint]));
                } else if(insertpoint == a.length){
                    //only -1
                    min = Math.min(min, Math.abs(A[i]+a[insertpoint-1]));
                } else {
                    min = Math.min(min, Math.abs(A[i]+a[insertpoint]));
                    min = Math.min(min, Math.abs(A[i]+a[insertpoint-1]));
                }
            }
        }
        return min;
    }
    
    /* Lesson 17 Dynamic Programming*/
    /* 17.1*/
    public int maxResult(int[] A) {
        int[] S = {1,2,3,4,5,6};
        int N = A.length;
        int[] dp = new int[N]; // dp[j] stores the max result from 0 to j
        dp[0] = A[0]; // others are 0 as default //need to set to Integer.MIN_VALUE
        for(int j = 1;j<N;j++){
        	dp[j] = Integer.MIN_VALUE;
        }
        for(int j=1;j<N;j++){
        	for(int i=0;i<S.length;i++){
        		if(j-S[i]>=0){
        			int sum = 0; // the positive sum between j-S[i] and j
        			for(int k=j-S[i]+1;k<j;k++){
        				if(A[k]>0){
        					sum += A[k];
        				}
        			}
        			dp[j] = Math.max(dp[j], dp[j-S[i]]+sum+A[j]);
        		}       		
        	}
        }
        return dp[N-1];
    }
    /* 17.2 */
    // invalid answer:
//    public int minVal(int[] A) {
//    	int N = A.length;
//    	if(N==0){
//    		return 0;
//    	}
//        int[] S = new int[N]; // store sum(abs(A))
//        int[] dp = new int[N]; // dp[j] stores the min Val at j
//        
//        int max = Math.abs(A[0]);
//        dp[0] = Math.abs(A[0]);
//        S[0] = Math.abs(A[0]);
//        for(int j = 1;j<N;j++){
//        	S[j] = S[j-1] + Math.abs(A[j]);
//        	if(Math.abs(A[j])<max){
//        		dp[j] = Math.abs(dp[j-1]-Math.abs(A[j]));
//        	}else{
//        		max = Math.abs(A[j]);
//        		int d = Math.abs(Math.abs(A[j])-S[j-1]);
//        		if(d<S[j-1]){
//        			int index = Arrays.binarySearch(S,d); //
//        			if(index >=0){ // found
//        				dp[j] = 0;
//        			}else{
//        				index = -(index+1);
//        				if(index-1 < 0){
//        					dp[j] = Math.abs(S[index]-d);
//        				}else if (index==N){
//        					dp[j] = Math.abs(S[index-1]-d);
//        				}else{
//        					dp[j] = Math.min(Math.abs(S[index]-d), Math.abs(S[index-1]-d));
//        				}
//        			}
//        		}else{
//        			dp[j] = d;
//        		}
//        		
//        	}
//        } 
//        return dp[N-1];
//    }
    // solution
    // https://codility.com/media/train/solution-min-abs-sum.pdf
    public int minVal(int[] A) {
    	int N = A.length;
    	if(N==0){
    		return 0;
    	}
    	int M = 0; // max(abs(A))
    	for(int i = 0;i<N;i++){
    		A[i] = Math.abs(A[i]);
    		M = Math.max(M,A[i]);
    	}
    	int[] count = new int[M+1]; // initialized to 0 as default // store frequency of elements
    	for(int i=0;i<N;i++){
    		count[A[i]]++;
    	}
    	int S = 0; // sum(abs(A))
    	for(int i = 0;i<N;i++){
    		S += A[i];
    	}
        int[] dp = new int[S+1]; // dp[j] stores how many values a remain after achieving sum j
        //initialize
        dp[0] = 0;
        for(int i = 1;i<dp.length;i++){
        	dp[i] = -1;
        }
        
        for(int a =1;a<count.length;a++){
        	if(count[a]>0){
        		for(int j=0;j<dp.length;j++){
        			if(dp[j]>=0){
        				dp[j] = count[a]; // no a is need to obtain the sum j
        			}else{
        				if(j>=a && dp[j-a]>0){
        					dp[j] = dp[j-a]-1;
        				}
        			}
        		}
        	}
        }
        int P = 0; // P <= S/2 <= Q, P+Q=S, result = Q-P = S-2P
        for(int i = S/2;i>=0;i--){
        	if(dp[i]>=0){
        		P = i;
        		break;
        	}
        }
        return S-2*P;
    }
    public static void main(String arg[]){
//    	System.out.println(solution(12));//
//    	System.out.println(solution(37));
		int[] A = {1,5,2,1,4,0};
//		System.out.println(intersectingDiscs(A));
//		System.out.println(isPrime(2));
		
//		int n = 366;
//		List<Integer> list = primeDivisor(n);
//		for(Integer i:list){
//			System.out.println(i);
//		}
		
//		System.out.println(4%10);//4
		
//		boolean[] sieve = sieve(25);
//		for(int i=0;i<sieve.length;i++){
//			if(sieve[i]==true){
//				System.out.println(i);
//			}
//		}
		int[] P = {1,4,16};
		int[] Q = {26,10, 20};
		int[] countSemiprimes = countSemiprimes(26, P, Q);
		for(int i=0;i<countSemiprimes.length;i++){
			System.out.println(countSemiprimes[i]);
		}
		
		
		
    }
    

}
