package Model;

public class UseCase {
	enum base {actor,event};
	enum Category {primary,secondary};
	String Actor;
	String Ref;
	String Name;
	Category Category;
	int Num;
	base Base;
	Requirement related_requirement;
	
	public UseCase(String Name) {
		super();
		// TODO Auto-generated constructor stub
		this.Name=Name;
	}

	public String getActor() {
		return Actor;
	}

	public void setActor(String actor) {
		Actor = actor;
	}

	public String getRef() {
		return Ref;
	}

	public void setRef(String ref) {
		Ref = ref;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public base getBase() {
		return Base;
	}

	public void setBase(base base) {
		Base = base;
	}

	public requirement getRelated_requirement() {
		return related_requirement;
	}

	public void setRelated_requirement(requirement related_requirement) {
		this.related_requirement = related_requirement;
	}
	
}
