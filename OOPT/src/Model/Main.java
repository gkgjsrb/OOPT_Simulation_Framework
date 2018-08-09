package Model;

import java.util.ArrayList;

import view.GUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requirement req = new Requirement();
		Risk risk = new Risk();
		ArrayList <UseCase> uc = new ArrayList<>();
		
		GUI gui = new GUI(req, risk, uc);
		
	}

}
