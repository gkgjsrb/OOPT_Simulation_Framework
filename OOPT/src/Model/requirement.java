package Model;

public class requirement {
	enum Category {EVIDENT, HIDDEN};
	String Ref;
	String Name;
	Category Category;
	
	public requirement(String Ref,String Name,Category Category) {
		super();
		// TODO Auto-generated constructor stub
		this.Ref=Ref;
		this.Name=Name;
		this.Category=Category;
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
}
