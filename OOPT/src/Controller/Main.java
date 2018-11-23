package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Datainfo;
import Model.Glossary;
import Model.Mkdir;
import Model.Requirement;
import Model.Risk;
import Model.SystemOperation;
import Model.UMLDiagram;
import Model.UMLEditorApplication;
import Model.UseCase;
import view.GUI;

public class Main {

	public static void main(String[] args) throws SQLException, IOException  {
		// TODO Auto-generated method stub
		UMLEditorApplication u = new UMLEditorApplication();
		
		Mkdir md = new Mkdir();
		md.createVioletDirectory();
		md.initalDirectory();
		String initdb = "." + File.separator + "clean db" + File.separator + "db.sqlite";
		String newdb = "." + File.separator + "init" + File.separator + "db.sqlite";
		
		Files.copy(new File(initdb).toPath(), new File(newdb).toPath());
		Datainfo data = new Datainfo();
		data.open(newdb);
		
		Requirement req = new Requirement();
		ArrayList <Risk> risk = new ArrayList<>();
		ArrayList<Glossary> gl = new ArrayList<>();
		ArrayList <UseCase> uc = new ArrayList<>();
		ArrayList <SystemOperation> op = new ArrayList<>();
		
		ArrayList <UMLDiagram> std = new ArrayList<>();
		ArrayList <UMLDiagram> sd = new ArrayList<>();
		ArrayList <UMLDiagram> id = new ArrayList<>();
		UMLDiagram dm = new UMLDiagram();
		UMLDiagram ud = new UMLDiagram();
		UMLDiagram cd = new UMLDiagram();
		UMLDiagram sa = new UMLDiagram();
		UMLDiagram dsa = new UMLDiagram();
		UMLDiagram sb = new UMLDiagram();
		GUI gui = new GUI(req, risk, gl, uc, op, std, sd, id, sb, ud, cd, dm, sa, dsa, data);
		
		
		
	}
	
}
