package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.edge.InteractionEdge;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.horstmann.violet.workspace.editorpart.IEditorPart;

import Model.Graph;
import Model.Requirement;
import Model.SystemTestCase;
import Model.UnitTestCase;
import Model.UseCase;

//traceability
public class Activity2067 extends JTabbedPane {

	private IEditorPart editorPart;
	private IEditorPart editorPart2;
	
	public Activity2067(Requirement req, ArrayList<UseCase> uc, Graph cd, ArrayList<SystemTestCase> stc, ArrayList<UnitTestCase> utc) {
		
		JSplitPane splitPane = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("+");
		JButton button_2 = new JButton("-");
		JButton button_3 = new JButton("Commit");
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.add(button_3);
		JTabbedPane jtabbedPane = new JTabbedPane();
		
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
		IGraphFile graphFile = new GraphFile(graphClass);
		IWorkspace workspace = new Workspace(graphFile);

		this.editorPart = workspace.getEditorPart();
		GraphEditor ge = new GraphEditor(editorPart);

		WorkspacePanel wp = workspace.getAWTComponent();
		wp.getScrollableSideBar().setVisible(false);
		wp.getScrollableEditorPart().getViewport().getView()
				.removeMouseListener(wp.getScrollableEditorPart().getViewport().getView().getMouseListeners()[0]);

		wp.getScrollableEditorPart().getViewport().getView().addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent event) {

			}

			public void mouseReleased(MouseEvent event) {

			}

			public void mouseClicked(MouseEvent event) {
				ge.highlight(event);
				wp.refreshDisplay();
			}
		});
		
		graphClass = new UseCaseDiagramGraph().getClass();
		graphFile = new GraphFile(graphClass);
		IWorkspace workspace2 = new Workspace(graphFile);

		this.editorPart2 = workspace2.getEditorPart();
		GraphEditor ge2 = new GraphEditor(editorPart2);

		WorkspacePanel wp2 = workspace2.getAWTComponent();
		wp2.getScrollableSideBar().setVisible(false);
		wp2.getScrollableEditorPart().getViewport().getView()
				.removeMouseListener(wp.getScrollableEditorPart().getViewport().getView().getMouseListeners()[0]);

		wp2.getScrollableEditorPart().getViewport().getView().addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent event) {

			}

			public void mouseReleased(MouseEvent event) {

			}

			public void mouseClicked(MouseEvent event) {
				ge2.highlight(event);
				wp2.refreshDisplay();
			}
		});
		
		jtabbedPane.add("Unit Testing", wp);
		jtabbedPane.addTab("System Testing", wp2);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane t = (JTabbedPane) splitPane.getBottomComponent();
				if(t.getSelectedComponent().equals(wp)){
					workspace.getEditorPart().changeZoom(1);
				}
				else {
					workspace2.getEditorPart().changeZoom(1);
				}
			}
		});

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane t = (JTabbedPane) splitPane.getBottomComponent();
				if(t.getSelectedComponent().equals(wp)){
					workspace.getEditorPart().changeZoom(-1);
				}
				else {
					workspace2.getEditorPart().changeZoom(-1);
				}
			}
		});
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Collection<INode> allNodes = workspace.getGraphFile().getGraph().getAllNodes();
				Collection<IEdge> allEdges = workspace.getGraphFile().getGraph().getAllEdges();
				for (INode in : allNodes) {
					workspace.getGraphFile().getGraph().removeNode(in);
				}
				for (IEdge ie : allEdges) {
					workspace.getGraphFile().getGraph().removeEdge(ie);
				}
				allNodes = workspace2.getGraphFile().getGraph().getAllNodes();
				allEdges = workspace2.getGraphFile().getGraph().getAllEdges();
				for (INode in : allNodes) {
					workspace2.getGraphFile().getGraph().removeNode(in);
				}
				for (IEdge ie : allEdges) {
					workspace2.getGraphFile().getGraph().removeEdge(ie);
				}
				int i = 1;
				ArrayList<UseCaseNode> req_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> uc_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> st_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> ut_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> m_Node = new ArrayList<UseCaseNode>();
				
				ArrayList<String> req_name = new ArrayList<String>(req.getAllName());
		        ArrayList<String> uName = new ArrayList<String>(req.getAlluName());
		        
				
				
				for(String tmp_req : req_name) {
		        	UseCaseNode tmp_reqnode = new UseCaseNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_req);
		        	tmp_reqnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(10.0, i*10.0);
		        	req_Node.add(tmp_reqnode);
		        	workspace2.getGraphFile().getGraph().addNode(tmp_reqnode, tmp_xy);
		        	i=i+5;
		        }
				i=1;
				for(UseCase tmp_uc : uc) {
		        	UseCaseNode tmp_ucnode = new UseCaseNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_uc.getName());
		        	tmp_ucnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(200.0, i*10.0);
		        	uc_Node.add(tmp_ucnode);
		        	workspace2.getGraphFile().getGraph().addNode(tmp_ucnode, tmp_xy);
		        	i=i+5;
		        }
				i=1;
				for(SystemTestCase tmp_stc : stc) {
		        	UseCaseNode tmp_stcnode = new UseCaseNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_stc.getName());
		        	tmp_stcnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(400.0, i*10.0);
		        	st_Node.add(tmp_stcnode);
		        	workspace2.getGraphFile().getGraph().addNode(tmp_stcnode, tmp_xy);
		        	i=i+5;
		        }
				i = 1;
				Collection<INode> node = cd.getGraph().getGraph().getAllNodes();
				for (INode s : node) {
					ClassNode c = (ClassNode) s;
					StringTokenizer t = new StringTokenizer(c.getMethods().toString(), "|");
					for (int j = 0; t.hasMoreTokens(); j++) {
						StringTokenizer u = new StringTokenizer(t.nextToken(), " ");
						for (int l = 0; l < 2; l++) {
							u.nextToken();
						}
						String temp = u.nextToken();
						while (u.hasMoreTokens()) {
							temp = temp + " " + u.nextToken();
						}
						UseCaseNode tmp_mnode = new UseCaseNode();
						SingleLineText tmp_name = new SingleLineText();
						tmp_name.setText(temp);
						tmp_mnode.setName(tmp_name);
						Point2D tmp_xy = new Point2D.Double(10.0, i * 20.0);
						m_Node.add(tmp_mnode);
						workspace.getGraphFile().getGraph().addNode(tmp_mnode, tmp_xy);
						i = i + 5;
					}
				}
				i = 1;
				for(UnitTestCase tmp_utc : utc) {
					UseCaseNode tmp_utcnode = new UseCaseNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_utc.getNumber() + ". " + tmp_utc.getName());
		        	tmp_utcnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(200.0, i * 20.0);
		        	ut_Node.add(tmp_utcnode);
		        	workspace.getGraphFile().getGraph().addNode(tmp_utcnode, tmp_xy);
		        	i=i+5;
				}
				for(UseCaseNode tmp_node : req_Node) {
		        	for(int index=0; index<req_name.size(); index++) {
		        		if(req_name.get(index).equals(tmp_node.getName().toString())){
		        			String related_uc = req.getuName(index);
		        			for(UseCaseNode uc_nd : uc_Node) {
		        				if(uc_nd.getName().toString().equals(related_uc)) {
		        					InteractionEdge ie_tmp = new InteractionEdge();
		        					ge2.changeEdge(ie_tmp);
		        					workspace2.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocation(), uc_nd, uc_nd.getLocation(), null);	
		        				}
		        			}
		        		}
		        	}	
		        }
				for(UseCaseNode tmp_node : uc_Node) {
					for(SystemTestCase temp : stc) {
						String uc = temp.getUsecase();
						StringTokenizer st = new StringTokenizer(uc, " ");
						if(st.hasMoreTokens()) {
							st.nextToken();
							if(st.nextToken().equals(tmp_node.getName().toString())) {
								for(UseCaseNode st_nd : st_Node) {
									if(st_nd.getName().toString().equals(temp.getName())) {
										InteractionEdge ie_tmp = new InteractionEdge();
			        					ge2.changeEdge(ie_tmp);
			        					workspace2.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocation(), st_nd, st_nd.getLocation(), null);
									}
								}
							}
						}
					}
				}
				for (UseCaseNode tmp_node : m_Node) {
					for (UnitTestCase temp : utc) {
						if(temp.getName().equals(tmp_node.getName().toString())) {
							for(UseCaseNode ut_nd : ut_Node) {
								if(ut_nd.getName().toString().equals(temp.getNumber() + ". " + temp.getName())) {
									InteractionEdge ie_tmp = new InteractionEdge();
		        					ge.changeEdge(ie_tmp);
		        					workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocation(), ut_nd, ut_nd.getLocation(), null);
								}
							}
						}
					}
				}
				workspace.getAWTComponent().refreshDisplay();
				workspace2.getAWTComponent().refreshDisplay();
			}

		});
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(jtabbedPane);
		splitPane.setTopComponent(jpanel);
		addTab("Traceability Analysis", null, splitPane, null);
	}

}
