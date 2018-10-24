package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.metal.MetalIconFactory;

import Model.Datainfo;
import Model.Glossary;
import Model.Graph;
import Model.NonFuncReq;
import Model.Requirement;
import Model.Risk;
import Model.Schedule;
import Model.Simulation;
import Model.StageText;
import Model.SystemOperation;
import Model.SystemTestCase;
import Model.UnitTestCase;
import Model.UseCase;

public class GUI {
	private int divSize = 400;
	private JFrame frame;
	private ArrayList<Glossary> gl2;
	private ArrayList<UseCase> ruc;
	private ArrayList<SystemTestCase> stc = new ArrayList<>();
	private ArrayList<UnitTestCase> utc = new ArrayList<>();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ClassNotFoundException
	 */
	public GUI(Requirement req, ArrayList risk, ArrayList gl, ArrayList uc, ArrayList op, ArrayList std, ArrayList sd,
			ArrayList id, Graph ud, Graph cd, Graph dm, Graph sa, Datainfo data) {
		initialize(req, risk, gl, uc, op, std, sd, id, ud, cd, dm, sa, data);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private Hashtable makeIcons() {
		Hashtable icons = new Hashtable();
		icons.put("floppyDrive", MetalIconFactory.getTreeFloppyDriveIcon());
		icons.put("hardDrive", MetalIconFactory.getTreeHardDriveIcon());
		icons.put("computer", MetalIconFactory.getTreeComputerIcon());
		icons.put("c", TextIcons.getIcon("c"));
		icons.put("java", TextIcons.getIcon("java"));
		icons.put("html", TextIcons.getIcon("html"));
		return icons;
	}

	private void initialize(Requirement req, ArrayList<Risk> risk, ArrayList<Glossary> gl, ArrayList<UseCase> uc,
			ArrayList<SystemOperation> op, ArrayList<Graph> std, ArrayList<Graph> sd, ArrayList<Graph> id, Graph ud,
			Graph cd, Graph dm, Graph sa, Datainfo data) {
		gl2 = new ArrayList<>();
		ruc = new ArrayList<>();
		frame = new JFrame();
		
		frame.setBounds(100, 100, 928, 617);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		// splitPane.disable();
		// splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		// splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(splitPane);

		String[] strs = { "OOPT", // 0
				"Stage1000 - Plan and Elaboration", // 1
				"Activity 1001 - Define Draft Plan", // 2
				"Activity 1002 - Create Preliminary Investigation Report", // 3
				"Activity 1003 - Define Requirements", // 4
				"Activity 1004 - Record Terms in Glossary", // 5
				"Activity 1005 - Implement Prototype", // 6
				"Activity 1006 - Define Business Use Case", // 7
				"Activity 1007 - Define Business Concept Model", // 8
				"Activity 1008 - Define Draft System Architecture", // 9
				"Activity 1009 - Define System Test Case", // 10
				"Activity 1010 - Refine Plan", // 11
				"Stage2000 - Build", // 12
				"Stage2030 - Analyze", // 13
				"Activity 2031 - Define Essential Use Cases", // 14
				"Activity 2032 - Refine Use Case Diagrams", // 15
				"Activity 2033 - Define Domain Model", // 16
				"Activity 2034 - Refine Glossary", // 17
				"Activity 2035 - Define System Sequence Diagrams", // 18
				"Activity 2036 - Define Operation Contracts", // 19
				"Activity 2037 - Define State Diagrams", // 20
				"Activity 2038 - Refine System Test Case", // 21
				"Activity 2039 - 2030 Phase Traceability Analysis", // 22
				"Stage2040 - Design", // 23
				"Activity 2041 - Design Real Use Cases", // 24
				"Activity 2042 - Define Reports, UI and Storyboards", // 25
				"Activity 2043 - Refine System Architecture", // 26
				"Activity 2044 - Define Interaction Diagrams", // 27
				"Activity 2045 - Define Design Class Diagrams", // 28
				"Activity 2046 - Design Traceability Analysis", // 29
				"Stage2050 - Construct", // 30
				"Activity 2051 - Implement Class & Methods Definitions", // 31
				"Activity 2052 - Implement Windows", // 32
				"Activity 2053 - Implement Reports", // 33
				"Activity 2054 - Implement DB Schema", // 34
				"Activity 2055 - Write Unit Test Code", // 35
				"Stage2060 - Test", // 36
				"Activity 2061 - Unit Testing", // 37
				"Activity 2062 - Integration Testing", // 38
				"Activity 2063 - System Testing", // 39
				"Activity 2064 - Performance Testing", // 40
				"Activity 2065 - Acceptance Testing", // 41
				"Activity 2066 - Documentation Testing", // 42
				"Activity 2067 - Testing Traceability Analysis", // 43
		};
		IconNode[] nodes = new IconNode[strs.length];
		for (int i = 0; i < strs.length; i++) {
			nodes[i] = new IconNode(strs[i]);
		}
		nodes[0].add(nodes[1]);
		nodes[0].add(nodes[12]);
		nodes[12].add(nodes[13]);
		nodes[12].add(nodes[23]);
		nodes[12].add(nodes[30]);
		nodes[12].add(nodes[36]);
		for (int i = 2; i < 12; i++) {
			nodes[1].add(nodes[i]);
		}
		for (int i = 14; i < 23; i++) {
			nodes[13].add(nodes[i]);
		}
		for (int i = 24; i < 30; i++) {
			nodes[23].add(nodes[i]);
		}
		for (int i = 31; i < 36; i++) {
			nodes[30].add(nodes[i]);
		}
		for (int i = 37; i < 44; i++) {
			nodes[36].add(nodes[i]);
		}
		nodes[0].setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
		for (int i = 1; i < 44; i++) {
			if (i == 1 || i == 12 || i == 13 || i == 23 || i == 30 || i == 36) {
				nodes[i].setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
			} else {
				nodes[i].setIconName("computer");
			}
		}

		// tree
		JTree tree = new JTree(nodes[0]);
		tree.putClientProperty("JTree.icons", makeIcons());
		tree.setCellRenderer(new IconNodeRenderer());
		// stage
		Oopt oopt = new Oopt();
		Stage1000 s1000 = new Stage1000();
		Stage2000 s2000 = new Stage2000();
		Stage2030 s2030 = new Stage2030();
		Stage2040 s2040 = new Stage2040();
		Stage2050 s2050 = new Stage2050();
		Stage2060 s2060 = new Stage2060();
		// activity
		// UMLEditorApplication u = new UMLEditorApplication();
		Activity1001 a1001 = new Activity1001(tree, data);
		Activity1002 a1002 = new Activity1002(tree, risk, data);
		Activity1003 a1003 = new Activity1003(tree, req, data);
		Activity1004 a1004 = new Activity1004(tree, gl, data);
		Activity1005 a1005 = new Activity1005(tree);
		Activity1006 a1006 = new Activity1006(tree, req, uc, ud, sd, id, std, data);
		Activity1007 a1007 = new Activity1007(tree, data);
		Activity1008 a1008 = new Activity1008(tree);
		Activity1009 a1009 = new Activity1009(tree, req, data);
		Activity1010 a1010 = new Activity1010(tree, req, data);
		Activity2031 a2031 = new Activity2031(tree, uc, data);
		Activity2032 a2032 = new Activity2032();
		Activity2033 a2033 = new Activity2033(dm, data);
		Activity2034 a2034 = new Activity2034(tree, gl2, data);
		Activity2035 a2035 = new Activity2035(tree, op, sd, data);
		Activity2036 a2036 = new Activity2036(tree, op, data);
		Activity2037 a2037 = new Activity2037(std, data);
		Activity2038 a2038 = new Activity2038(tree, req, uc, stc, data);
		Activity2039 a2039 = new Activity2039(req, uc, op, sd);
		Activity2041 a2041 = new Activity2041(tree, ruc, data);
		Activity2042 a2042 = new Activity2042(id);
		Activity2043 a2043 = new Activity2043(sa, data);
		Activity2044 a2044 = new Activity2044(id, data);
		Activity2045 a2045 = new Activity2045(cd, data);
		Activity2046 a2046 = new Activity2046(op, id, cd);
		Activity2051 a2051 = new Activity2051();
		Activity2052 a2052 = new Activity2052();
		Activity2053 a2053 = new Activity2053();
		Activity2054 a2054 = new Activity2054();
		Activity2055 a2055 = new Activity2055();
		Activity2061 a2061 = new Activity2061(tree, utc, data);
		Activity2062 a2062 = new Activity2062();
		Activity2063 a2063 = new Activity2063(tree, stc, data);
		Activity2064 a2064 = new Activity2064();
		Activity2065 a2065 = new Activity2065();
		Activity2066 a2066 = new Activity2066();
		Activity2067 a2067 = new Activity2067(req, uc, op, sd, id, cd, stc, utc);

		Simulation sm = new Simulation(a2042);
		
		JScrollPane jtree = new JScrollPane(tree);
		splitPane.setDividerLocation(divSize);
		splitPane.setLeftComponent(jtree);

		JMenuBar menuBar_1 = new JMenuBar();
		frame.setJMenuBar(menuBar_1);

		JMenu File = new JMenu("File");
		menuBar_1.add(File);
		
		JMenuItem mntmNew = new JMenuItem("New");
		File.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		File.add(mntmOpen);

		mntmOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Open?", "Warning", dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION) {
					ArrayList<StageText> st = new ArrayList<>();
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<StageText> temp = data.getSearchText();
							st.addAll(temp);
						}
						
					});
					
					Thread thread2 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<Risk> temp = data.getSearchRisk();
							risk.clear();
							risk.addAll(temp);
						}
						
					});
					
					Thread thread3 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Requirement temp = data.getSearchReq();
							req.clear();
							for (int i = 0; i < temp.get_length(); i++) {
								req.add_row();
								req.setRef(temp.getRef(i), i);
								req.setName(temp.getName(i), i);
								req.setCategory(temp.getCategory(i), i);
								req.setuCategory(temp.getuCategory(i), i);
								req.setuNumber(temp.getuNumber(i), i);
								req.setuName(temp.getuName(i), i);
								req.setRank(temp.getRank(i), i);
								req.setTestcase(temp.getTestcase(i), i);
							}
						}
						
					});
					Thread thread4 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<Glossary> g = data.getSearchGl("D");
							gl.clear();
							gl.addAll(g);
							
						}
						
					});
					Thread thread5 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<Glossary> g2 = data.getSearchGl("R");
							gl2.clear();
							gl2.addAll(g2);
							
						}
						
					});
	
					ArrayList<String> concept = new ArrayList<>();
					Thread thread6 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<String> temp = data.getSearchConcept();
							concept.addAll(temp);
							
						}
						
					});
					ArrayList<String> ausecase = new ArrayList<>();
					Thread thread7 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<String> temp = data.getSearchBUsecase("A");
							ausecase.addAll(temp);
							
						}
						
					});
					ArrayList<String> eusecase = new ArrayList<>();
					Thread thread8 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<String> temp = data.getSearchBUsecase("E");
							eusecase.addAll(temp);
							
						}
						
					});
					ArrayList<UseCase> u = data.getSearchUseCase();
					Thread thread9 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<UseCase> u = data.getSearchUseCase();
							uc.clear();
							uc.addAll(u);
						}
						
					});
	
					ArrayList<Schedule> sc = new ArrayList<>();
					Thread thread10 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<Schedule> temp = data.getSearchSche();
							sc.addAll(temp);
						}
						
					});
					
					ArrayList<NonFuncReq> nreq = new ArrayList<>();
					Thread thread11 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<NonFuncReq> temp = data.getSearchNonReq("D");
							nreq.addAll(temp);
						}
						
					});
					Thread thread12 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Graph tmp_ud = data.getSearchGraph("ud").get(0);
							ud.setGraph(tmp_ud.getGraph());
							ud.setId(tmp_ud.getId());
							ud.setName(tmp_ud.getName());
						}
						
					});
					Thread thread13 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Graph tmp_dm = data.getSearchGraph("dm").get(0);
							dm.setGraph(tmp_dm.getGraph());
							dm.setId(tmp_dm.getId());
							dm.setName(tmp_dm.getName());
						}
						
					});
					Thread thread14 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Graph tmp_sa = data.getSearchGraph("sa").get(0);
							sa.setGraph(tmp_sa.getGraph());
							sa.setId(tmp_sa.getId());
							sa.setName(tmp_sa.getName());
						}
						
					});
					Thread thread15 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Graph tmp_cd = data.getSearchGraph("cd").get(0);
							cd.setGraph(tmp_cd.getGraph());
							cd.setId(tmp_cd.getId());
							cd.setName(tmp_cd.getName());
						}
						
					});
					
					Thread thread16 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<Graph> tmpSd = data.getSearchGraph("sd");
							sd.clear();
							sd.addAll(tmpSd);
						}
						
					});
					Thread thread17 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<Graph> tmpStd = data.getSearchGraph("std");
							std.clear();
							std.addAll(tmpStd);
						}
						
					});
					Thread thread18 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<Graph> tmpId = data.getSearchGraph("id");
							id.clear();
							id.addAll(tmpId);
						}
						
					});
					Thread thread19 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<UseCase> ructmp = data.getSearchRealUseCase();
							ruc.clear();
							ruc.addAll(ructmp);
						}
						
					});
					Thread thread20 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<SystemOperation> s = data.getSearchOperation();
							op.clear();
							op.addAll(s);
						}
						
					});
					ArrayList<NonFuncReq> nreq2 = new ArrayList<>();
					Thread thread21 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<NonFuncReq> temp = data.getSearchNonReq("R");
							nreq2.addAll(temp);
						}
						
					});
					Thread thread22 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<SystemTestCase> stc2 = data.getSearchSystemTC();
							stc.clear();
							stc.addAll(stc2);
						}
						
					});
					Thread thread23 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ArrayList<UnitTestCase> tmpUtc = data.getSearchTC("U");
							utc.clear();
							utc.addAll(tmpUtc);
						}
						
					});
					
					thread.start();
					thread2.start();
					thread3.start();
					thread4.start();
					thread5.start();
					thread6.start();
					thread7.start();
					thread8.start();
					thread9.start();
					thread10.start();
					thread11.start();
					thread12.start();
					thread13.start();
					thread14.start();
					thread15.start();
					thread16.start();
					thread17.start();
					thread18.start();
					thread19.start();
					thread20.start();
					thread21.start();
					thread22.start();
					thread23.start();
					
					try {
						thread4.join();
						a1004.open(gl);
						thread5.join();
						a2034.open(gl2);
						thread6.join();
						a1007.open(concept);
						thread7.join();
						thread8.join();
						thread9.join();
						a2031.open(uc);
						thread10.join();
						thread12.join();
						thread13.join();
						a2033.open(dm);
						thread14.join();
						a2043.open(sa);
						thread15.join();
						a2045.open(cd);
						thread16.join();
						thread17.join();
						thread18.join();
						thread19.join();
						a2041.open(ruc);
						thread20.join();
						a2036.open(op);
						thread.join();
						a1001.open(st);
						thread2.join();
						a1002.open(st, risk);
						thread3.join();
						a1003.open(st, req);
						thread11.join();
						a1009.open(req, nreq);
						thread21.join();
						thread22.join();
						a2038.open(stc, nreq2);
						a2063.open(st, stc);
						a1006.open(st, ausecase, eusecase, ud, uc);
						a1010.open(st, sc);
						thread23.join();
						a2061.open(st, utc);
						a2062.open(st, utc);
						
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		JMenuItem mntmSave = new JMenuItem("Save");
		File.add(mntmSave);

		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Save?", "Warning", dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION) {
					IconNode node = (IconNode) tree.getLastSelectedPathComponent();
					int index;
					if (node == null) {
						return;
					}
					// 1000
					else if (node.getParent().equals(node.getRoot().getChildAt(0))) {
						index = node.getParent().getIndex(node);
						switch (index) {
						case 0:
							a1001.save(data);
							nodes[2].setIconName("floppyDrive");
							break;
						case 1:
							a1002.save(data, risk);
							nodes[3].setIconName("floppyDrive");
							break;
						case 2:
							a1003.save(data, req);
							nodes[4].setIconName("floppyDrive");
							break;
						case 3:
							a1004.save(data, gl);
							nodes[5].setIconName("floppyDrive");
							break;
						case 4:
							nodes[6].setIconName("floppyDrive");
							break;
						case 5:
							a1006.save(data, req, uc);
							nodes[7].setIconName("floppyDrive");
							break;
						case 6:
							a1007.save(data);
							nodes[8].setIconName("floppyDrive");
							break;
						case 7:
							nodes[9].setIconName("floppyDrive");
							break;
						case 8:
							a1009.save(data, req);
							nodes[10].setIconName("floppyDrive");
							break;
						case 9:
							a1010.save(data);
							nodes[11].setIconName("floppyDrive");
							break;
						default:
							break;
						}
					}
					// 2030
					else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))) {
						index = node.getParent().getIndex(node);
						switch (index) {
						case 0:
							a2031.save(data, uc);
							nodes[14].setIconName("floppyDrive");
							break;
						case 1:
							nodes[15].setIconName("floppyDrive");
							break;
						case 2:
							a2033.save(data, dm);
							nodes[16].setIconName("floppyDrive");
							break;
						case 3:
							a2034.save(data, gl2);
							nodes[17].setIconName("floppyDrive");
							break;
						case 4:
							a2035.save(data, sd);
							nodes[18].setIconName("floppyDrive");
							break;
						case 5:
							a2036.save(data, op);
							nodes[19].setIconName("floppyDrive");
							break;
						case 6:
							a2037.save(data, std);
							nodes[20].setIconName("floppyDrive");
							break;
						case 7:
							nodes[21].setIconName("floppyDrive");
							break;
						case 8:
							nodes[22].setIconName("floppyDrive");
							break;
						default:
							break;
						}
					}
					// 2040
					else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(1))) {
						index = node.getParent().getIndex(node);
						switch (index) {
						case 0:
							a2041.save(data, ruc);
							nodes[24].setIconName("floppyDrive");
							break;
						case 1:
							nodes[25].setIconName("floppyDrive");
							break;
						case 2:
							a2043.save(data, sa);
							nodes[26].setIconName("floppyDrive");
							break;
						case 3:
							a2044.save(data, id);
							nodes[27].setIconName("floppyDrive");
							break;
						case 4:
							a2045.save(data, cd);
							nodes[28].setIconName("floppyDrive");
							break;
						case 5:
							nodes[29].setIconName("floppyDrive");
							break;
						// case 6 :splitPane.setRightComponent(a2047);
						// break;
						default:
							break;
						}
					}
					// 2050
					else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(2))) {
						index = node.getParent().getIndex(node);
						switch (index) {
						case 0:
							nodes[31].setIconName("floppyDrive");
							break;
						case 1:
							nodes[32].setIconName("floppyDrive");
							break;
						case 2:
							nodes[33].setIconName("floppyDrive");
							break;
						case 3:
							nodes[34].setIconName("floppyDrive");
							break;
						case 4:
							nodes[35].setIconName("floppyDrive");
							break;
						default:
							break;
						}
					}
					// 2060
					else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(3))) {
						index = node.getParent().getIndex(node);
						switch (index) {
						case 0:
							a2061.save(data);
							nodes[37].setIconName("floppyDrive");
							break;
						case 1:
							a2062.save(data);
							nodes[38].setIconName("floppyDrive");
							break;
						case 2:
							a2063.save(data);
							nodes[39].setIconName("floppyDrive");
							break;
						case 3:
							nodes[40].setIconName("floppyDrive");
							break;
						case 4:
							nodes[41].setIconName("floppyDrive");
							break;
						case 5:
							nodes[42].setIconName("floppyDrive");
							break;
						case 6:
							nodes[43].setIconName("floppyDrive");
							break;
						default:
							break;
						}
					}
					tree.putClientProperty("JTree.icons", makeIcons());
				}
			}
		});
		JMenuItem mntmExit = new JMenuItem("Exit");
		File.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		JMenu mnNewMenu_1 = new JMenu("Simulation");
		menuBar_1.add(mnNewMenu_1);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				int index;
				if (node == null) {
					return;
				}
				// stage
				if (node.equals(node.getRoot())) {
					splitPane.setRightComponent(oopt);
					splitPane.setDividerLocation(divSize);
				} else if (node.getParent().equals(node.getRoot())) {
					index = node.getParent().getIndex(node);
					JComponent c = null;
					switch (index) {
					case 0:
						c = s1000;
						break;
					case 1:
						c = s2000;
						break;
					default:
						break;
					}
					splitPane.setRightComponent(c);
					splitPane.setDividerLocation(divSize);
				} else if (node.getParent().equals(node.getRoot().getChildAt(0))) {
					index = node.getParent().getIndex(node);
					JComponent c = null;
					switch (index) {
					case 0:
						c = a1001;
						break;
					case 1:
						c = a1002;
						break;
					case 2:
						a1003.syncRequirement(req);
						c = a1003;
						break;
					case 3:
						c = a1004;
						break;
					case 4:
						c = a1005;
						break;
					case 5:
						a1006.sync(req);
						c = a1006;
						break;
					case 6:
						c = a1007;
						break;
					case 7:
						c = a1008;
						break;
					case 8:
						a1009.syncComboBox(req.getAllName());
						c = a1009;
						break;
					case 9:
						a1010.syncRequirement(req);
						c = a1010;
						break;
					default:
						break;
					}
					splitPane.setRightComponent(c);
					splitPane.setDividerLocation(divSize);

				} else if (node.getParent().equals(node.getRoot().getChildAt(1))) {
					index = node.getParent().getIndex(node);
					JComponent c = null;
					switch (index) {
					case 0:
						c = s2030;
						break;
					case 1:
						c = s2040;
						break;
					case 2:
						c = s2050;
						break;
					case 3:
						c = s2060;
						break;
					default:
						break;
					}
					splitPane.setRightComponent(c);
					splitPane.setDividerLocation(divSize);
				} else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))) {
					index = node.getParent().getIndex(node);
					JComponent c = null;
					switch (index) {
					case 0:
						a2031.syncUseCase(uc);
						c = a2031;
						break;
					case 1:
						c = a2032;
						break;
					case 2:
						c = a2033;
						break;
					case 3:
						c = a2034;
						break;
					case 4:
						a2035.syncComboBox(sd);
						c = a2035;
						break;
					case 5:
						a2036.syncOperation(op);
						c = a2036;
						break;
					case 6:
						a2037.syncComboBox(std);
						c = a2037;
						break;
					case 7:
						a2038.syncComboBox(req);
						c = a2038;
						break;
					case 8:
						c = a2039;
						break;
					default:
						break;
					}
					splitPane.setRightComponent(c);
					splitPane.setDividerLocation(divSize);
				} else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(1))) {
					index = node.getParent().getIndex(node);
					JComponent c = null;
					switch (index) {
					case 0:
						a2041.syncUseCase(uc, ruc);
						c = a2041;
						break;
					case 1:
						c = a2042;
						break;
					case 2:
						c = a2043;
						break;
					case 3:
						a2044.syncComboBox(id);
						c = a2044;
						break;
					case 4:
						c = a2045;
						break;
					case 5:
						c = a2046;
						break;
					// case 6 :splitPane.setRightComponent(a2047);
					// break;
					default:
						break;
					}
					splitPane.setRightComponent(c);
					splitPane.setDividerLocation(divSize);
				} else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(2))) {
					index = node.getParent().getIndex(node);
					JComponent c = null;
					switch (index) {
					case 0:
						c = a2051;
						break;
					case 1:
						c = a2052;
						break;
					case 2:
						c = a2053;
						break;
					case 3:
						c = a2054;
						break;
					case 4:
						c = a2055;
						break;
					default:
						break;
					}
					splitPane.setRightComponent(c);
					splitPane.setDividerLocation(divSize);
				} else if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(3))) {
					index = node.getParent().getIndex(node);
					JComponent c = null;
					switch (index) {
					case 0:
						c = a2061;
						break;
					case 1:
						c = a2062;
						break;
					case 2: 
						a2063.syncSystemTestCase(stc);
						c = a2063;
						break;
					case 3:
						c = a2064;
						break;
					case 4:
						c = a2065;
						break;
					case 5:
						c = a2066;
						break;
					case 6:
						c = a2067;
						break;
					default:
						break;
					}
					splitPane.setRightComponent(c);
					splitPane.setDividerLocation(divSize);
				}
			}
		});

		frame.setVisible(true);
	}

	// testing code

}
