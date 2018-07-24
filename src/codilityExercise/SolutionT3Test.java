package codilityExercise;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

public class SolutionT3Test {
	class SolutionT3 {
		public String solution(String S) {
	    	if(S.equals("")){
	    		return "null";
	    	}
	    	String err = "error";
	    	String[] ops = S.split(" ");
	    	ArrayList<String> stack = new ArrayList<String>(); // push to the last, pop from the last
	    	for(int i = 0;i<ops.length;i++){
	    		if(ops[i].equals("DUP")){
	    			if(stack.size()==0){
	    				return err;
	    			}else{
	    				stack.add(stack.get(stack.size()-1));
	    			}
	    		} else if(ops[i].equals("POP")){
	    			if(stack.size()==0){
	    				return err;
	    			}else{
	    				stack.remove(stack.size()-1);
	    			}
	    		} else if(ops[i].equals("+")){
	    			if(stack.size()<2){
	    				return err;
	    			}else{
	    				String a = stack.remove(stack.size()-1);
	    				String b = stack.remove(stack.size()-1);
	    				int result = Integer.parseInt(a)+Integer.parseInt(b);
	    				stack.add(Integer.toString(result));
	    			}
	    		} else if(ops[i].equals("-")){
	    			if(stack.size()<2){
	    				return err;
	    			}else{
	    				String a = stack.remove(stack.size()-1);
	    				String b = stack.remove(stack.size()-1);
	    				int result = Integer.parseInt(a)-Integer.parseInt(b);
	    				stack.add(Integer.toString(result));
	    			}
	    		}else{
	    			stack.add(ops[i]);
	    		}
	    	}
	    	if(stack.size()>0){
	    		return stack.get(stack.size()-1);  
	    	}else{
	    		return "null";
	    	}
	    }
	}


	@Test
	public void testNormalExample1() {
		SolutionT3 tester = new SolutionT3();
		String S = "13 DUP 5 POP 12 DUP 2 +"; //14
    	String result = tester.solution(S); 
    	assertEquals(result, "14");
	}
	
	@Test
	public void testNormalExample2() {
		SolutionT3 tester = new SolutionT3();
		String S = "13 DUP 5 POP 12 DUP 2 - DUP"; //-10
    	String result = tester.solution(S); 
    	assertEquals(result, "-10");
	}
	
	@Test
	public void testExtremeEmpty() {
		SolutionT3 tester = new SolutionT3();
		String S = ""; // null
    	String result = tester.solution(S); 
    	assertEquals(result, "null");
	}
	
	@Test
	public void testError() {
		SolutionT3 tester = new SolutionT3();
		String S = "5 6 + -"; // error
    	String result = tester.solution(S); 
    	assertEquals(result, "error");
	}
	
	@Test
	public void testExtremeSingle() {
		SolutionT3 tester = new SolutionT3();
		String S = "3"; // 3
    	String result = tester.solution(S); 
    	assertEquals(result, "3");
	}
	@Test
	public void testEmptyStack() {
		SolutionT3 tester = new SolutionT3();
		String S = "3 DUP POP POP"; // 3
    	String result = tester.solution(S); 
    	assertEquals(result, "null");
	}

}
