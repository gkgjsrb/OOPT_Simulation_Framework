package Model;

import com.horstmann.violet.product.diagram.abstracts.Id;

public class UseCase {
	Id Id;
	String Name;
	String Actor;
	String Description;
	String Purpose;
	String Overview;
	String Type;
	//String Base;
	String Cross;
	//String related_requirement;
	String PreRequistes;
	String Typical;
	String Alternative;
	String Exceptional;
	String UI;
	
	public UseCase() {
		super();
		this.Name = " ";
		// TODO Auto-generated constructor stub
	}
	public Id getId() {
		return Id;
	}
	
	public void setId(Id id) {
		Id = id;
	}
	public String getCross() {
		return Cross;
	}
	public void setCross(String cross) {
		Cross = cross;
	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	public String getActor() {
		return Actor;
	}

	public void setActor(String actor) {
		Actor = actor;
	}
	public String getDes() {
		return Description;
	}
	
	public void setDes(String description) {
		Description=description;
	}
	
	public String getPurpose() {
		return Purpose;
	}
	public void setPurpose(String purpose) {
		Purpose = purpose;
	}
	public String getOverview() {
		return Overview;
	}
	public void setOverview(String overview) {
		Overview = overview;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	public String getPreRequistes() {
		return PreRequistes;
	}
	public void setPreRequistes(String preRequistes) {
		PreRequistes = preRequistes;
	}
	public String getTypical() {
		return Typical;
	}
	public void setTypical(String typical) {
		Typical = typical;
	}
	public String getAlternative() {
		return Alternative;
	}
	public void setAlternative(String alternative) {
		Alternative = alternative;
	}
	public String getExceptional() {
		return Exceptional;
	}
	public void setExceptional(String exceptional) {
		Exceptional = exceptional;
	}
	public String getUI() {
		return UI;
	}
	public void setUI(String ui) {
		UI = ui;
	}


	
}
