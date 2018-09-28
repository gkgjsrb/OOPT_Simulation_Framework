package Model;

public class Glossary {
	private String type;
	private String term;
	private String description;
	private String remarks;
	private String category;
	public Glossary() {
		
	}
	public Glossary(String type) {
		if (type.equals("D")) {
			this.type = "D";
		}
		else {
			this.type = "R";
		}
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
