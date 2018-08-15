package view;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.product.diagram.abstracts.property.MultiLineString;
import com.horstmann.violet.product.diagram.usecase.UseCaseNode;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphTool;

import Model.UMLEditorApplication;



public class Activity1006 extends JTabbedPane {
	//private JTable table;
	

	public Activity1006(JTree tree, ArrayList uc, String[] args) {
		
		JScrollPane scrollPane = new JScrollPane();
		addTab("Define System Boundary", null, scrollPane, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		addTab("Identify and Describe Actors", null, scrollPane_1, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		addTab("Identify Use-Case", null, scrollPane_2, null);

        
		JScrollPane scrollPane_3 = new JScrollPane();
		addTab("Allocate System Functions into Related Use-Cases", null, scrollPane_3, null);

		JScrollPane scrollPane_4 = new JScrollPane();
		addTab("Categorize Use-Cases", null, scrollPane_4, null);

		JScrollPane scrollPane_5 = new JScrollPane();
		addTab("Identify Relationsships between Use-Cases", null, scrollPane_5, null);

		UMLEditorApplication uml = new UMLEditorApplication(args, this);
		
		JTabbedPane panel = new JTabbedPane();
		addTab("Describe Use-Cases", null, panel, null);
		JScrollPane usecasePane = new JScrollPane();
		panel.addTab("Use Case1", null, usecasePane, null);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Name", null},
				{"Actor", null},
				{"Description", null}
			},
			new String[] {
				"", " "
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		usecasePane.setViewportView(table);
		table.setRowHeight(45);
		
		table.getColumn(" ").setCellRenderer(new TextAreaRenderer());
	    table.getColumn(" ").setCellEditor(new TextAreaEditor(null, table, uc, panel));
		
		JScrollPane scrollPane_7 = new JScrollPane();
		addTab("Rank Use-Cases", null, scrollPane_7, null);
		
	}



}
