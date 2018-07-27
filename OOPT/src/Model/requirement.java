package Model;

import java.util.ArrayList;

public class requirement {
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
	
	public requirement() {
		Ref.add("test");
		Name.add("test");
		Category.add("test");
		Purpose.add(null);
		Overview.add(null);
		Type.add(null);
		CrossReference.add(null);
		PreRequistes.add(null);
		Typical.add(null);
		Alternative.add(null);
		Exceptional.add(null);	

	}
	public void add_row() {
		Ref.add(null);
		Name.add(null);
		Category.add(null);
		Purpose.add(null);
		Overview.add(null);
		Type.add(null);
		CrossReference.add(null);
		PreRequistes.add(null);
		Typical.add(null);
		Alternative.add(null);
		Exceptional.add(null);
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
