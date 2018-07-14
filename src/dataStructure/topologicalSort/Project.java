package dataStructure.topologicalSort;

import java.util.ArrayList;

// Project is the node of Graph
public class Project {
	private String name;
	public ArrayList<Project> children;
	public int inbound; // number of incoming edges
	
	public Project(String name){
		this.name = name;
		children = new ArrayList<Project>();
	}
	
	public String getName(){
		return this.name;
	}

}
