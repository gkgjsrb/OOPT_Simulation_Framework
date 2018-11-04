package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import Model.Datainfo;
import Model.UMLDiagram;

public class Activity1008 extends JTabbedPane {
	
	private IWorkspace workspace;
    private WorkspacePanel wp;
    private JPanel panel;
    private JSplitPane splitPane_1;
    
	public Activity1008(JTree tree, Datainfo data) {

		JSplitPane splitPane = new JSplitPane();
		splitPane_1 = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		JButton button = new JButton("+");
		JButton button_1 = new JButton("-");
		JButton button_2 = new JButton("Commit");
		JButton button_3 = new JButton("Box");
		JButton button_4 = new JButton("Actor");
		JButton button_5 = new JButton("Edge");
		JButton button_6 = new JButton("Select");
		jpanel.add(button);
		jpanel.add(button_1);
		jpanel.add(button_2);
		
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        workspace = new Workspace(graphFile);
        
        ClassNode c = new ClassNode();
        workspace.getSideBar().getGraphToolsBar().addTool(c, "class");
        
        wp = workspace.getAWTComponent();
        wp.getScrollableSideBar().setVisible(false);
        
        panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);

		button_3.setLocation(23, 120);
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getGraphToolsBar().setSelectedTool(workspace.getSideBar().getGraphToolsBar().getNodeTools().get(4));
			}

		});
		button_3.setSize(100, 30);
		panel.add(button_3);

		button_4.setLocation(23, 180);
		button_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				workspace.getSideBar().getGraphToolsBar().setSelectedTool(workspace.getSideBar().getGraphToolsBar().getNodeTools().get(1));
			}
		});
		button_4.setSize(100, 30);
		panel.add(button_4);
		
		button_5.setLocation(23, 240);
		button_5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				workspace.getSideBar().getGraphToolsBar().setSelectedTool(workspace.getSideBar().getGraphToolsBar().getEdgeTools().get(0));
			}
		});
		button_5.setSize(100, 30);
		panel.add(button_5);
		
		button_6.setLocation(23, 60);
		button_6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				workspace.getSideBar().getGraphToolsBar().setSelectedTool(workspace.getSideBar().getGraphToolsBar().getNodeTools().get(0));
			}
		});
		button_6.setSize(100, 30);
		panel.add(button_6);
		
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getEditorPart().changeZoom(1);
		    
			}
		});
		
        button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getEditorPart().changeZoom(-1);
		        
			}
		});
        
        button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UMLDiagram g = new UMLDiagram();
				g.setName("System Architecture");
				g.setGraph(workspace.getGraphFile());
				data.syncGraph("dsa", "");
				data.setGraph("dsa", g);
		        
			}
		});
      //wp.refreshDisplay();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane_1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane_1.setLeftComponent(panel);
        splitPane_1.setRightComponent(wp);
        splitPane_1.setDividerLocation(150);
        splitPane.setBottomComponent(splitPane_1);
        splitPane.setTopComponent(jpanel);
        addTab("Define System Architecture", null, splitPane, null);
	}
	public void save(Datainfo data) {
		UMLDiagram g = new UMLDiagram();
		g.setName("System Architecture");
		g.setGraph(workspace.getGraphFile());
		data.syncGraph("dsa", "");
		data.setGraph("dsa", g);
	}
	public void open(UMLDiagram g) {
		workspace = new Workspace(g.getGraph());
		wp = workspace.getAWTComponent();
		ClassNode c = new ClassNode();
        workspace.getSideBar().getGraphToolsBar().addTool(c, "class");
		wp.getScrollableSideBar().setVisible(false);
		splitPane_1.setRightComponent(wp);
	}

}
