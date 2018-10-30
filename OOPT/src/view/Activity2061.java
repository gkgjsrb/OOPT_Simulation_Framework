package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.NonFuncReq;
import Model.StageText;
import Model.SystemTestCase;
import Model.UnitTestCase;


public class Activity2061 extends JTabbedPane {

	/**
	 * Create the panel.
	 */
	JTextPane textPane;
	DefaultTableModel model;
	public Activity2061(JTree tree, ArrayList<UnitTestCase> utc, Datainfo data) {
		JSplitPane splitPane = new JSplitPane();
		textPane = new JTextPane();
		JScrollPane panel = new JScrollPane();
		panel.setViewportView(textPane);
		GridBagConstraints c = new GridBagConstraints();
		JPanel jpanel = new JPanel(new GridBagLayout());
		
		JButton button = new JButton("Commit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		jpanel.add(button, c);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JLabel label = new JLabel("<html>example(Test Environment)<br>"
	            + "- Test Team : T1<br>"
	            + "- Date : 2018/10/18<br>"
	            + "- OS : Windows 10 (64 bit)<br>"
	            + "- Test 제외 항목<br>"
	            + "&nbsp;&nbsp;&nbsp;GUI 관련 메소드<br>"
	            + "&nbsp;&nbsp;&nbsp;Listeners (Interface 내 메소드)<br>"
	            + "&nbsp;&nbsp;&nbsp;Setters, Getters<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel.add(label, c);
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();
		
		this.addTab("Unit Test Environment", null, splitPane, null);
		
		
		Object []header = {"Test Case Number", "Method Name", "Description", "Input", "Output", "Result"};
		Object [][]contents = {
				{null, null, null, null, null, null}
		};
		model = new DefaultTableModel(contents, header);
		
		JTable table = new JTable(model);
		table.setCellSelectionEnabled(false);
		table.setRowHeight(70);

	    table.getColumn("Test Case Number").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Test Case Number").setCellEditor(new TextAreaEditor());
	    
	    table.getColumn("Method Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Method Name").setCellEditor(new TextAreaEditor());
	    
	    table.getColumn("Description").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Description").setCellEditor(new TextAreaEditor());
	    
	    table.getColumn("Input").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Input").setCellEditor(new TextAreaEditor());
	  
	    table.getColumn("Output").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Output").setCellEditor(new TextAreaEditor());
	    
	    table.getColumn("Result").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Result").setCellEditor(new TextAreaEditor());
	    
	    JSplitPane splitPane_1 = new JSplitPane();
		JScrollPane panel_1 = new JScrollPane(table);
		JPanel jpanel_1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("+");
		JButton button_2 = new JButton("-");
		JButton button_3 = new JButton("Commit");
		
		jpanel_1.add(button_1);
		jpanel_1.add(button_2);
		jpanel_1.add(button_3);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(panel_1);
		splitPane_1.setTopComponent(jpanel_1);
		splitPane_1.disable();
		
		this.addTab("Unit Test Result", null, splitPane_1, null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(panel_1, popupMenu);
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null,null,null,null,null,null};
				model.addRow(add);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(3))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				table.editingStopped(changeEvent);
				data.setText(24, textPane.getText());
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {"","","","","",""};
				model.addRow(add);
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
					//data.syncmTestCase("U");
					table.editingCanceled(changeEvent);
				}
			}
		});
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(3))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				table.editingStopped(changeEvent);
				utc.clear();
				data.syncTestCase("U");
			
				for (int i = 0; i < model.getRowCount(); i++) {
					UnitTestCase r = new UnitTestCase();
					r.setNumber((String) model.getValueAt(i, 0));
					r.setName((String) model.getValueAt(i, 1));
					r.setDescription((String) model.getValueAt(i, 2));
					r.setInput((String) model.getValueAt(i, 3));
					r.setOutput((String) model.getValueAt(i, 4));
					r.setResult((String) model.getValueAt(i, 5));
					data.setTestCase("U", r);
					
					utc.add(r);
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
			}
		});
	}
	public void save(Datainfo data) {
		data.setText(24, textPane.getText());
		
	}
	public void open(ArrayList<StageText> st, ArrayList<UnitTestCase> req) {
		setTextPane(st);
		model.setRowCount(0);
		for(UnitTestCase r : req) {
			Object[] add = { r.getNumber(), r.getName(), r.getDescription(), r.getInput(), r.getOutput(), r.getResult() };
			model.addRow(add);
		}
		if (req.size() == 0) {
			Object[] add = { "", "", "", "", "" ,"" };
			model.addRow(add);
		}
 	}
	private void setTextPane(ArrayList<StageText> st) {
		textPane.setText(st.get(24).getText());
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
	
}
