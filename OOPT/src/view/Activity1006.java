package view;

import java.io.IOException;

import javax.swing.JTabbedPane;
import javax.swing.JTree;

import Model.UMLEditorApplication;

public class Activity1006 extends JTabbedPane {

	
	public Activity1006(JTree tree, String[] args) throws IOException {
		
		UMLEditorApplication uml = new UMLEditorApplication(args,this);
		JTabbedPane panel = new JTabbedPane();
		addTab("Use Case", null, panel, null);

	}
	
}
