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
import com.horstmann.violet.product.diagram.sequence.edge.AsynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.edge.ReturnEdge;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.edge.InteractionEdge;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.horstmann.violet.workspace.editorpart.IEditorPart;

import Model.Graph;
import Model.SystemOperation;

//traceability
public class Activity2046 extends JTabbedPane {

	private IEditorPart editorPart;

	private WorkspacePanel wp;

	public Activity2046(ArrayList<SystemOperation> op, ArrayList<Graph> id, Graph cd) {

		JSplitPane splitPane = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("+");
		JButton button_2 = new JButton("-");
		JButton button_3 = new JButton("Commit");
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.add(button_3);

		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
		IGraphFile graphFile = new GraphFile(graphClass);
		IWorkspace workspace = new Workspace(graphFile);

		this.editorPart = workspace.getEditorPart();
		GraphEditor ge = new GraphEditor(editorPart);

		wp = workspace.getAWTComponent();
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

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getEditorPart().changeZoom(1);

			}
		});

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getEditorPart().changeZoom(-1);

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
				int i = 1;
				ArrayList<UseCaseNode> op_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> id_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> m_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> c_Node = new ArrayList<UseCaseNode>();

				ArrayList<ArrayList<String>> id_allname = new ArrayList<>();

				ArrayList<ArrayList<String>> class_name = new ArrayList<>();

				for (Graph tmp_Graph : id) {
					Collection<IEdge> Edges = tmp_Graph.getGraph().getGraph().getAllEdges();
					ArrayList<String> id_name = new ArrayList<>();
					for (IEdge aEdge : Edges) {
						int exist = 0;
						if (aEdge.getClass().equals(SynchronousCallEdge.class)) {
							SynchronousCallEdge a = (SynchronousCallEdge) aEdge;
							for (String tmp : id_name) {
								if (a.getCenterLabel().toString().equals(tmp)) {
									exist = 1;
								}
							}
							if (exist == 0) {
								String ide = new String(a.getCenterLabel().toString());
								id_name.add(ide);
							}
						} else if (aEdge.getClass().equals(AsynchronousCallEdge.class)) {
							AsynchronousCallEdge a = (AsynchronousCallEdge) aEdge;
							for (String tmp : id_name) {
								if (a.getCenterLabel().toString().equals(tmp)) {
									exist = 1;
								}
							}
							if (exist == 0) {
								String ide = new String(a.getCenterLabel().toString());
								id_name.add(ide);
							}
						} else if (aEdge.getClass().equals(ReturnEdge.class)) {
							ReturnEdge a = (ReturnEdge) aEdge;
							for (String tmp : id_name) {
								if (a.getCenterLabel().toString().equals(tmp)) {
									exist = 1;
								}
							}
							if (exist == 0) {
								String ide = new String(a.getCenterLabel().toString());
								id_name.add(ide);
							}
						}
					}
					id_allname.add(id_name);

				}

				for (SystemOperation tmp_op : op) {
					UseCaseNode tmp_opnode = new UseCaseNode();
					SingleLineText tmp_name = new SingleLineText();
					tmp_name.setText(tmp_op.getName());
					tmp_opnode.setName(tmp_name);
					Point2D tmp_xy = new Point2D.Double(10.0, i * 10.0);
					op_Node.add(tmp_opnode);
					workspace.getGraphFile().getGraph().addNode(tmp_opnode, tmp_xy);
					i = i + 5;
				}
				i = 1;

				for (ArrayList<String> tmp_allid : id_allname) {
					// System.out.println("1");
					for (String tmp_id : tmp_allid) {

						UseCaseNode tmp_idnode = new UseCaseNode();
						SingleLineText tmp_name = new SingleLineText();
						tmp_name.setText(tmp_id);
						tmp_idnode.setName(tmp_name);
						Point2D tmp_xy = new Point2D.Double(200.0, i * 10.0);
						id_Node.add(tmp_idnode);
						workspace.getGraphFile().getGraph().addNode(tmp_idnode, tmp_xy);
						i = i + 5;
					}
				}
				i = 1;
				Collection<INode> node = cd.getGraph().getGraph().getAllNodes();
				for (INode s : node) {
					ClassNode c = (ClassNode) s;
					ArrayList<String> method_name = new ArrayList<>();
					StringTokenizer t = new StringTokenizer(c.getMethods().toString(), "|");
					int k = 1;
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
						Point2D tmp_xy = new Point2D.Double(400.0, k * 20.0);
						m_Node.add(tmp_mnode);
						workspace.getGraphFile().getGraph().addNode(tmp_mnode, tmp_xy);
						k = k + 5;
						method_name.add(tmp_name.toString());
					}

					UseCaseNode tmp_cnode = new UseCaseNode();
					SingleLineText tmp_name = new SingleLineText();
					tmp_name.setText(c.getName());
					tmp_cnode.setName(tmp_name);
					Point2D tmp_xy = new Point2D.Double(800.0, i * 10.0);
					c_Node.add(tmp_cnode);
					workspace.getGraphFile().getGraph().addNode(tmp_cnode, tmp_xy);
					i = i + 5;
					class_name.add(method_name);
				}

				for (UseCaseNode tmp_node : op_Node) {
					for (ArrayList<String> tmp_id : id_allname) {
						for (int j = 0; j < tmp_id.size(); j++) {
							if (tmp_id.get(j).equals(tmp_node.getName().toString())) {
								for (UseCaseNode id_nd : id_Node) {
									InteractionEdge ie_tmp = new InteractionEdge();
									ge.changeEdge(ie_tmp);
									workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
											tmp_node.getLocation(), id_nd, id_nd.getLocation(), null);
								}
							}
						}
					}
				}

				for (UseCaseNode tmp_node : id_Node) {
					for (UseCaseNode m_nd : m_Node) {
						if (m_nd.getName().toString().equals(tmp_node.getName().toString())) {
							InteractionEdge ie_tmp = new InteractionEdge();
							ge.changeEdge(ie_tmp);
							workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
									tmp_node.getLocation(), m_nd, m_nd.getLocation(), null);
						}
					}
				}
				for (UseCaseNode tmp_node : m_Node) {
					for (ArrayList<String> tmp_c : class_name) {
						for (int j = 0; j < tmp_c.size(); j++) {
							if (tmp_c.get(j).equals(tmp_node.getName().toString())) {
								for (UseCaseNode c_nd : c_Node) {
									InteractionEdge ie_tmp = new InteractionEdge();
									ge.changeEdge(ie_tmp);
									workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
											tmp_node.getLocation(), c_nd, c_nd.getLocation(), null);
								}
							}
						}
					}
				}

				wp.refreshDisplay();
			}

		});
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(wp);
		splitPane.setTopComponent(jpanel);
		addTab("Traceability Analysis", null, splitPane, null);
	}

}
