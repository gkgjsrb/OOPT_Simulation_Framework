package Model;

import java.io.Serializable;

import com.horstmann.violet.framework.file.IGraphFile;

public class Graph implements Serializable{
	String name;
	String id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IGraphFile getGraph() {
		return graph;
	}
	public void setGraph(IGraphFile graph) {
		this.graph = graph;
	}
}
