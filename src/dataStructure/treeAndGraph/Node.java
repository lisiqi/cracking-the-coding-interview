package dataStructure.treeAndGraph;

import java.util.ArrayList;

public class Node<T> {
	public T name;
//	public Node[] adjacent;
	public ArrayList<Node> adjacent;
	public boolean visited;
	public boolean marked;
	
	public Node(T name){
		this.name = name;
		adjacent = new ArrayList<Node>();		
	}
	
	public void clearMarked(){
		marked = false;
	}
	public void clearVisited(){
		visited = false;
	}

}
