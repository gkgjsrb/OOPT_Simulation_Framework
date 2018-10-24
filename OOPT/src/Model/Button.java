package Model;

import javax.swing.JButton;

public class Button extends JButton {

	private String Name;
	private Graph id;
	private int Id;
	public Button(String Name,int Objectcnt) {
		this.Name=Name;
		this.Id = Objectcnt;
		this.id=null;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public void setid(Graph id) {
		this.id = id;
	}
	
	public Graph getid() {
		return this.id;
	}
}
