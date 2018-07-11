package dataStructure.linkedList;

// Singly liked list
public class Node {
	int data;
	Node next = null;
	
	public Node(int d){
		data = d;
	}
	
	public void appendToTail(int d){
		Node end = new Node(d);
		Node n = this;
		while(n.next != null)
			n = n.next;
		n.next = end;	
	}
	
}
