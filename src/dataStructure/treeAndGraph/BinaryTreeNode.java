package dataStructure.treeAndGraph;

public class BinaryTreeNode {
	public int name;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	public boolean marked; // used in breadth-first-search
	
	public BinaryTreeNode parent;

	public BinaryTreeNode(int name){
		this.name = name;	
	}
	public BinaryTreeNode(BinaryTreeNode node){ // made from copy
		this.name = node.name;	
		this.left = node.left;
		this.right = node.right;
		this.marked = node.marked;
	}
	
	public void print(){
		if(left != null) left.print();
		System.out.println(name);
		if(right != null) right.print();
	}
	
}
