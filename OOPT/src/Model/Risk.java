package Model;

import java.util.ArrayList;

public class Risk {
	ArrayList<String> Name = new ArrayList<String>();
	ArrayList<Integer> Probability = new ArrayList<Integer>();
	ArrayList<Integer> Significance = new ArrayList<Integer>();	
	ArrayList<Integer> Weight = new ArrayList<Integer>();	
	ArrayList<String> Plan = new ArrayList<String>();

	public Risk() {
		Name.add("");
		Probability.add(1);
		Significance.add(1);
		Weight.add(1);
		Plan.add("");
	}
	public void add_row() {
		Name.add("");
		Probability.add(1);
		Significance.add(1);
		Weight.add(1);
		Plan.add("");
	}
	
	public void del_row(int row) {
		Name.remove(row);
		Probability.remove(row);
		Significance.remove(row);
		Weight.remove(row);
		Plan.remove(row);
	}
	public int get_length() {
		return Name.size();
	}
	public String getName(int index) {
		return Name.get(index);
	}
	public void setName(String name, int index) {
		Name.set(index, name);
	}
	public int getPro(int index) {
		return Probability.get(index);
	}
	public void setPro(int pro, int index) {
		Probability.set(index, pro);
	}
	public int getSig(int index) {
		return Significance.get(index);
	}
	public void setSig(int sig, int index) {
		Significance.set(index, sig);
	}
	public int getWeight(int index) {
		return Weight.get(index);
	}
	public void setWeight(int pro, int sig, int index) {
		Weight.set(index, pro*sig);
	}
	public String getPlan(int index) {
		return Plan.get(index);
	}
	public void setPlan(String plan, int index) {
		Plan.set(index, plan);
	}
	
}
