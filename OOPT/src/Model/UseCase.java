package Model;

public class UseCase {
	String Name;
	String Actor;
	String Purpose;
	String Overview;
	String Type;
	String Base;
	String related_requirement;
	String PreRequistes;
	String Typical;
	String Alternative;
	String Exceptional;
	
	public UseCase() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getBase() {
		return Base;
	}

	public void setBase(String base) {
		Base = base;
	}

	public String getRelated_requirement() {
		return related_requirement;
	}

	public void setRelated_requirement(String related_requirement) {
		this.related_requirement = related_requirement;
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
	
}
