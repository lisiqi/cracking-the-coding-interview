package dataStructure.treeAndGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	public ArrayList<Node> nodes;
	
	public Graph(){
		nodes = new ArrayList<Node>();
	}
	
	public void depthFirstSearch(Node root){
		if(root == null) return;
		visit(root);
		root.visited = true;
		Iterator<Node> iterator = root.adjacent.iterator();
		while(iterator.hasNext()){
			Node n = iterator.next();
			if(n.visited == false){
				depthFirstSearch(n);
			}
		}
		
	}
	
	public void breadthFirstSearch(Node root){
		// operates as Queue
		Queue<Node> queue = new LinkedList<Node>();
		root.marked = true;
		queue.add(root);
		
		while(!queue.isEmpty()){
			Node r = queue.remove();
			visit(r);
			Iterator<Node> iterator = r.adjacent.iterator();
			while(iterator.hasNext()){
				Node n = iterator.next();
				if(n.marked == false){
					queue.add(n);
					n.marked = true;
				}
			}
		}
	}
	
	private void visit(Node n){
		System.out.println("visit " + n.name);
	}
	
	public void clearMarked(){
		for(Node n:nodes){
			n.clearMarked();
		}
	}
	public void clearVisited(){
		for(Node n:nodes){
			n.clearVisited();
		}
	}

	/* Q 4.1
	 * Route Between Nodes: 
	 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
	 * */
	
	// modification of BFS
	public boolean findRoute(Node n1, Node n2){
		Queue<Node> queue = new LinkedList<Node>();
		n1.marked = true;
		queue.add(n1);
		
		while(!queue.isEmpty()){
			Node n = queue.remove();
			// compare
			if(n == n2){
				return true;
			}
			Iterator<Node> iterator = n.adjacent.iterator();
			while(iterator.hasNext()){
				Node i = iterator.next();
				if(i.marked ==false){
					i.marked = true;
					queue.add(i);
				}
			}
		}
		return false;
	}
	
	public static void main(String arg[]){
		// example in page 119
		ArrayList<Node<Integer>> n = new ArrayList<Node<Integer>>();
		for(int i=0;i<=5;i++){
			n.add(i,new Node<Integer>(i));
		}
				
		n.get(0).adjacent.add(n.get(1));
		n.get(0).adjacent.add(n.get(4));
		n.get(0).adjacent.add(n.get(5));
		n.get(1).adjacent.add(n.get(3));
		n.get(1).adjacent.add(n.get(4));
		n.get(2).adjacent.add(n.get(1));
		n.get(3).adjacent.add(n.get(2));
		n.get(3).adjacent.add(n.get(4));

		Graph g = new Graph();
		g.nodes.addAll(n);
		System.out.println("Depth-First-Search:");
		g.depthFirstSearch(n.get(0));
		g.clearVisited();
		System.out.println("Breadth-First-Search:");
		g.breadthFirstSearch(n.get(0));
		g.clearMarked();
		System.out.println(g.findRoute(n.get(0),n.get(3))); // true
		g.clearMarked();
		System.out.println(g.findRoute(n.get(3),n.get(0))); // false
		g.clearMarked();
		System.out.println(g.findRoute(n.get(2),n.get(4))); // true
		g.clearMarked();
		System.out.println(g.findRoute(n.get(4),n.get(2))); //false
		g.clearMarked();
		
		
	}
}
