package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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

import Model.Requirement;
import Model.SystemOperation;
import Model.SystemTestCase;
import Model.UMLDiagram;
import Model.UnitTestCase;
import Model.UseCase;

//traceability
public class Activity2067 extends JTabbedPane {

	private IEditorPart editorPart;
	private IEditorPart editorPart2;
	private IEditorPart editorPart3;
	
	public Activity2067(Requirement req, ArrayList<UseCase> uc, ArrayList<SystemOperation> op, ArrayList<UMLDiagram> sd,
			ArrayList<UMLDiagram> id, UMLDiagram cd, ArrayList<SystemTestCase> stc, ArrayList<UnitTestCase> utc) {
		
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
				.removeMouseListener(wp2.getScrollableEditorPart().getViewport().getView().getMouseListeners()[0]);

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

		graphClass = new UseCaseDiagramGraph().getClass();
		graphFile = new GraphFile(graphClass);
		IWorkspace workspace3 = new Workspace(graphFile);

		this.editorPart3 = workspace3.getEditorPart();
		GraphEditor ge3 = new GraphEditor(editorPart3);

		WorkspacePanel wp3 = workspace3.getAWTComponent();
		wp3.getScrollableSideBar().setVisible(false);
		wp3.getScrollableEditorPart().getViewport().getView()
				.removeMouseListener(wp3.getScrollableEditorPart().getViewport().getView().getMouseListeners()[0]);

		wp3.getScrollableEditorPart().getViewport().getView().addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent event) {

			}

			public void mouseReleased(MouseEvent event) {

			}

			public void mouseClicked(MouseEvent event) {
				ge3.highlight(event);
				wp3.refreshDisplay();
			}
		});
		
		jtabbedPane.add("Unit Testing", wp);
		jtabbedPane.addTab("System Testing", wp2);
		jtabbedPane.addTab("OOPT Traceability", wp3);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane t = (JTabbedPane) splitPane.getBottomComponent();
				if(t.getSelectedComponent().equals(wp)){
					workspace.getEditorPart().changeZoom(1);
				}
				else if(t.getSelectedComponent().equals(wp2)){
					workspace2.getEditorPart().changeZoom(1);
				}
				else {
					workspace3.getEditorPart().changeZoom(1);
				}
			}
		});

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane t = (JTabbedPane) splitPane.getBottomComponent();
				if(t.getSelectedComponent().equals(wp)){
					workspace.getEditorPart().changeZoom(-1);
				}
				else if(t.getSelectedComponent().equals(wp2)){
					workspace2.getEditorPart().changeZoom(-1);
				}
				else {
					workspace3.getEditorPart().changeZoom(-1);
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
				allNodes = workspace3.getGraphFile().getGraph().getAllNodes();
				allEdges = workspace3.getGraphFile().getGraph().getAllEdges();
				for (INode in : allNodes) {
					workspace3.getGraphFile().getGraph().removeNode(in);
				}
				for (IEdge ie : allEdges) {
					workspace3.getGraphFile().getGraph().removeEdge(ie);
				}
				int i = 1;
				ArrayList<ClassNode> req_Node = new ArrayList<>();
				ArrayList<ClassNode> uc_Node = new ArrayList<>();
				ArrayList<ClassNode> uc_Node2 = new ArrayList<>();
				ArrayList<ClassNode> st_Node = new ArrayList<>();
				ArrayList<ClassNode> st_Node2 = new ArrayList<>();
				ArrayList<ClassNode> ut_Node = new ArrayList<>();
				ArrayList<ClassNode> ut_Node2 = new ArrayList<>();
				ArrayList<ClassNode> m_Node = new ArrayList<>();
				ArrayList<ClassNode> m_Node2 = new ArrayList<>();
				ArrayList<ClassNode> op_Node = new ArrayList<>();
				ArrayList<ClassNode> id_Node = new ArrayList<>();
				
				ArrayList<String> req_name = new ArrayList<String>(req.getAllName());
		        ArrayList<String> uName = new ArrayList<String>(req.getAlluName());
		        ArrayList<String> id_name = new ArrayList<>();
				
		        for (UMLDiagram tmp_Graph : id) {
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
				
				for(String tmp_req : req_name) {
					ClassNode tmp_reqnode = new ClassNode();
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
					ClassNode tmp_ucnode = new ClassNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_uc.getName());
		        	tmp_ucnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(400.0, i*10.0);
		        	uc_Node.add(tmp_ucnode);
		        	workspace2.getGraphFile().getGraph().addNode(tmp_ucnode, tmp_xy);
		        	
		        	tmp_ucnode = new ClassNode();
		        	tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_uc.getName());
		        	tmp_ucnode.setName(tmp_name);
		        	uc_Node2.add(tmp_ucnode);
		        	workspace3.getGraphFile().getGraph().addNode(tmp_ucnode, tmp_xy);
		        	i=i+5;
		        }
				i=1;
				for(SystemTestCase tmp_stc : stc) {
					ClassNode tmp_stcnode = new ClassNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_stc.getName());
		        	tmp_stcnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(800.0, i*10.0);
		        	st_Node.add(tmp_stcnode);
		        	workspace2.getGraphFile().getGraph().addNode(tmp_stcnode, tmp_xy);
		        	
		        	tmp_stcnode = new ClassNode();
		        	tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_stc.getName());
		        	tmp_stcnode.setName(tmp_name);
		        	tmp_xy = new Point2D.Double(10.0, i*10.0);
		        	st_Node2.add(tmp_stcnode);
		        	workspace3.getGraphFile().getGraph().addNode(tmp_stcnode, tmp_xy);
		        	i=i+5;
		        }
				
				Collection<INode> node = cd.getGraph().getGraph().getAllNodes();
				List<INode> inode_list = new ArrayList<>();
				inode_list.addAll(node);
				Collections.reverse(inode_list);
				i = 1;
				for (INode s : inode_list) {
					ClassNode c = (ClassNode) s;
					StringTokenizer t = new StringTokenizer(c.getMethods().toString(), "|");
					for (int j = 0; t.hasMoreTokens(); j++) {
						StringTokenizer u = new StringTokenizer(t.nextToken(), " ");
						for (int l = 0; l < 2; l++) {
							u.nextToken();
						}
						String temp = c.getName().toString() + " : " + u.nextToken();
						while (u.hasMoreTokens()) {
							temp = temp + " " + u.nextToken();
						}
						ClassNode tmp_mnode = new ClassNode();
						SingleLineText tmp_name = new SingleLineText();
						tmp_name.setText(temp);
						tmp_mnode.setName(tmp_name);
						Point2D tmp_xy = new Point2D.Double(10.0, i * 10.0);
						m_Node.add(tmp_mnode);
						workspace.getGraphFile().getGraph().addNode(tmp_mnode, tmp_xy);
						
						tmp_mnode = new ClassNode();
						tmp_name = new SingleLineText();
						tmp_name.setText(temp);
						tmp_mnode.setName(tmp_name);
						m_Node2.add(tmp_mnode);
						tmp_xy = new Point2D.Double(3000.0, i * 10.0);
						workspace3.getGraphFile().getGraph().addNode(tmp_mnode, tmp_xy);
						i = i + 5;
					}
				}
				i = 1;
				for(UnitTestCase tmp_utc : utc) {
					ClassNode tmp_utcnode = new ClassNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_utc.getNumber() + ". " + tmp_utc.getName());
		        	tmp_utcnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(1000.0, i * 10.0);
		        	ut_Node.add(tmp_utcnode);
		        	workspace.getGraphFile().getGraph().addNode(tmp_utcnode, tmp_xy);
		        	
		        	tmp_utcnode = new ClassNode();
		        	tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_utc.getNumber() + ". " + tmp_utc.getName());
		        	tmp_utcnode.setName(tmp_name);
		        	tmp_xy = new Point2D.Double(4000.0, i * 10.0);
		        	ut_Node2.add(tmp_utcnode);
		        	workspace3.getGraphFile().getGraph().addNode(tmp_utcnode, tmp_xy);
		        	i=i+5;
				}
				i = 1;
				for (SystemOperation tmp_op : op) {
					ClassNode tmp_opnode = new ClassNode();
					SingleLineText tmp_name = new SingleLineText();
					tmp_name.setText(tmp_op.getOp_name());
					tmp_opnode.setName(tmp_name);
					Point2D tmp_xy = new Point2D.Double(1000.0, i * 10.0);
					op_Node.add(tmp_opnode);
					workspace3.getGraphFile().getGraph().addNode(tmp_opnode, tmp_xy);
					i = i + 5;
				}
				i = 1;

				for (String tmp_id : id_name) {
					ClassNode tmp_idnode = new ClassNode();
					SingleLineText tmp_name = new SingleLineText();
					tmp_name.setText(tmp_id);
					tmp_idnode.setName(tmp_name);
					Point2D tmp_xy = new Point2D.Double(2000.0, i * 10.0);
					id_Node.add(tmp_idnode);
					workspace3.getGraphFile().getGraph().addNode(tmp_idnode, tmp_xy);
					i = i + 5;
				}
				
				for(ClassNode tmp_node : req_Node) {
		        	for(int index=0; index<req_name.size(); index++) {
		        		if(req_name.get(index).equals(tmp_node.getName().toString())){
		        			String related_uc = req.getuName(index);
		        			for(ClassNode uc_nd : uc_Node) {
		        				if(uc_nd.getName().toString().equals(related_uc)) {
		        					AssociationEdge ie_tmp = new AssociationEdge();
		        					ge2.changeEdge(ie_tmp);
		        					workspace2.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), uc_nd, uc_nd.getLocationOnGraph(), null);	
		        				}
		        			}
		        		}
		        	}	
		        }
				for(ClassNode tmp_node : uc_Node) {
					for(SystemTestCase temp : stc) {
						String uc = temp.getUsecase();
						StringTokenizer st = new StringTokenizer(uc, " ");
						if(st.hasMoreTokens()) {
							st.nextToken();
							String name = st.nextToken();
							while(st.hasMoreTokens()) {
								name = name + " " + st.nextToken();
							}
							if(name.equals(tmp_node.getName().toString())) {
								for(ClassNode st_nd : st_Node) {
									if(st_nd.getName().toString().equals(temp.getName())) {
										AssociationEdge ie_tmp = new AssociationEdge();
			        					ge2.changeEdge(ie_tmp);
			        					workspace2.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), st_nd, st_nd.getLocationOnGraph(), null);
									}
								}
							}
						}
					}
				}
				for(ClassNode tmp_node : uc_Node2) {
					for(SystemTestCase temp : stc) {
						String uc = temp.getUsecase();
						StringTokenizer st = new StringTokenizer(uc, " ");
						if(st.hasMoreTokens()) {
							st.nextToken();
							String name = st.nextToken();
							while(st.hasMoreTokens()) {
								name = name + " " + st.nextToken();
							}
							if(name.equals(tmp_node.getName().toString())) {
								for(ClassNode st_nd : st_Node2) {
									if(st_nd.getName().toString().equals(temp.getName())) {
										AssociationEdge ie_tmp = new AssociationEdge();
			        					ge2.changeEdge(ie_tmp);
			        					workspace3.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), st_nd, st_nd.getLocationOnGraph(), null);
									}
								}
							}
						}
					}
				}
				for(ClassNode tmp_node : uc_Node2) {
					for(UMLDiagram tmp_graph : sd) {
		        		if(tmp_node.getName().toString().equals(tmp_graph.getName())) {
		        			Collection<IEdge> tmp_allEdges = tmp_graph.getGraph().getGraph().getAllEdges();
		        			for(IEdge aEdge : tmp_allEdges) {
		        				if(aEdge.getClass().equals(SynchronousCallEdge.class)) {
		        					SynchronousCallEdge a =(SynchronousCallEdge)aEdge;
		        					for(SystemOperation so : op) {
		        						if(a.getCenterLabel().toString().equals(so.getName())) {
		        							for(ClassNode op_node : op_Node) {
		        								if(so.getOp_name().equals(op_node.getName().toString())) {
		        									AssociationEdge ie_tmp = new AssociationEdge();
		        									ge3.changeEdge(ie_tmp);
						        					workspace3.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), op_node, op_node.getLocationOnGraph(), null);
		        								}
		        							}
		        						}
		        					}
		        				}
		        				else if(aEdge.getClass().equals(AsynchronousCallEdge.class)) {
		        					AsynchronousCallEdge a =(AsynchronousCallEdge)aEdge;
		        					for(SystemOperation so : op) {
		        						if(a.getCenterLabel().toString().equals(so.getName())) {
		        							for(ClassNode op_node : op_Node) {
		        								if(so.getOp_name().equals(op_node.getName().toString())) {
		        									AssociationEdge ie_tmp = new AssociationEdge();
		        									ge3.changeEdge(ie_tmp);
						        					workspace3.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), op_node, op_node.getLocationOnGraph(), null);
		        								}
		        							}
		        						}
		        					}
		        				}
		        			}
		        		}
		        	}
				}
				for (ClassNode tmp_node : m_Node) {
					StringTokenizer a = new StringTokenizer(tmp_node.getName().toString(), " ");
					for (int l = 0; l < 2; l++) {
						a.nextToken();
					}
					String name = a.nextToken();
					while (a.hasMoreTokens()) {
						name = name + " " + a.nextToken();
					}
					for (UnitTestCase temp : utc) {
						if(temp.getName().equals(name)) {
							for(ClassNode ut_nd : ut_Node) {
								if(ut_nd.getName().toString().equals(temp.getNumber() + ". " + temp.getName())) {
									AssociationEdge ie_tmp = new AssociationEdge();
		        					ge.changeEdge(ie_tmp);
		        					workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), ut_nd, ut_nd.getLocationOnGraph(), null);
								}
							}
						}
					}
				}
				

				for (ClassNode tmp_node : op_Node) {
					for (UMLDiagram tmp_id : id) {
						Collection<IEdge> Edges = tmp_id.getGraph().getGraph().getAllEdges();
						for(IEdge e :Edges) {
							if(e.getClass().equals(SynchronousCallEdge.class)) {
								SynchronousCallEdge a = (SynchronousCallEdge) e;
								if(a.getCenterLabel().toString().equals(tmp_node.getName().toString())) {
									for (ClassNode id_nd : id_Node) {
										if(id_nd.getName().toString().equals(tmp_node.getName().toString())) {
											AssociationEdge ie_tmp = new AssociationEdge();
											ge3.changeEdge(ie_tmp);
											workspace3.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
													tmp_node.getLocationOnGraph(), id_nd, id_nd.getLocationOnGraph(), null);
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
																	ge3.changeEdge(ae);
																	workspace3.getGraphFile().getGraph().connect(ae, tmp_node,
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
																	ge3.changeEdge(ae);
																	workspace3.getGraphFile().getGraph().connect(ae, tmp_node,
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
											ge3.changeEdge(ie_tmp);
											workspace3.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
													tmp_node.getLocationOnGraph(), id_nd, id_nd.getLocationOnGraph(), null);
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
																	ge3.changeEdge(ae);
																	workspace3.getGraphFile().getGraph().connect(ae, tmp_node,
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
																	ge3.changeEdge(ae);
																	workspace3.getGraphFile().getGraph().connect(ae, tmp_node,
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
					}
				}

				for (ClassNode tmp_node : id_Node) {
					for (ClassNode m_nd : m_Node2) {
						StringTokenizer t = new StringTokenizer(m_nd.getName().toString()," ");
						for (int l = 0; l < 2; l++) {
							t.nextToken();
						}
						String name = t.nextToken();
						while (t.hasMoreTokens()) {
							name = name + " " + t.nextToken();
						}
						if (name.equals(tmp_node.getName().toString())) {
							AssociationEdge ie_tmp = new AssociationEdge();
							ge3.changeEdge(ie_tmp);
							workspace3.getGraphFile().getGraph().connect(ie_tmp, tmp_node,
									tmp_node.getLocationOnGraph(), m_nd, m_nd.getLocationOnGraph(), null);
						}
					}
				}
				
				for (ClassNode tmp_node : m_Node2) {
					StringTokenizer a = new StringTokenizer(tmp_node.getName().toString(), " ");
					for (int l = 0; l < 2; l++) {
						a.nextToken();
					}
					String name = a.nextToken();
					while (a.hasMoreTokens()) {
						name = name + " " + a.nextToken();
					}
					for (UnitTestCase temp : utc) {
						if(temp.getName().equals(name)) {
							for(ClassNode ut_nd : ut_Node2) {
								if(ut_nd.getName().toString().equals(temp.getNumber() + ". " + temp.getName())) {
									AssociationEdge ie_tmp = new AssociationEdge();
		        					ge3.changeEdge(ie_tmp);
		        					workspace3.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), ut_nd, ut_nd.getLocationOnGraph(), null);
								}
							}
						}
					}
				}
				
				workspace.getAWTComponent().refreshDisplay();
				workspace2.getAWTComponent().refreshDisplay();
				workspace3.getAWTComponent().refreshDisplay();
			}

		});
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(jtabbedPane);
		splitPane.setTopComponent(jpanel);
		addTab("Traceability Analysis", null, splitPane, null);
	}

}
