package dataStructure.treeAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;

public class Main {

	/* Q 4.2 Minimal Tree: 
	 * Given a sorted (increasing order) array with unique integer elements, write an algo-
rithm to create a binary search tree with minimal height.*/
	// use median as root to make the tree complete tree
	public static BinaryTreeNode createBinarySearchTree(int[] arr, int left, int right){
		if(left>right) { //!!!!!!!
			return null;
		}
		int mid = (left+right)/2;
		BinaryTreeNode root = new BinaryTreeNode(arr[mid]);
		root.left = createBinarySearchTree(arr, left, mid-1);
		root.right = createBinarySearchTree(arr, mid+1, right);
		return root;
	}
	
	/*Q 4.3 List of Depths: 
	 * Given a binary tree, design an algorithm which creates a linked list of 
	 * all the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).*/
	// modification of breadth-first-search
	public static ArrayList<LinkedList<BinaryTreeNode>> listOfDepth(BinaryTreeNode root){
		ArrayList<LinkedList<BinaryTreeNode>> result = new ArrayList<LinkedList<BinaryTreeNode>>();
		LinkedList<BinaryTreeNode> current = new LinkedList<BinaryTreeNode>(); // store nodes in current level
		current.add(root);
		
		while(!current.isEmpty()){
			result.add(current); // add nodes in previous level
			LinkedList<BinaryTreeNode> parents = new LinkedList<BinaryTreeNode>();
			parents = current;
			current = new LinkedList<BinaryTreeNode>();
			for(BinaryTreeNode parent:parents){
				if(parent.left!=null){
					current.add(parent.left);
				}
				if(parent.right!=null){
					current.add(parent.right);
				}
			}		
		}
		return result;
	}
	
    /*Q4.4 Check Balanced: 
     * Implement a function to check if a binary tree is balanced. 
     * For the purposes of this question, a balanced tree is defined to be 
     * a tree such that the heights of the two subtrees of any node never differ by more than one.*/
	
	// Method 1:
	// breadth-first-search: count each level 
	// can simply return the result.size() from last question
	// run in O(N*N)
	
//	public static int height(BinaryTreeNode root){
//		ArrayList<LinkedList<BinaryTreeNode>> result = new ArrayList<LinkedList<BinaryTreeNode>>();
//		LinkedList<BinaryTreeNode> current = new LinkedList<BinaryTreeNode>(); // store nodes in current level
//		current.add(root);
//		
//		while(!current.isEmpty()){
//			result.add(current); // add nodes in previous level
//			LinkedList<BinaryTreeNode> parents = new LinkedList<BinaryTreeNode>();
//			parents = current;
//			current = new LinkedList<BinaryTreeNode>();
//			for(BinaryTreeNode parent:parents){
//				if(parent.left!=null){
//					current.add(parent.left);
//				}
//				if(parent.right!=null){
//					current.add(parent.right);
//				}
//			}		
//		}
//		return result.size();
//	}
//	//check one node
//	public static boolean oneNodeCheck(BinaryTreeNode root){
//		if(root==null){
//			return true;
//		}
//		int leftHeight;
//		if(root.left==null){
//			leftHeight = 0;
//		}else{
//			leftHeight = height(root.left);
//		}
//		int rightHeight;
//		if(root.right==null){
//			rightHeight = 0;
//		}else{
//			rightHeight = height(root.right);
//		}
//		return (Math.abs(leftHeight - rightHeight)<=1);
//	}
//	//check each node // pre-order-traversal
//	public static boolean isBalanced(BinaryTreeNode root){	
//		if(!oneNodeCheck(root)){
//			return false;
//		}
//		if(root.left!=null){
//			return isBalanced(root.left); // !!!! return
//		}
//		if(root.right!=null){
//			return isBalanced(root.right);
//		}
//		return true;
//	}
	
	// Q4.4 Method 2
	// runtime O(N); space O(H)
	public static int checkHeight(BinaryTreeNode n){
		if(n==null) return -1; // The height of a null tree is generally defined as -1
		
		//Integer.MIN_VALUE is the error code
		int leftHeight = checkHeight(n.left);
		if(leftHeight==Integer.MIN_VALUE) return Integer.MIN_VALUE;
		
		int rightHeight = checkHeight(n.right);
		if(rightHeight==Integer.MIN_VALUE) return Integer.MIN_VALUE;
		
		if(Math.abs(leftHeight-rightHeight)>1){
			return Integer.MIN_VALUE;
		}else{
			return Math.max(leftHeight,rightHeight)+1;
		}
	}
	public static boolean isBalanced(BinaryTreeNode root){
		return (checkHeight(root)!=Integer.MIN_VALUE);
	}
	
	/*Q4.5 Validate BST: 
	 * Implement a function to check if a binary tree is a binary search tree.*/
	// Method 1: in-order-traversal
	// store last_printed and compare with n.name
	
	// Method 2:
	// for each node n: max(left-tree) < n < min(right-tree)
	// max(left-tree) ==> most right child
	// min(right-tree) ==> most left child
	// runtime O(N)
	public static boolean checkNode(BinaryTreeNode root){
		// if null
		if(root == null){
			return true;
		}
		// get most right child from left subtree
		int maxLeft; 
		if(root.left == null) {
			maxLeft = Integer.MIN_VALUE;
		}else{
			BinaryTreeNode mostRight = root.left;
			while(mostRight.right!=null){
				mostRight = mostRight.right;
			}
			maxLeft = mostRight.name;
		}
		
		// get most left child from right subtree
		int minRight; 
		if(root.right == null) {
			minRight = Integer.MAX_VALUE;
		}else{
			BinaryTreeNode mostLeft = root.right;
			while(mostLeft.left!=null){
				mostLeft = mostLeft.left;
			}
			minRight = mostLeft.name;
		}
		
		if(maxLeft <= root.name && root.name < minRight){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isBST(BinaryTreeNode root){
		// any order traversal
		if(!checkNode(root)) return false;
		if(!checkNode(root.left)) return false;
		if(!checkNode(root.right)) return false;
		return true;
	}
	
	// Method 3: recursive method
	// maintain the range (min,max]
	// start from (null,null), ture for the root
	// for left subtree, all the node should be in range (min, n.name]
	// for right subtree, all the node should be in range (n.name, max)
	// runtime:O(N) space:O(logN) for balanced tree
	public static boolean checkBST(BinaryTreeNode n, Integer min, Integer max){
		if(n==null) return true;
		
		// check current
		if(max!=null && n.name>max || min!=null && n.name<=min){
			return false;
		}
		// check left and right
		if(!checkBST(n.left, min, n.name) || !checkBST(n.right, n.name, max)){ //!!!!
			return false;
		}
		return true;
	}
	public static boolean checkBST(BinaryTreeNode n){
		return checkBST(n, null, null);
	}
	
	/* Q4.6 Successor: Write an algorithm to find the "next" node (i.e., in-order successor) 
	 * of a given node in a binary search tree. 
	 * You may assume that each node has a link to its parent.*/
	public static BinaryTreeNode inOrderSucc(BinaryTreeNode n){
		if(n==null) return null;
		
		if(n.right!=null){
		    return leftMost(n.right);
		}
		// if n.right==null, go up parent
		BinaryTreeNode x = n;
		BinaryTreeNode p = x.parent;
		while(p.right==x && p != null){
			x = p;
			p = p.parent;
		}
		return p;
	}
	private static BinaryTreeNode leftMost(BinaryTreeNode n){ // pass n.right 
		if(n==null) return null;
		while(n.left!=null){
			n=n.left;
		}
		return n;
	}
	
	public static void main(String[] args) {
		
		/*4.2*/
		int[] arr = {0,1,2,3,4,5,6,7,8,9};
		BinaryTreeNode root = createBinarySearchTree(arr, 0, arr.length-1);
		root.print();
		
		/*4.3*/
		System.out.println("Results for Q4.3");
		ArrayList<LinkedList<BinaryTreeNode>> result = listOfDepth(root);
		for(LinkedList<BinaryTreeNode> list:result){
			for(BinaryTreeNode n:list){
				System.out.print(n.name);
			}
			System.out.println("\n");
		}
		
		/*4.4 & 4.5*/
		System.out.println("Check Balanced:" + isBalanced(root)); //ture
		System.out.println("Check Binary search tree:" + isBST(root)); //ture
		System.out.println("Check Binary search tree: Method 3:" + checkBST(root)); //ture
		//test the unbalanced 
		// add right child of the last 9
		BinaryTreeNode node = new BinaryTreeNode(root); // root still got changed 
//		BinaryTreeNode node = root;
		node.right.right.right.right = new BinaryTreeNode(10);
		node.right.right.right.right.right = new BinaryTreeNode(11);
		node.right.right.right.right.right.right = new BinaryTreeNode(12);
		System.out.println("Check Balanced:" + isBalanced(node)); //false
		System.out.println("Check Binary search tree:" + isBST(node)); //ture
		System.out.println("Check Binary search tree: Method 3:" + checkBST(node)); //ture
		// if only check root, true.
		BinaryTreeNode n2 = new BinaryTreeNode(node); 
//		BinaryTreeNode n2 = node;
		n2.left.right.right.right = new BinaryTreeNode(31);
		n2.left.right.right.right.right = new BinaryTreeNode(32);
//		System.out.println("One Node check:" + oneNodeCheck(n2));
		System.out.println("Check Balanced:" + isBalanced(n2)); // false
		System.out.println("Check Binary search tree:" + isBST(n2)); //false
		System.out.println("Check Binary search tree: Method 3:" + checkBST(n2)); //false
		
		/*4.6*/
		BinaryTreeNode r6 = createBinarySearchTree(arr, 0, arr.length-1);
		r6.left.left.parent = r6.left;
		r6.left.parent = r6;
		r6.left.right.right.parent = r6.left.right;
		r6.left.right.parent = r6.left;
		r6.right.left.right.parent = r6.right.left;
		r6.right.left.parent = r6.right;
		r6.right.right.right.parent = r6.right.right;
		r6.right.right.parent = r6.right;
		r6.right.parent = r6;
		
		System.out.println("next of 4:"+inOrderSucc(r6).name);
		System.out.println("next of 0:"+inOrderSucc(r6.left.left).name);
		System.out.println("next of 6:"+inOrderSucc(r6.right.left.right).name);
//		System.out.println("next of 9:"+inOrderSucc(r6.right.right.right).name);

	}

}
