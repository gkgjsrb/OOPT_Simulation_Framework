package Model;

import javax.swing.JButton;

public class Button extends JButton {

	private Graph id;
	private int Id;
	public Button(int Objectcnt) {
		this.Id = Objectcnt;
		this.id=null;
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
