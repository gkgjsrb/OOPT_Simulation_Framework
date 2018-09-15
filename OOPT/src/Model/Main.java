package Model;

import java.util.ArrayList;

import view.GUI;

public class Main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Mkdir md = new Mkdir();
		md.createVioletDirectory();
		
		Requirement req = new Requirement();
		Risk risk = new Risk();
		ArrayList <UseCase> uc = new ArrayList<>();
		ArrayList <SystemOperation> op = new ArrayList();
		GUI gui = new GUI(req, risk, uc, op);
		
	}
	
}
