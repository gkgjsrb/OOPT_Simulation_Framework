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
import com.horstmann.violet.product.diagram.classes.edge.AssociationEdge;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import com.horstmann.violet.product.diagram.sequence.edge.AsynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
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
				ArrayList<ClassNode> op_Node = new ArrayList<>();
				ArrayList<ClassNode> id_Node = new ArrayList<>();
				ArrayList<ClassNode> m_Node = new ArrayList<>();
				ArrayList<ClassNode> c_Node = new ArrayList<>();

				ArrayList<String> id_name = new ArrayList<>();

				for (Graph tmp_Graph : id) {
					
					Collection<IEdge> Edges = tmp_Graph.getGraph().getGraph().getAllEdges();
					
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
								if(a.getCenterLabel().toString().length() > 0) {
									if(a.getCenterLabel().toString().charAt(0) == '<') {
										
									}
									else {
										String ide = new String(a.getCenterLabel().toString());
										id_name.add(ide);
									}
								}
							}
						} else if (aEdge.getClass().equals(AsynchronousCallEdge.class)) {
							AsynchronousCallEdge a = (AsynchronousCallEdge) aEdge;
							for (String tmp : id_name) {
								if (a.getCenterLabel().toString().equals(tmp)) {
									exist = 1;
								}
							}
							if (exist == 0) {
								if(a.getCenterLabel().toString().length() > 0) {
									if(a.getCenterLabel().toString().charAt(0) == '<') {
										
									}
									else {
										String ide = new String(a.getCenterLabel().toString());
										id_name.add(ide);
									}
								}
							}
						}
					}
					

				}

				for (SystemOperation tmp_op : op) {
					ClassNode tmp_opnode = new ClassNode();
					SingleLineText tmp_name = new SingleLineText();
					tmp_name.setText(tmp_op.getOp_name());
					tmp_opnode.setName(tmp_name);
					Point2D tmp_xy = new Point2D.Double(10.0, i * 10.0);
					op_Node.add(tmp_opnode);
					workspace.getGraphFile().getGraph().addNode(tmp_opnode, tmp_xy);
					i = i + 5;
				}
				i = 1;

				for (String tmp_id : id_name) {
					ClassNode tmp_idnode = new ClassNode();
					SingleLineText tmp_name = new SingleLineText();
					tmp_name.setText(tmp_id);
					tmp_idnode.setName(tmp_name);
					Point2D tmp_xy = new Point2D.Double(400.0, i * 10.0);
					id_Node.add(tmp_idnode);
					workspace.getGraphFile().getGraph().addNode(tmp_idnode, tmp_xy);
					i = i + 5;
				}
				
				i = 1;
				Collection<INode> node = cd.getGraph().getGraph().getAllNodes();
				int k = 1;
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
						ClassNode tmp_mnode = new ClassNode();
						SingleLineText tmp_name = new SingleLineText();
						tmp_name.setText(temp);
						tmp_mnode.setName(tmp_name);
						Point2D tmp_xy = new Point2D.Double(1400.0, k * 10.0);
						m_Node.add(tmp_mnode);
						workspace.getGraphFile().getGraph().addNode(tmp_mnode, tmp_xy);
						k = k + 5;
						
					}

					ClassNode tmp_cnode = new ClassNode();
					SingleLineText tmp_name = new SingleLineText();
					tmp_name.setText(c.getName());
					tmp_cnode.setName(tmp_name);
					Point2D tmp_xy = new Point2D.Double(2400.0, i * 30.0);
					c_Node.add(tmp_cnode);
					workspace.getGraphFile().getGraph().addNode(tmp_cnode, tmp_xy);
					i = i + 5;
				}
			
				for (ClassNode tmp_node : op_Node) {
					for (Graph tmp_id : id) {
						//if(tmp_id.getName().equals(tmp_node.getName().toString())) {
							Collection<IEdge> Edges = tmp_id.getGraph().getGraph().getAllEdges();
							for(IEdge e :Edges) {
									if(e.getClass().equals(SynchronousCallEdge.class)) {
										SynchronousCallEdge a = (SynchronousCallEdge) e;
										if(a.getCenterLabel().toString().equals(tmp_node.getName().toString())) {
											for (ClassNode id_nd : id_Node) {
												if(id_nd.getName().toString().equals(tmp_node.getName().toString())) {
													AssociationEdge ie_tmp = new AssociationEdge();
													ge.changeEdge(ie_tmp);
													workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
															tmp_node.getLocationOnGraph(), id_nd, id_nd.getLocationOnGraph(), null);
													//System.out.println(tmp_node.getName() + " " + tmp_id.getName() + " " + id_nd.getName() + " " + a.getCenterLabel());
													INode inode = a.getEndNode();
													int u = 0;
													while(u==0) {
														for(IEdge edge : Edges) {
															int t = 0;
															if(edge.getStartNode().equals(inode)) {
																if(edge.getClass().equals(SynchronousCallEdge.class)) {
																	SynchronousCallEdge b = (SynchronousCallEdge) edge;
																	for (ClassNode idnode : id_Node) {
																		if(b.getCenterLabel().toString().equals(idnode.getName().toString())) {
																			AssociationEdge ae = new AssociationEdge();
																			ge.changeEdge(ae);
																			workspace.getGraphFile().getGraph().connect(ae, tmp_node,
																					tmp_node.getLocationOnGraph(), idnode, idnode.getLocationOnGraph(), null);
																			t = 1;
																			inode = b.getEndNode();
																			break;
																		}
																	}
																}
																else if(edge.getClass().equals(AsynchronousCallEdge.class)) {
																	AsynchronousCallEdge b = (AsynchronousCallEdge) edge;
																	for (ClassNode idnode : id_Node) {
																		if(b.getCenterLabel().toString().equals(idnode.getName().toString())) {
																			AssociationEdge ae = new AssociationEdge();
																			ge.changeEdge(ae);
																			workspace.getGraphFile().getGraph().connect(ae, tmp_node,
																					tmp_node.getLocationOnGraph(), idnode, idnode.getLocationOnGraph(), null);
																			t = 1;
																			inode = b.getEndNode();
																			break;
																		}
																	}
																}
															}
															
															if(t == 1) 
																break;
															
															u = 1;
														}
													}
												}
											}
										}
									}
									else if(e.getClass().equals(AsynchronousCallEdge.class)) {
										AsynchronousCallEdge a = (AsynchronousCallEdge) e;
										if(a.getCenterLabel().toString().equals(tmp_node.getName().toString())) {
											for (ClassNode id_nd : id_Node) {
												if(id_nd.getName().toString().equals(tmp_node.getName().toString())) {
													AssociationEdge ie_tmp = new AssociationEdge();
													ge.changeEdge(ie_tmp);
													workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
															tmp_node.getLocationOnGraph(), id_nd, id_nd.getLocationOnGraph(), null);
													//System.out.println(tmp_node.getName() + " " + tmp_id.getName() + " " + id_nd.getName() + " " + a.getCenterLabel());
													INode inode = a.getEndNode();
													int u = 0;
													while(u==0) {
														for(IEdge edge : Edges) {
															int t = 0;
															if(edge.getStartNode().equals(inode)) {
																if(edge.getClass().equals(SynchronousCallEdge.class)) {
																	SynchronousCallEdge b = (SynchronousCallEdge) edge;
																	for (ClassNode idnode : id_Node) {
																		if(b.getCenterLabel().toString().equals(idnode.getName().toString())) {
																			AssociationEdge ae = new AssociationEdge();
																			ge.changeEdge(ae);
																			workspace.getGraphFile().getGraph().connect(ae, tmp_node,
																					tmp_node.getLocationOnGraph(), idnode, idnode.getLocationOnGraph(), null);
																			t = 1;
																			inode = b.getEndNode();
																			break;
																		}
																	}
																}
																else if(edge.getClass().equals(AsynchronousCallEdge.class)) {
																	AsynchronousCallEdge b = (AsynchronousCallEdge) edge;
																	for (ClassNode idnode : id_Node) {
																		if(b.getCenterLabel().toString().equals(idnode.getName().toString())) {
																			AssociationEdge ae = new AssociationEdge();
																			ge.changeEdge(ae);
																			workspace.getGraphFile().getGraph().connect(ae, tmp_node,
																					tmp_node.getLocationOnGraph(), idnode, idnode.getLocationOnGraph(), null);
																			t = 1;
																			inode = b.getEndNode();
																			break;
																		}
																	}
																}
															}
															
															if(t == 1) 
																break;
															
															u = 1;
														}
													}
												}
											}
										}
									}
								}
								
						//}
						
					}
				}

				for (ClassNode tmp_node : id_Node) {
					for (ClassNode m_nd : m_Node) {
						if (m_nd.getName().toString().equals(tmp_node.getName().toString())) {
							AssociationEdge ie_tmp = new AssociationEdge();
							ge.changeEdge(ie_tmp);
							workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
									tmp_node.getLocationOnGraph(), m_nd, m_nd.getLocationOnGraph(), null);
						}
					}
				}
				for (ClassNode tmp_node : m_Node) {
					//Collection<INode> node = cd.getGraph().getGraph().getAllNodes();
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
							if(temp.equals(tmp_node.getName().toString())) {
								for (ClassNode c_nd : c_Node) {
									if(c_nd.getName().toString().equals(c.getName().toString())) {
										AssociationEdge ie_tmp = new AssociationEdge();
										ge.changeEdge(ie_tmp);
										workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
												tmp_node.getLocationOnGraph(), c_nd, c_nd.getLocationOnGraph(), null);
									}
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
