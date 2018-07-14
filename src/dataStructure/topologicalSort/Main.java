package dataStructure.topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*Q 4.7 Build Order: 
 * You are given a list of projects and a list of dependencies 
 * (which is a list of pairs of projects, where the second project is dependent on the first project). 
 * All of a project's dependencies must be built before the project is. 
 * Find a build order that will allow the projects to be built. 
 * If there is no valid build order, return an error.
 * EXAMPLE
 * Input:
 * 		projects: a, b, c, d, e, f
 * 		dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 * Output: f, e, a, b, d, c
 * */

// NOTE: All of a project's dependencies must be built before the project is. 
// Topological Sort of DAG (directed acyclic graph)

public class Main {

	// need to modify
	public static Queue<Project> topologicalSort(Graph g){
		Queue<Project> order = new LinkedList<Project>(); // store the valid order
		Queue<Project> processNext = new LinkedList<Project>(); // the processing queue
//		ArrayList<Project> nodes = new ArrayList<Project>(); // store nodes whose inbound = 0
		
		// calculate each node's incoming number
		for(Project n : g.nodes){
			for(Project x : n.children){
				x.inbound++;
			}
		}
		
		for(Project n : g.nodes){
			if(n.inbound ==0){
				processNext.add(n);
//				nodes.add(n); //
			}
		}
//		for(Project n : nodes){
//			if(projects.contains(n.getName())){
//				order.add(n);
//			}else {
//				return null; // ERROR
//			}
//		}
		while(!processNext.isEmpty()){
			Project n = processNext.remove();
			for(Project x : n.children){
				x.inbound--;
				if(x.inbound==0){
					processNext.add(x);
				}
			}
//			if(projects.contains(n.getName())){
			order.add(n);
//			}
		}
		// if there is cycle, the topological sort will not include all nodes
		if(order.size()!=g.nodes.size()){
			return null;
		}else{
			return order;
		}
	}
	public static void main(String[] args) {
//		Graph g = new Graph();
//		g.addDependency("a","d");
//		g.addDependency("f","b");
//		g.addDependency("b","d");
//		g.addDependency("f","a");
//		g.addDependency("d","c");
//		String[] projects = {"a", "b", "c", "d", "e", "f"};
//		g.addProject(projects);
//		// graph created successfully
//
//		Queue<Project> order = topologicalSort(g);
//		if(order!=null){
//			for(Project p : order){
//				System.out.println(p.getName());
//			}
//		}
		
		// example 2
		Graph g = new Graph();
		g.addDependency("f","a");
		g.addDependency("f","b");
		g.addDependency("f","c");
		g.addDependency("c","a");
		g.addDependency("b","a");
		g.addDependency("a","e");
		g.addDependency("b","e");
		g.addDependency("d","g");
//		String[] projects = {"a", "b", "c", "d", "e", "f"};
//		g.addProject(projects);
		// graph created successfully

		Queue<Project> order = topologicalSort(g);
		if(order!=null){
			for(Project p : order){
				System.out.println(p.getName());
			}
		}

	}

}
