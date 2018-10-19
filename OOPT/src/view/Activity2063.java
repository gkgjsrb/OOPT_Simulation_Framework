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
import Model.StageText;
import Model.SystemTestCase;
import Model.UnitTestCase;

public class Activity2063 extends JTabbedPane {

	/**
	 * Create the panel.
	 */
	DefaultTableModel model;
	JTextPane textPane;
	public Activity2063(JTree tree, ArrayList<SystemTestCase> stc, Datainfo data) {
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
		this.addTab("System Test Environment", null, splitPane, null);
		
		String []header = {"Test Case Number", "Test Name","Description", "Use Case", "System Function", "Result"};
		String [][]contents = {
				{null, null, null, null, null, null}
		};
		model = new DefaultTableModel(contents, header);
		
		JTable table = new JTable(model);
		table.setCellSelectionEnabled(false);
		table.setRowHeight(70);

	    table.getColumn("Test Case Number").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Test Case Number").setCellEditor(new TextAreaEditor());
	    
		table.getColumn("Test Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Test Name").setCellEditor(new TextAreaEditor());
	    
		table.getColumn("Description").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Description").setCellEditor(new TextAreaEditor());
	 		
		table.getColumn("Use Case").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Use Case").setCellEditor(new TextAreaEditor());
	    
	    table.getColumn("System Function").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("System Function").setCellEditor(new TextAreaEditor());

	    table.getColumn("Result").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Result").setCellEditor(new TextAreaEditor());
	    
	    JSplitPane splitPane_1 = new JSplitPane();
		JScrollPane panel_1 = new JScrollPane(table);
		JPanel jpanel_1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("Commit");
		
		jpanel_1.add(button_1);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(panel_1);
		splitPane_1.setTopComponent(jpanel_1);
		splitPane_1.disable();
	    
		this.addTab("System Test Result", null,splitPane_1, null);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(panel_1, popupMenu);
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null,null,null};
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
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(3))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				table.editingStopped(changeEvent);
				stc.clear();
				data.syncSystemTestCase();
			
				for (int i = 0; i < model.getRowCount(); i++) {
					SystemTestCase r = new SystemTestCase();
					r.setNumber((String) model.getValueAt(i, 0));
					r.setName((String) model.getValueAt(i, 1));
					r.setDescription((String) model.getValueAt(i, 2));
					r.setUsecase((String) model.getValueAt(i, 3));
					r.setSystemFunction((String) model.getValueAt(i, 4));
					r.setResult((String) model.getValueAt(i, 5));
					data.setSystemTestCase(i, r);
					stc.add(r);
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
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
	public void syncSystemTestCase(ArrayList<SystemTestCase> stc) {
		if(stc.size()>model.getRowCount()) {
			int count = stc.size()-model.getRowCount();
			for(int i = 0; i < count; i++) {
				Object[] add = {"", "", "","","",""};
				model.addRow(add);
			}
		}
		else if(stc.size()>model.getRowCount()) {
			int count = model.getRowCount()-stc.size();
			for(int i = 0; i < count; i++) {
				model.removeRow(0);
			}
		}
		for(int i = 0; i < stc.size(); i++) {
			model.setValueAt(stc.get(i).getNumber(), i, 0);
			model.setValueAt(stc.get(i).getName(), i, 1);
			model.setValueAt(stc.get(i).getDescription(), i, 2);
			model.setValueAt(stc.get(i).getUsecase(), i, 3);
			model.setValueAt(stc.get(i).getSystemFunction(), i, 4);
		}	
	}
	public void save(Datainfo data) {
		data.setText(26, textPane.getText());
		
	}
	public void open(ArrayList<StageText> st, ArrayList<SystemTestCase> req) {
		setTextPane(st);
		model.setRowCount(0);
		for(SystemTestCase r : req) {
			Object[] add = { r.getNumber(), r.getName(), r.getDescription(), r.getUsecase(), r.getSystemFunction(), r.getResult() };
			model.addRow(add);
		}
		if (req.size() == 0) {
			Object[] add = { "", "", "", "", "" ,"" };
			model.addRow(add);
		}
 	}
	private void setTextPane(ArrayList<StageText> st) {
		textPane.setText(st.get(26).getText());
	}
}
