package algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

public class SortTest {
	private Sort s = new Sort();
	
	// test Q10.2
	@Test
	public void testSort() {
//		fail("Not yet implemented");
		String[] strs = {"abc", "cde", "bca", "xyz", "opq", "yzx"};
		s.sort(strs);
		for(int i=0;i<strs.length; i++){
			System.out.println(strs[i]);
		}
	}

}
