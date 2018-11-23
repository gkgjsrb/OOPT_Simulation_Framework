package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.sequence.SequenceDiagramGraph;
import com.horstmann.violet.product.diagram.state.StateDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import Model.Datainfo;
import Model.Requirement;
import Model.StageText;
import Model.UMLDiagram;
import Model.UseCase;

public class Activity1006 extends JTabbedPane {
	// private JTable table;

	private JSplitPane splitPane_6;
	private JSplitPane splitPane_9;
	private JTabbedPane tpanel;
	private JTextPane textPane;

	DefaultTableModel model;
	DefaultTableModel model2;
	DefaultTableModel model3;
	DefaultTableModel model4;
	DefaultTableModel model5;

	private IWorkspace workspace;
	private IWorkspace workspace_1;

	private WorkspacePanel wp;
	private WorkspacePanel wp_1;

	public Activity1006(JTree tree, Requirement req, ArrayList<UseCase> uc, UMLDiagram ud, ArrayList<UMLDiagram> sd,
			ArrayList<UMLDiagram> id, ArrayList<UMLDiagram> std, Datainfo data) {
		// sd.add(new Graph());

		String category[] = { "Primary", "Secondary", "Optional" };
		String rank[] = { "High", "Medium", "Low" };

		JPanel panelc;
		JSplitPane splitPane_10 = new JSplitPane();
		splitPane_9 = new JSplitPane();
		JPanel jpanelc = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		JButton button_13 = new JButton("+");
		JButton button_14 = new JButton("-");
		JButton button_15 = new JButton("Commit");
		JButton button_16 = new JButton("Box");
		JButton button_17 = new JButton("Actor");
		JButton button_18 = new JButton("Edge");
		JButton button_19 = new JButton("Select");
		jpanelc.add(button_13);
		jpanelc.add(button_14);
		jpanelc.add(button_15);

		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
		IGraphFile graphFile = new GraphFile(graphClass);
		workspace_1 = new Workspace(graphFile);

		ClassNode cn = new ClassNode();
		workspace_1.getSideBar().getGraphToolsBar().addTool(cn, "class");

		wp_1 = workspace_1.getAWTComponent();
		wp_1.getScrollableSideBar().setVisible(false);

		panelc = new JPanel();
		panelc.setBackground(Color.DARK_GRAY);
		panelc.setLayout(null);

		button_16.setLocation(23, 120);
		button_16.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workspace_1.getSideBar().getGraphToolsBar()
						.setSelectedTool(workspace_1.getSideBar().getGraphToolsBar().getNodeTools().get(4));
			}

		});
		button_16.setSize(100, 30);
		panelc.add(button_16);

		button_17.setLocation(23, 180);
		button_17.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				workspace_1.getSideBar().getGraphToolsBar()
						.setSelectedTool(workspace_1.getSideBar().getGraphToolsBar().getNodeTools().get(1));
			}
		});
		button_17.setSize(100, 30);
		panelc.add(button_17);

		button_18.setLocation(23, 240);
		button_18.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				workspace_1.getSideBar().getGraphToolsBar()
						.setSelectedTool(workspace_1.getSideBar().getGraphToolsBar().getEdgeTools().get(0));
			}
		});
		button_18.setSize(100, 30);
		panelc.add(button_18);

		button_19.setLocation(23, 60);
		button_19.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				workspace_1.getSideBar().getGraphToolsBar()
						.setSelectedTool(workspace_1.getSideBar().getGraphToolsBar().getNodeTools().get(0));
			}
		});
		button_19.setSize(100, 30);
		panelc.add(button_19);

		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace_1.getEditorPart().changeZoom(1);

			}
		});

		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace_1.getEditorPart().changeZoom(-1);

			}
		});

		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UMLDiagram g = new UMLDiagram();
				g.setName("System Boundary");
				g.setGraph(workspace_1.getGraphFile());
				data.syncGraph("sb", "");
				data.setGraph("sb", g);

			}
		});
		
		splitPane_10.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_9.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane_9.setLeftComponent(panelc);
		splitPane_9.setRightComponent(wp_1);
		splitPane_9.setDividerLocation(150);
		splitPane_10.setBottomComponent(splitPane_9);
		splitPane_10.setTopComponent(jpanelc);
		addTab("Define System Boundary", null, splitPane_10, null);

		JSplitPane splitPane_1 = new JSplitPane();
		JPanel jpanel_1 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_1 = new JButton("Commit");
		jpanel_1.add(button_1, c);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.disable();
		splitPane_1.setBottomComponent(scrollPane_1);
		splitPane_1.setTopComponent(jpanel_1);
		this.addTab("Identify and Describe Actors", null, splitPane_1, null);

		textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		textPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("computer");
					}
					((DefaultTreeModel) tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		JLabel lblNewLabel = new JLabel("<html>example(Library Management System)<br>"
				+ "- Librarian : an employee of the library who interacts with the<br>"
				+ "customers(borrowers) and whose work is supported by the system.<br>" + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_1.add(lblNewLabel, c);
		//
		JTabbedPane tabPane = new JTabbedPane();
		JSplitPane splitPane_2 = new JSplitPane();
		JScrollPane panel = new JScrollPane();
		String[] colName = { "AUseCase" };
		Object[][] rowData = { { "" } };
		model = new DefaultTableModel(rowData, colName);
		JTable table = new JTable(model);
		table.setCellSelectionEnabled(false);

		panel.setViewportView(table);
		table.setRowHeight(45);

		table.getColumn("AUseCase").setCellRenderer(new TextAreaRenderer());
		table.getColumn("AUseCase").setCellEditor(new TextAreaEditor());

		JPanel jpanel_2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_2 = new JButton("+");
		JButton button_3 = new JButton("-");
		JButton button_4 = new JButton("Commit");

		jpanel_2.add(button_2);
		jpanel_2.add(button_3);
		jpanel_2.add(button_4);
		jpanel_2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setBottomComponent(panel);
		splitPane_2.setTopComponent(jpanel_2);
		splitPane_2.disable();

		JSplitPane splitPane_3 = new JSplitPane();
		JScrollPane panel2 = new JScrollPane();
		String[] colName2 = { "EUseCase" };
		Object[][] rowData2 = { { "" } };
		model2 = new DefaultTableModel(rowData2, colName2);
		JTable table_1 = new JTable(model2);
		table_1.setCellSelectionEnabled(false);

		panel2.setViewportView(table_1);
		table_1.setRowHeight(45);

		table_1.getColumn("EUseCase").setCellRenderer(new TextAreaRenderer());
		table_1.getColumn("EUseCase").setCellEditor(new TextAreaEditor());

		JPanel jpanel_3 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_5 = new JButton("+");
		JButton button_6 = new JButton("-");
		JButton button_7 = new JButton("Commit");

		jpanel_3.add(button_5);
		jpanel_3.add(button_6);
		jpanel_3.add(button_7);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setBottomComponent(panel2);
		splitPane_3.setTopComponent(jpanel_3);
		splitPane_3.disable();

		tabPane.addTab("Use-Cases by Actor-Based", null, splitPane_2, null);
		tabPane.addTab("Use-Cases by Event-Based", null, splitPane_3, null);
		this.addTab("Identify Use-Case", null, tabPane, null);

		JSplitPane splitPane_4 = new JSplitPane();
		JScrollPane panel3 = new JScrollPane();
		String[] colName3 = { "Ref", "Function", "Use-Case Number & Name(Formmat : #._Name)" };
		Object[][] rowData3 = { { "", "", "" } };
		model3 = new DefaultTableModel(rowData3, colName3);
		JTable table_2 = new JTable(model3);
		table_2.setCellSelectionEnabled(false);

		panel3.setViewportView(table_2);
		table_2.setRowHeight(45);

		table_2.getColumn("Use-Case Number & Name(Formmat : #._Name)").setCellRenderer(new TextAreaRenderer());
		table_2.getColumn("Use-Case Number & Name(Formmat : #._Name)").setCellEditor(new TextAreaEditor(table_2, req));

		JPanel jpanel_4 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_8 = new JButton("Commit");

		jpanel_4.add(button_8);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setBottomComponent(panel3);
		splitPane_4.setTopComponent(jpanel_4);
		splitPane_4.disable();

		this.addTab("Allocate System Functions into Related Use-Cases", null, splitPane_4, null);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane TabbedPane = (JTabbedPane) e.getSource();

				int tab = TabbedPane.getSelectedIndex();

				if (tab == 3) {
					sync(req);
				}
			}

		});

		JSplitPane splitPane_5 = new JSplitPane();
		JScrollPane panel4 = new JScrollPane();
		String[] colName4 = { "Use-Case Number & Name", "Category" };
		Object[][] rowData4 = { { "", "" } };
		model4 = new DefaultTableModel(rowData4, colName4);
		JTable table_3 = new JTable(model4);
		table_3.setCellSelectionEnabled(false);
		JComboBox<String> comboBox = new JComboBox<String>(category);
		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		table_3.getColumnModel().getColumn(1).setCellEditor(Comboeditor);
		panel4.setViewportView(table_3);
		table_3.setRowHeight(45);

		// table_3.getColumn("Use-Case Number & Name").setCellRenderer(new
		// TextAreaRenderer());
		// table_3.getColumn("Use-Case Number & Name").setCellEditor(new
		// TextAreaEditor());

		JPanel jpanel_5 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_9 = new JButton("Commit");

		jpanel_5.add(button_9);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setBottomComponent(panel4);
		splitPane_5.setTopComponent(jpanel_5);
		splitPane_5.disable();

		this.addTab("Categorize Use-Cases", null, splitPane_5, null);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane TabbedPane = (JTabbedPane) e.getSource();

				int tab = TabbedPane.getSelectedIndex();

				if (tab == 4) {
					if (model4.getRowCount() < model3.getRowCount()) {
						int count = model3.getRowCount() - model4.getRowCount();
						for (int i = 0; i < count; i++) {
							Object[] add = { "", "" };
							model4.addRow(add);
						}
					} else if (model4.getRowCount() > model3.getRowCount()) {
						int count = model4.getRowCount() - model3.getRowCount();
						for (int i = 0; i < count; i++) {
							model4.removeRow(0);

						}
					}

					for (int i = 0; i < model4.getRowCount(); i++) {
						model4.setValueAt(model3.getValueAt(i, 2), i, 0);
						model4.setValueAt(req.getuCategory(i), i, 1);
					}

				}
			}

		});

		splitPane_6 = new JSplitPane();
		JPanel jpanel_6 = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		JButton button_10 = new JButton("Commit");
		jpanel_6.add(button_10);

		Class<? extends IGraph> graphClass_1 = new UseCaseDiagramGraph().getClass();
		IGraphFile graphFile_1 = new GraphFile(graphClass_1);

		workspace = new Workspace(graphFile_1);
		wp = workspace.getAWTComponent();

		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_6.setBottomComponent(wp);
		splitPane_6.setTopComponent(jpanel_6);
		splitPane_6.disable();
		addTab("Draw a Use-Case Diagram", null, splitPane_6, null);

		JSplitPane splitPane_7 = new JSplitPane();
		tpanel = new JTabbedPane();

		JPanel jpanel_7 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_11 = new JButton("Commit");

		jpanel_7.add(button_11);
		jpanel_7.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		splitPane_7.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_7.setBottomComponent(tpanel);
		splitPane_7.setTopComponent(jpanel_7);
		splitPane_7.disable();
		addTab("Describe Use-Cases", null, splitPane_7, null);

		JSplitPane splitPane_8 = new JSplitPane();
		JScrollPane panel5 = new JScrollPane();
		String[] colName5 = { "Use-Case Number & Name", "Rank" };
		Object[][] rowData5 = { { "", "" } };
		model5 = new DefaultTableModel(rowData5, colName5);
		JTable table_5 = new JTable(model5);
		table_5.setCellSelectionEnabled(false);
		JComboBox<String> comboBox2 = new JComboBox<String>(rank);
		TableCellEditor Comboeditor2 = new DefaultCellEditor(comboBox2);
		table_5.getColumnModel().getColumn(1).setCellEditor(Comboeditor2);

		panel5.setViewportView(table_5);
		table_5.setRowHeight(45);

		// table_3.getColumn("Use-Case Number & Name").setCellRenderer(new
		// TextAreaRenderer());
		// table_3.getColumn("Use-Case Number & Name").setCellEditor(new
		// TextAreaEditor());

		JPanel jpanel_8 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_12 = new JButton("Commit");

		jpanel_8.add(button_12);
		jpanel_8.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		splitPane_8.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_8.setBottomComponent(panel5);
		splitPane_8.setTopComponent(jpanel_8);
		splitPane_8.disable();

		this.addTab("Rank Use-Cases", null, splitPane_8, null);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane TabbedPane = (JTabbedPane) e.getSource();

				int tab = TabbedPane.getSelectedIndex();

				if (tab == 7) {
					if (model5.getRowCount() < model3.getRowCount()) {
						int count = model3.getRowCount() - model5.getRowCount();
						for (int i = 0; i < count; i++) {
							Object[] add = { "", "" };
							model5.addRow(add);
						}
					} else if (model5.getRowCount() > model3.getRowCount()) {
						int count = model5.getRowCount() - model3.getRowCount();
						for (int i = 0; i < count; i++) {
							model5.removeRow(0);

						}
					}

					for (int i = 0; i < model5.getRowCount(); i++) {
						model5.setValueAt(model3.getValueAt(i, 2), i, 0);
						model5.setValueAt(req.getRank(i), i, 1);
					}

				}
			}

		});

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("floppyDrive");
					}
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
				data.setText(15, textPane.getText());
			}
		});

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add = { "" };
				model.addRow(add);
			}
		});

		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row != -1) {
					model.removeRow(row);
					data.syncBUsecase("A", model.getRowCount());
					table.editingCanceled(changeEvent);
				}
			}
		});

		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("floppyDrive");
					}
				}
				table.editingStopped(changeEvent);

				for (int i = 0; i < model.getRowCount(); i++) {
					String text = (String) model.getValueAt(i, 0);
					data.setBasedUsecase(i, "A", text);
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
			}
		});

		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add = { "" };
				model2.addRow(add);
			}
		});

		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table_1.getSelectedRow();
				if (row != -1) {
					model2.removeRow(row);
					data.syncBUsecase("E", model2.getRowCount());
					table_1.editingCanceled(changeEvent);
				}
			}
		});

		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("floppyDrive");
					}
				}
				table_1.editingStopped(changeEvent);

				for (int i = 0; i < model2.getRowCount(); i++) {
					String text = (String) model2.getValueAt(i, 0);
					data.setBasedUsecase(i, "E", text);
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
			}
		});

		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("floppyDrive");
					}
				}
				table_2.setEditingRow(table_2.getRowCount() - 1);
				table_2.editingStopped(changeEvent);

				for (int i = 0; i < req.get_length(); i++) {
					String n = (String) table_2.getValueAt(i, 2);
					if (n == null) {
						req.setuNumber(-1, i);
						req.setuName(n, i);
						data.setReq(i, req);
					} else {
						if (n.length() < 1) {
							req.setuNumber(-1, i);
							req.setuName(n, i);
							data.setReq(i, req);
						} else {
							int idx = n.indexOf(".");
							String s = n.substring(0, idx);
							req.setuNumber(Integer.parseInt(s), i);

							s = n.substring(idx + 2);
							if (s == null)
								s = "";
							req.setuName(s, i);
							data.setReq(i, req);
						}
					}
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
			}
		});

		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("floppyDrive");
					}
				}
				table_3.editingCanceled(changeEvent);
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
				for (int i = 0; i < req.get_length(); i++) {
					String n = table_3.getValueAt(i, 1).toString();
					if (n == null) {
						req.setuCategory("", i);
					} else {
						req.setuCategory(n, i);

					}
					data.setReq(i, req);
				}
			}
		});

		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ud.setGraph(workspace.getGraphFile());
				ud.setName("usecase");
				data.syncGraph("ud", "");
				data.setGraph("ud", ud);

				Collection<INode> allNodes = workspace.getGraphFile().getGraph().getAllNodes();
				Collection<IEdge> allEdges = workspace.getGraphFile().getGraph().getAllEdges();
				tpanel.removeAll();
				for (INode aNode : allNodes) {
					int exist = 0;
					if (aNode.getClass().equals(UseCaseNode.class)) {
						UseCaseNode a = (UseCaseNode) aNode;
						for (UseCase tmp : uc) {
							if (a.getId().equals(tmp.getId())) {
								tmp.setName(a.getName().toString());
								exist = 1;
							}
						}
						if (exist == 0) {
							UseCase usec = new UseCase();
							usec.setName(a.getName().toString());
							usec.setId(a.getId());
							uc.add(usec);
						}
					}
				}
				ArrayList<UseCase> tmp_list = new ArrayList<UseCase>();
				for (UseCase tmp : uc) {
					for (INode aNode : allNodes) {
						if (tmp.getId().equals(aNode.getId())) {
							tmp_list.add(tmp);
						}
					}
				} // clone?

				uc.clear();
				uc.addAll(tmp_list);
				Collections.reverse(uc);

				syncSd(sd, uc);

				syncId(id, uc);

				ArrayList<UMLDiagram> stdTmp = new ArrayList<>();
				stdTmp.addAll(std);
				std.clear();
				for (UseCase tmp : uc) {
					int exist = 0;
					for (UMLDiagram gtmp : stdTmp) {
						if (gtmp.getId().equals(tmp.getId().toString())) {
							std.add(gtmp);
							exist = 1;
						}
					}
					if (exist == 0) {
						UMLDiagram stdtmp = new UMLDiagram(tmp.getName());
						Class<? extends IGraph> graphClass = new StateDiagramGraph().getClass();
						IGraphFile graphFile = new GraphFile(graphClass);
						stdtmp.setGraph(graphFile);
						stdtmp.setId(tmp.getId().toString());
						std.add(stdtmp);
					}
				}

				for (UseCase tmp : uc) {
					JScrollPane usecasePane = new JScrollPane();
					tpanel.addTab(tmp.getName(), null, usecasePane, null);
					JTable table_4 = new JTable();
					table_4.setCellSelectionEnabled(false);
					table_4.setModel(new DefaultTableModel(new Object[][] { { "Name", tmp.getName() },
							{ "Actor", tmp.getActor() }, { "Description", tmp.getDes() } }, new String[] { "", " " }) {
						boolean[] columnEditables = new boolean[] { false, true };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					table_4.getColumnModel().getColumn(0).setResizable(false);
					usecasePane.setViewportView(table_4);
					table_4.setRowHeight(45);
					table_4.getColumn(" ").setCellRenderer(new TextAreaRenderer());
					table_4.getColumn(" ").setCellEditor(new TextAreaEditor(table_4, uc, tpanel, workspace));
				}
			}
		});
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("floppyDrive");
					}
				}

				for (int i = 0; i < tpanel.getTabCount(); i++) {
					JScrollPane cp = (JScrollPane) tpanel.getComponentAt(i);

					JViewport v = cp.getViewport();
					JTable t = (JTable) v.getView();
					t.editingStopped(changeEvent);
					tpanel.setTitleAt(i, (String) t.getValueAt(0, 1));

					uc.get(i).setName((String) t.getValueAt(0, 1));
					uc.get(i).setActor((String) t.getValueAt(1, 1));
					uc.get(i).setDes((String) t.getValueAt(2, 1));

					Collection<INode> allNodes = workspace.getGraphFile().getGraph().getAllNodes();
					for (INode aNode : allNodes) {
						if (aNode.getClass().equals(UseCaseNode.class)) {
							UseCaseNode a = (UseCaseNode) aNode;
							if (a.getId().equals(uc.get(i).getId())) {
								a.getName().setText((String) t.getValueAt(0, 1));
							}
						}
					}

					data.setUsecase(i, uc.get(i));

				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
			}
		});
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 5) {
						node.setIconName("floppyDrive");
					}
				}
				table_5.editingStopped(changeEvent);
				for (int i = 0; i < req.get_length(); i++) {
					String n = table_5.getValueAt(i, 1).toString();
					if (n == null) {
						req.setRank("", i);
					} else {
						req.setRank(n, i);
					}
					data.setReq(i, req);
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
			}
		});
	}

	public void sync(Requirement req) {
		if (req.get_length() > model3.getRowCount()) {
			int count = req.get_length() - model3.getRowCount();
			for (int i = 0; i < count; i++) {
				Object[] add = { "", "", "" };
				Object[] add2 = { "", "" };
				model3.addRow(add);
				model4.addRow(add2);
				model5.addRow(add2);
			}
		} else if (req.get_length() < model3.getRowCount()) {
			int count = model3.getRowCount() - req.get_length();
			for (int i = 0; i < count; i++) {
				model3.removeRow(0);
				model4.removeRow(0);
				model5.removeRow(0);
			}
		}
		for (int i = 0; i < req.get_length(); i++) {
			model3.setValueAt(req.getRef(i), i, 0);
			model3.setValueAt(req.getName(i), i, 1);
			if (req.getuNumber(i) < 0) {

			} else {
				model3.setValueAt(req.getuNumber(i) + ". " + req.getuName(i), i, 2);
				model4.setValueAt(req.getuNumber(i) + ". " + req.getuName(i), i, 0);
				model4.setValueAt(req.getuCategory(i), i, 1);

				model5.setValueAt(req.getuNumber(i) + ". " + req.getuName(i), i, 0);
				model5.setValueAt(req.getRank(i), i, 0);
			}

		}
	}

	public void save(Datainfo data, Requirement req, ArrayList<UseCase> uc) {
		UMLDiagram g = new UMLDiagram();
		g.setName("System Boundary");
		g.setGraph(workspace_1.getGraphFile());
		data.syncGraph("sb", "");
		data.setGraph("sb", g);
		data.setText(15, textPane.getText());
		for (int i = 0; i < model.getRowCount(); i++) {
			String text = (String) model.getValueAt(i, 0);
			data.setBasedUsecase(i, "A", text);
		}
		for (int i = 0; i < model2.getRowCount(); i++) {
			String text = (String) model2.getValueAt(i, 0);
			data.setBasedUsecase(i, "E", text);
		}
		for (int i = 0; i < req.get_length(); i++) {
			data.setReq(i, req);
		}
		data.syncUsecase();
		for (UseCase tmp : uc) {
			data.setUsecase(uc.indexOf(tmp), tmp);
		}
	}

	public void open(ArrayList<StageText> st, ArrayList<String> ausecase, ArrayList<String> eusecase, UMLDiagram ud,
			ArrayList<UseCase> uc, UMLDiagram sb) {
		setActors(st);
		if(sb.getGraph() != null) {
			workspace_1 = new Workspace(sb.getGraph());
			wp_1 = workspace_1.getAWTComponent();
			ClassNode cn = new ClassNode();
			workspace_1.getSideBar().getGraphToolsBar().addTool(cn, "class");
			wp_1.getScrollableSideBar().setVisible(false);
			splitPane_9.setRightComponent(wp_1);
		}
		
		if(ud.getGraph() != null) {
			workspace = new Workspace(ud.getGraph());
			wp = workspace.getAWTComponent();
			splitPane_6.setBottomComponent(wp);
		}
		

		model.setRowCount(0);
		model2.setRowCount(0);

		for (String s : ausecase) {
			Object[] add = { s };
			model.addRow(add);
		}

		for (String s : eusecase) {
			Object[] add = { s };
			model2.addRow(add);
		}
		tpanel.removeAll();
		for (UseCase tmp : uc) {
			JScrollPane usecasePane = new JScrollPane();
			tpanel.addTab(tmp.getName(), null, usecasePane, null);
			JTable table_4 = new JTable();
			table_4.setModel(new DefaultTableModel(new Object[][] { { "Name", tmp.getName() },
					{ "Actor", tmp.getActor() }, { "Description", tmp.getDes() } }, new String[] { "", " " }) {
				boolean[] columnEditables = new boolean[] { false, true };

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table_4.getColumnModel().getColumn(0).setResizable(false);
			usecasePane.setViewportView(table_4);
			table_4.setRowHeight(45);
			table_4.getColumn(" ").setCellRenderer(new TextAreaRenderer());
			table_4.getColumn(" ").setCellEditor(new TextAreaEditor(table_4, uc, tpanel, workspace));
		}
	}

	private void setActors(ArrayList<StageText> st) {
		textPane.setText(st.get(15).getText());
	}

	private void syncSd(ArrayList<UMLDiagram> sd, ArrayList<UseCase> uc) {

		ArrayList<UMLDiagram> sdTmp = new ArrayList<>();
		sdTmp.addAll(sd);
		sd.clear();
		// data.syncGraph("sd");
		for (UseCase tmp : uc) {
			int exist = 0;
			for (UMLDiagram gtmp : sdTmp) {

				if (gtmp.getId().equals(tmp.getId().toString())) {
					sd.add(gtmp);
					exist = 1;
				}
			}
			if (exist == 0) {
				for (int i = 0; i < model.getRowCount(); i++) {
					if (tmp.getName().equals(model.getValueAt(i, 0))) {
						UMLDiagram sdtmp = new UMLDiagram(tmp.getName());
						Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
						IGraphFile graphFile = new GraphFile(graphClass);
						sdtmp.setGraph(graphFile);
						sdtmp.setId(tmp.getId().toString());
						sd.add(sdtmp);
						break;
					}
				}
			}

		}
		/*
		 * for(Graph g : sd) { data.setGraph("sd", g); }
		 */
	}

	private void syncId(ArrayList<UMLDiagram> id, ArrayList<UseCase> uc) {

		ArrayList<UMLDiagram> idTmp = new ArrayList<>();
		idTmp.addAll(id);
		id.clear();
		// data.syncGraph("id");
		for (UseCase tmp : uc) {
			int exist = 0;
			for (UMLDiagram gtmp : idTmp) {
				if (gtmp.getId().equals(tmp.getId().toString())) {
					id.add(gtmp);
					exist = 1;
				}
			}
			if (exist == 0) {
				for (int i = 0; i < model.getRowCount(); i++) {
					if (tmp.getName().equals(model.getValueAt(i, 0))) {
						UMLDiagram idtmp = new UMLDiagram(tmp.getName());
						Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
						IGraphFile graphFile = new GraphFile(graphClass);
						idtmp.setGraph(graphFile);
						idtmp.setId(tmp.getId().toString());
						id.add(idtmp);
						break;
					}
				}
			}
		}
		/*
		 * for(Graph g : id) { data.setGraph("id", g); }
		 */
	}
	public void newActivity() {
		textPane.setText("");
		model.setRowCount(0);
		Object[] add= {""};
		model.addRow(add);
		
		model2.setRowCount(0);
		Object[] add2= {""};
		model2.addRow(add2);
		
		model3.setRowCount(0);
		Object[] add3= {"","",""};
		model3.addRow(add3);
		
		model4.setRowCount(0);
		Object[] add4= {"",""};
		model4.addRow(add4);
		
		model5.setRowCount(0);
		Object[] add5= {"", ""};
		model5.addRow(add5);
		
		tpanel.removeAll();
		Class<? extends IGraph> graphClass_1 = new UseCaseDiagramGraph().getClass();
		IGraphFile graphFile_1 = new GraphFile(graphClass_1);
		
		workspace_1 = new Workspace(graphFile_1);
		wp_1 = workspace_1.getAWTComponent();
		ClassNode cn = new ClassNode();
		workspace_1.getSideBar().getGraphToolsBar().addTool(cn, "class");
		wp_1.getScrollableSideBar().setVisible(false);
		splitPane_9.setRightComponent(wp_1);
		splitPane_9.setDividerLocation(150);
	
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
		IGraphFile graphFile = new GraphFile(graphClass);
		
		workspace = new Workspace(graphFile);
		wp = workspace.getAWTComponent();
		splitPane_6.setBottomComponent(wp);
		
	}
}
