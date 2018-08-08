package view;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import Model.Requirement;

import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Activity1003 extends JTabbedPane {
	public DefaultTableModel model;
	
	public Activity1003(Requirement req) {
		//DefaultTableModel model;
		String Category[] = {"EVIDENT","HIDDEN"};		
		String[] colName= {"Ref","Name","Category"};
		Object[][] rowData= {{req.getRef(0),req.getName(0),req.getCategory(0)}};
		
		model=new DefaultTableModel(null,colName);
		

		JSplitPane splitPane = new JSplitPane();
		
		JTable table = new JTable(model);
		
		//CELL 
		JComboBox<String> comboBox = new JComboBox<String>(Category);
		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		table.getColumnModel().getColumn(2).setCellEditor(Comboeditor);
		
		table.setRowHeight(70);

		table.getColumn("Ref").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Ref").setCellEditor(new TextAreaEditor(req, table));

	    table.getColumn("Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Name").setCellEditor(new TextAreaEditor(req, table));
	    
	    JPanel jpanel = new JPanel();
	    JScrollPane panel = new JScrollPane(table);

	    JButton add_btn = new JButton();
	    JButton del_btn = new JButton();
	    jpanel.add(add_btn);
	    jpanel.add(del_btn);
	    splitPane.setTopComponent(panel);
	    splitPane.setBottomComponent(jpanel);
	    add_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				req.add_row();
				Object[] add= {" "," "," "};
				model.addRow(add);	
			}
	    	
	    });
	    del_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				System.out.println(row);
				model.removeRow(row);
				req.del_row(row);
				table.editingCanceled(changeEvent);
				
			}
	    });
/*		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(panel, popupMenu);
		addPopup(table, popupMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				req.add_row();
				Object[] add= {" "," "," "};
				model.addRow(add);				
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					req.del_row(row);
					model.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		*/
		this.addTab("Requirements", null, splitPane, null);
		
		JScrollPane ScrollPane = new JScrollPane();
		this.addTab("Operating Environment", null, ScrollPane, null);
		
		JTextPane textPane = new JTextPane();
		ScrollPane.setViewportView(textPane);
		
		JLabel lblNewLabel = new JLabel("<html>example(Library Management System)<br>"
	            + "- Microsoft Windows 7 and 10<br>"
	            + "</html>");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		
		ScrollPane.setColumnHeaderView(lblNewLabel);
				
		JScrollPane ScrollPane_1 = new JScrollPane();
		this.addTab("Develop Environment", null, ScrollPane_1, null);
		
		JTextPane textPane_1 = new JTextPane();
		ScrollPane_1.setViewportView(textPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("<html>example(Library Management System)<br>"
	            + "- CPU : Intel<br>"
	            + "- IDE : Eclipse<br>"
	            + "- Language : Java<br>"
	            + "- UML : StarUML<br>"
	            + "</html>");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		
		ScrollPane_1.setColumnHeaderView(lblNewLabel_1);
		
		JScrollPane ScrollPane_2 = new JScrollPane();
		this.addTab("Interface Requirements", null, ScrollPane_2, null);
		
		JTextPane textPane_2 = new JTextPane();
		ScrollPane_2.setViewportView(textPane_2);
		
		JLabel lblNewLabel_2 = new JLabel("<html>example(Library Management System)<br>"
	            + "- The current version may incorporate a menu-driven approach<br>"
	            + "- Next version incorporates windows metaphor<br>"
	            + "</html>");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		
		ScrollPane_2.setColumnHeaderView(lblNewLabel_2);
		
		JScrollPane ScrollPane_3 = new JScrollPane();
		this.addTab("Other Requirements", null, ScrollPane_3, null);
		
		JTextPane textPane_3 = new JTextPane();
		ScrollPane_3.setViewportView(textPane_3);
		
		JLabel lblNewLabel_3 = new JLabel("<html>example(Library Management System)<br>"
	            + "- The System must control the system access<br>"
	            + "</html>");
		ScrollPane_3.setColumnHeaderView(lblNewLabel_3);
	}
	/*
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
	*/
	public void syncRequirement(Requirement req) {
		model.setRowCount(0);
		for(int i = 0; i < req.get_length();i++) {
			Object[] add = {req.getRef(i), req.getName(i), req.getCategory(i)};
			model.addRow(add);
		}
	}
 }
