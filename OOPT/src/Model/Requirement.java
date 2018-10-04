package Model;

import java.util.ArrayList;

public class Requirement {
	enum Category {EVIDENT, HIDDEN};
	enum uCategory {PRIMARY,SECONDARY, OPTIONAL};
	enum Rank {HIGH, MEDIUM, LOW};
	ArrayList<String> Ref = new ArrayList<String>();
	ArrayList<String> Name = new ArrayList<String>();
	ArrayList<String> Category = new ArrayList<String>();
	ArrayList<String> uCategory = new ArrayList<>();
	ArrayList<Integer> uNumber = new ArrayList<>();
	ArrayList<String> uName = new ArrayList<>();
	ArrayList<String> rank = new ArrayList<>();
	ArrayList<String> testcase = new ArrayList<>();
	
	public Requirement() {
		Ref.add(null);
		Name.add(null);
		Category.add(null);
		uCategory.add(null);
		uNumber.add(-1);
		uName.add(null);
		rank.add(null);
		testcase.add(null);
	}
	public void add_row() {
		Ref.add(null);
		Name.add(null);
		Category.add(null);
		uCategory.add(null);
		uNumber.add(-1);
		uName.add(null);
		rank.add(null);
		testcase.add(null);
	}
	public void del_row(int row) {
		Ref.remove(row);
		Name.remove(row);
		Category.remove(row);
		uCategory.remove(row);
		uNumber.remove(row);
		uName.remove(row);
		rank.remove(row);
		testcase.remove(row);
			
	}
	public void clear() {
		Ref.clear();
		Name.clear();
		Category.clear();
		uCategory.clear();
		uNumber.clear();
		uName.clear();
		rank.clear();
		testcase.clear();
	}
	public String getuCategory(int index) {
		return uCategory.get(index);
	}
	public void setuCategory(String ucategory, int index) {
		uCategory.set(index, ucategory);
	}
	public Integer getuNumber(int index) {
		return uNumber.get(index);
	}
	public void setuNumber(Integer unumber, int index) {
		uNumber.set(index, unumber);
	}
	public String getuName(int index) {
		return uName.get(index);
	}
	public void setuName(String uname, int index) {
		uName.set(index, uname);
	}
	public ArrayList getAlluName() {
		return this.uName;
	}
	public String getRank(int index) {
		return rank.get(index);
	}
	public void setRank(String rank, int index) {
		this.rank.set(index, rank);
	}
	public String getTestcase(int index) {
		return testcase.get(index);
	}
	public void setTestcase(String testcase, int index) {
		this.testcase.set(index, testcase);
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
