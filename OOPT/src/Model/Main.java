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
		ArrayList <SystemOperation> op = new ArrayList<SystemOperation>();
		ArrayList <Graph> std = new ArrayList<Graph>();
		ArrayList <Graph> sd = new ArrayList<Graph>();
		ArrayList <Graph> id = new ArrayList<Graph>();
		Graph dm = new Graph();
		Graph ud = new Graph();
		Graph cd = new Graph();
		Graph sa = new Graph();
		
		GUI gui = new GUI(req, risk, uc, op, std, sd, id, ud, cd, dm, sa);
		
	}
	
}
