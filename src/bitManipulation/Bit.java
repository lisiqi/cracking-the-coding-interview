package bitManipulation;

import java.lang.Math;

public class Bit {
	
	// decide whether a number num's i-th bit is 1 or 0
	public boolean getBit(int num, int i){
		return ((num & (1 << i)) !=0);
	}
	
	// set a number num's i-th bit to 1
	public int setBit(int num, int i){
		return num | (1 << i);
	}
	
	// set (clear) a number num's i-th bit to 0
	int clearBit(int num, int i){
		return num & (~(1 << i));
	}
	
	// clear all bits from the most significant bit (MSB) through i (inclusive)
	int clearBitsMSBthroughI(int num, int i){
		return num & ((1 << i)-1);
	}
	
	// clear all bits from i through 0 (inclusive)
	int clearBitsIthrough0(int num, int i){
		return num & (-1 << (i+1));
	}
	
	// set the i-th bit to a value
	int updateBit(int num, int i, boolean bitIs1){
		int v = bitIs1 ? 1 : 0;
		return num & (~(1 << i)) | (v << i);
	}
	
	/* Q 5.1
	 * You are given two 32-bit numbers, N and M, and two bit positions, i and j. 
	 * Write a method to insert M into N such that M starts at bit j and ends at bit i. 
	 * You can assume that the bits j through i have enough space to fit all of M. 
	 * That is, if M= 10011, you can assume that there are at least 5 bits between j and i. 
	 * You would not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.*/
	public int insert5p1(int M, int N, int j, int i){	
		//clear N from j to i
		int temp = 0;
		for(int k = i; k <= j; k++){
			temp = temp + (1 << k);
		}
		return N & (~temp) | (M << i);
	}
	
	/* Q 5.2
	 * Binary to String: 
	 * Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, 
	 * print the binary representation.
	 *  If the number cannot be represented accurately in binary with at most 32 characters, 
	 *  print "ERROR:'*/
	public String printBinary(double num){
		if(num >= 1 || num <= 0){
			return "ERROR";
		}
		
		StringBuilder string = new StringBuilder(); 
		string.append(".");
		
		double temp = num;
		
		while(temp>0){
			temp = temp*2;
			if(temp>=1){
				string.append(1);
				temp = temp -1;
			}else{
				string.append(0);
			}
			if(string.length() > 32){
				return "ERROR";
			}
		}
		return string.toString();	
	}
	
	public static void main(String[] args) {
		Bit b = new Bit();
		int num = 3; // 0011
		int i = 3;
		
		// from right to left, index start from 0
		System.out.println(Integer.toBinaryString(num));
		System.out.println(b.getBit(num,0)); // true
		System.out.println(Integer.toBinaryString(b.setBit(num,i))); // 1011, rather than 0111, since index starts from 0
		System.out.println(Integer.toBinaryString(b.clearBit(num,1))); //0001
		
		System.out.println(Integer.toBinaryString(b.clearBitsMSBthroughI(7,1))); // from 0111 to 0001
		System.out.println(Integer.toBinaryString(b.clearBitsIthrough0(7,1))); // from 0111 to 0100
		System.out.println(Integer.toBinaryString(b.updateBit(7,1,false))); // from 0111 to 0101
		
		int M = Integer.parseInt("10011", 2);
		int N = Integer.parseInt("10000100000", 2);
		System.out.println(Integer.toBinaryString(b.insert5p1(M,N,6,2)));
		
		System.out.println(b.printBinary(0.5625));
		System.out.println(b.printBinary(Math.pow(2,-32))); // ERROR
		System.out.println(b.printBinary(Math.pow(2,-31))); // .000...1
		
		
	}

}
