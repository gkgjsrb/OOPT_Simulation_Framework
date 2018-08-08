package Model;

import java.util.ArrayList;

public class Requirement {
	enum Category {EVIDENT, HIDDEN};
	ArrayList<String> Ref = new ArrayList<String>();
	ArrayList<String> Name = new ArrayList<String>();
	ArrayList<String> Category = new ArrayList<String>();
	ArrayList<String> Purpose = new ArrayList<String>();
	ArrayList<String> Overview = new ArrayList<String>();	
	ArrayList<String> Type = new ArrayList<String>();	
	ArrayList<String> CrossReference = new ArrayList<String>();
	ArrayList<String> PreRequistes = new ArrayList<String>();	
	ArrayList<String> Typical = new ArrayList<String>();
	ArrayList<String> Alternative = new ArrayList<String>();
	ArrayList<String> Exceptional = new ArrayList<String>();
	
	public Requirement() {
		Ref.add("");
		Name.add("");
		Category.add("");
		Purpose.add("");
		Overview.add("");
		Type.add("");
		CrossReference.add("");
		PreRequistes.add("");
		Typical.add("");
		Alternative.add("");
		Exceptional.add("");	


	}
	public void add_row() {
		Ref.add("");
		Name.add("");
		Category.add("");
		Purpose.add("");
		Overview.add("");
		Type.add("");
		CrossReference.add("");
		PreRequistes.add("");
		Typical.add("");
		Alternative.add("");
		Exceptional.add("");	
	}
	public void del_row(int row) {
		Ref.remove(row);
		Name.remove(row);
		Category.remove(row);
		Purpose.remove(row);
		Overview.remove(row);
		Type.remove(row);
		CrossReference.remove(row);
		PreRequistes.remove(row);
		Typical.remove(row);
		Alternative.remove(row);
		Exceptional.remove(row);		
	}
	public int get_length() {
		return Ref.size();
	}
	public String getRef(int index) {
		return Ref.get(index);
	}
	public void setRef(String ref, int index) {
		Ref.set(index, ref);
	}
	public ArrayList getAllName() {
		return this.Name;
	}
	public String getName(int index) {
		return Name.get(index);
	}
	public void setName(String name, int index) {
		Name.set(index, name);
	}
	public String getCategory(int index) {
		return Category.get(index);
	}
	public void setCategory(String category, int index) {
		Category.set(index, category);
	}
	
}
