package dataStructure.topologicalSort;

import java.util.ArrayList;

public class Graph {
	public ArrayList<Project> nodes;
	
	public Graph(){
		nodes = new ArrayList<Project>();
	}
	
	// return the node given value (name)
	public Project getProject(String str){
		if(nodes!=null){
			for(Project n:nodes){
				if(n.getName()==str){
					return n;
				}
			}
		}
		return null;
	}
	public void addDependency(String a, String b){
		Project start =  getProject(a);
		Project end =  getProject(b);
		if(start==null){
			start = new Project(a);
			nodes.add(start);
		}
		if(end==null){
			end = new Project(b);
			nodes.add(end);
		}
		start.children.add(end);
	}
	public void addProject(String[] strs){
		for(String s : strs){
			Project node = getProject(s);
			if(node==null) {
				node = new Project(s);
				nodes.add(node);
			}		
		}
	}
	// 
//	public Graph(ArrayList<String[]> dependencies, ArrayList<String> projects){
//		for(String[] d : dependencies){
//			addDependency(d[0],d[1]);
//		}
//		for(String p : projects){
//			addProject(p);
//		}
//	}

	
	
}
