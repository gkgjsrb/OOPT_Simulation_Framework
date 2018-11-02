package Model;

import com.horstmann.violet.product.diagram.abstracts.Id;

public class UseCase extends TextualRequirement{
	private Id Id;
	private String Actor;
	private String Description;
	private String Purpose;
	private String Overview;
	private String PreRequistes;
	private String Typical;
	private String Alternative;
	private String Exceptional;
	private String UI;
	
	public UseCase() {
		this.setName(" ");
		// TODO Auto-generated constructor stub
	}
	public Id getId() {
		return Id;
	}
	
	public void setId(Id id) {
		Id = id;
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
