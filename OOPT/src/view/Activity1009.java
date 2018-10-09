package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.NonFuncReq;
import Model.Requirement;

public class Activity1009 extends JTabbedPane {

	JComboBox<String> comboBox = new JComboBox<String>();
	DefaultTableModel model;
	DefaultTableModel model2;

	public Activity1009(JTree tree, Requirement req, Datainfo data) {

		String[] colName = { "Ref", "Function", "Test Case" };
		String[] colName2 = { "Category", "Test Case" };

		Object[][] rowData = { { "", "", "" } };
		Object[][] rowData2 = { { "", "" } };

		model = new DefaultTableModel(rowData, colName);
		model2 = new DefaultTableModel(rowData2, colName2);

		JTable table = new JTable(model);
		JTable table2 = new JTable(model2);
		
		table.setCellSelectionEnabled(false);
		table2.setCellSelectionEnabled(false);
		
		table.setRowHeight(70);
		table2.setRowHeight(70);

		table.getColumn("Ref").setCellRenderer(new TextAreaRenderer());
		table.getColumn("Ref").setCellEditor(new TextAreaEditor());

		table.getColumn("Test Case").setCellRenderer(new TextAreaRenderer());
		table.getColumn("Test Case").setCellEditor(new TextAreaEditor());

		table2.getColumn("Category").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Category").setCellEditor(new TextAreaEditor());

		table2.getColumn("Test Case").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Test Case").setCellEditor(new TextAreaEditor());

		for (int i = 0; i < req.get_length(); i++) {
			comboBox.addItem(req.getName(i));
		}

		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		table.getColumnModel().getColumn(1).setCellEditor(Comboeditor);

		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);
		JSplitPane splitPane = new JSplitPane();
		JSplitPane splitPane_1 = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JPanel jpanel_1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		JButton button = new JButton("Commit");

		JButton button_1 = new JButton("+");
		JButton button_2 = new JButton("-");
		JButton button_3 = new JButton("Commit");

		jpanel.add(button);

		jpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));

		jpanel_1.add(button_1);
		jpanel_1.add(button_2);
		jpanel_1.add(button_3);

		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();

		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(panel2);
		splitPane_1.setTopComponent(jpanel_1);
		splitPane_1.disable();

		this.addTab("Functional Requirement Test Case", null, splitPane, null);
		this.addTab("Non Functional Requirement Test Case", null, splitPane_1, null);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 8) {
						node.setIconName("floppyDrive");
					}
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
				table.editingStopped(changeEvent);
				for (int i = 0; i < req.get_length(); i++) {
					req.setRef((String) table.getValueAt(i, 0), i);
					req.setName((String) table.getValueAt(i, 1), i);
					req.setTestcase((String) table.getValueAt(i, 2), i);
					data.setReq(i, req);
				}
			}
		});

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add = { "", "" };

				model2.addRow(add);
			}
		});

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table2.getSelectedRow();
				if (row != -1) {

					model2.removeRow(row);
					table2.editingCanceled(changeEvent);
				}
			}
		});

		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 8) {
						node.setIconName("floppyDrive");
					}
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);

				table.editingStopped(changeEvent);
				data.syncNonReq("D");
				for (int i = 0; i < model2.getRowCount(); i++) {
					NonFuncReq r = new NonFuncReq();
					r.setCategory((String) model2.getValueAt(i, 0));
					r.setTestcase((String) model2.getValueAt(i, 1));
					r.setType("D");
					data.setNonReq(i, r);
				}
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

	public void syncComboBox(ArrayList array) {
		comboBox.removeAllItems();
		for (int i = 0; i < array.size(); i++) {
			comboBox.addItem((String) array.get(i));
		}
	}

	public void save(Datainfo data, Requirement req) {
		for (int i = 0; i < req.get_length(); i++) {
			data.setReq(i, req);
		}

	}

	public void open(Requirement req, ArrayList<NonFuncReq> nreq) {

		model.setRowCount(0);
		model2.setRowCount(0);
		for (int i = 0; i < req.get_length(); i++) {
			Object[] add = { req.getRef(i), req.getName(i), req.getTestcase(i) };
			model.addRow(add);
		}

		for (NonFuncReq r : nreq) {
			Object[] add2 = { r.getCategory(), r.getTestcase() };
			model2.addRow(add2);
		}
		if (nreq.size() == 0) {
			Object[] add2 = { "", "" };
			model2.addRow(add2);
		}

	}
	/*
	 * public void addComboItem(String add) { comboBox.addItem(add); } public
	 * void removeComboItem(String remove) { comboBox.removeItem(remove); }
	 */
}