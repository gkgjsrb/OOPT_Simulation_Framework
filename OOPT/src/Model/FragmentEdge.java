package Model;

import java.util.ArrayList;

import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;

public class FragmentEdge {
	private ArrayList<IEdge> list;
	private String type;
	private String contents;
	public FragmentEdge() {
		
	}
	public ArrayList<IEdge> getList() {
		return list;
	}
	public void setList(ArrayList<IEdge> list) {
		this.list = list;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
}
