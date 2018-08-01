package Model;

import java.util.ArrayList;

import view.GUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requirement req = new Requirement();
		Risk risk = new Risk();
		ArrayList <Requirement> a = new ArrayList<>();
		ArrayList <UseCase> uc = new ArrayList<>();
		//a.add(req);
		GUI gui = new GUI(req, risk, uc);
	}

}
