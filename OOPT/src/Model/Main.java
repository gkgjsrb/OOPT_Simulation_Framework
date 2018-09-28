package Model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import view.GUI;

public class Main {

	public static void main(String[] args) throws SQLException  {
		// TODO Auto-generated method stub
		UMLEditorApplication u = new UMLEditorApplication();
		
		Mkdir md = new Mkdir();
		md.createVioletDirectory();
		
		Datainfo data = new Datainfo();
		data.open();
		
		Requirement req = new Requirement();
		ArrayList <Risk> risk = new ArrayList<>();
		ArrayList<Glossary> gl = new ArrayList<>();
		ArrayList <UseCase> uc = new ArrayList<>();
		ArrayList <SystemOperation> op = new ArrayList<>();
		
		ArrayList <Graph> std = new ArrayList<>();
		ArrayList <Graph> sd = new ArrayList<>();
		ArrayList <Graph> id = new ArrayList<>();
		Graph dm = new Graph();
		Graph ud = new Graph();
		Graph cd = new Graph();
		Graph sa = new Graph();
		
		
		
		GUI gui = new GUI(req, risk, gl, uc, op, std, sd, id, ud, cd, dm, sa, data);
		
		
		
	}
	
}
