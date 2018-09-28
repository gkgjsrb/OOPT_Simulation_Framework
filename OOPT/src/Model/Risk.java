package Model;

import java.util.ArrayList;

public class Risk {
	private String name;	
	private int probability;
	private int significance;
	private int weight;
	private String plan;
	

	public Risk() {
		super();
		this.probability = 1;
		this.significance = 1;
		this.weight = 1;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public int getSignificance() {
		return significance;
	}

	public void setSignificance(int significance) {
		this.significance = significance;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight() {
		this.weight = this.probability * this.significance;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

		
}
