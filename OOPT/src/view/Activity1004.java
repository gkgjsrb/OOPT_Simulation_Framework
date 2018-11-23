package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.Glossary;
import Model.Requirement;
import Model.Risk;
import Model.StageText;

public class Activity1004 extends JTabbedPane {

	//glossray save in file
	DefaultTableModel model;
	public Activity1004(JTree tree, ArrayList<Glossary> gl, Datainfo data) {
		gl.add(new Glossary("D"));
		
		String[] colName= {"Term","Description","Remarks"};
		Object[][] rowData= {{"","",""}};
		model=new DefaultTableModel(rowData,colName);
		
		JTable table = new JTable(model);
		table.setCellSelectionEnabled(false);
		table.setRowHeight(70);

		table.getColumn("Term").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Term").setCellEditor(new TextAreaEditor());

		table.getColumn("Description").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Description").setCellEditor(new TextAreaEditor());

		table.getColumn("Remarks").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Remarks").setCellEditor(new TextAreaEditor());

		JSplitPane splitPane = new JSplitPane();
		JScrollPane panel = new JScrollPane(table);
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("+");
		JButton button_1 = new JButton("-");
		JButton button_2 = new JButton("Commit");
		
		jpanel.add(button);
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(panel, popupMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null,null,null};
				model.addRow(add);
				gl.add(new Glossary("D"));
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					gl.remove(row);
					data.syncTerm("D", gl.size());
					
					model.removeRow(row);
					table.editingCanceled(changeEvent);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		this.addTab("Record Terms in Glossary", null, splitPane, null);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null, null, null};
				model.addRow(add);
				gl.add(new Glossary("D"));
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					gl.remove(row);
					data.syncTerm("D", gl.size());
					
					model.removeRow(row);
					table.editingCanceled(changeEvent);
				}
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 3) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				table.editingStopped(changeEvent);
				for(int i = 0; i < table.getRowCount(); i++) {
					Glossary g = gl.get(i);
					g.setTerm((String)table.getValueAt(i, 0));
					g.setDescription((String)table.getValueAt(i, 1));
					g.setRemarks((String)table.getValueAt(i, 2));
					data.setTerm(i, g);
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public void save(Datainfo data, ArrayList<Glossary> gl) {
		for(Glossary g : gl) {
			data.setTerm(gl.indexOf(g), g);
		}
	}
	public void open(ArrayList<Glossary> gl) {
		
		model.setRowCount(0);
		for(Glossary g : gl) {
			Object[] add= {g.getTerm(), g.getDescription(), g.getRemarks()};
			
			model.addRow(add);
		}	
		
	}
	public void newActivity() {
		model.setRowCount(0);
		Object[] add= {"", "", ""};
		model.addRow(add);
		
	}
	

}
