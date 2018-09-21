package Model;

import com.horstmann.violet.framework.file.IGraphFile;

public class Graph {
	String name;
	IGraphFile graph;
	
	public Graph() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Graph(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IGraphFile getGraph() {
		return graph;
	}
	public void setGraph(IGraphFile graph) {
		this.graph = graph;
	}
}
