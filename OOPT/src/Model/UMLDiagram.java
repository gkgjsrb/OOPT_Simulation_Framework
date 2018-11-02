package Model;

import java.io.Serializable;

import com.horstmann.violet.framework.file.IGraphFile;

public class UMLDiagram extends DevelopmentArtifact implements Serializable{
	
	private String id;
	
	IGraphFile graph;
	
	public UMLDiagram() {
		
		// TODO Auto-generated constructor stub
	}
	public UMLDiagram(String name) {
	
		this.setName(name);
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
