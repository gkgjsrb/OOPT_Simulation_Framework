package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Model.Requirement;
import Model.Risk;
public class GUI {

	private JFrame frame;

	public GUI(Requirement req, Risk risk, ArrayList uc)  {
		initialize(req, risk, uc);
	}
	
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
	
	private void initialize(Requirement req, Risk risk, ArrayList uc) {
		frame = new JFrame();
		frame.setBounds(100, 100, 928, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();

		splitPane.setResizeWeight(0.2);
		splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(splitPane);
		
		 String[] strs = { "OOPT", // 0
			        "Stage1000", // 1
			        "Activity 1001", //2
			        "Activity 1002", //3
			        "Activity 1003", //4
			        "Activity 1004", //5
			        "Activity 1005", //6
			        "Activity 1006", //7
			        "Activity 1007", //8
			        "Activity 1008", //9
			        "Activity 1009", //10
			        "Activity 1010", //11
			        "Stage2000", // 12
			        "Stage2030", // 13
			        "Activity 2031", //14
			        "Activity 2032", //15
			        "Activity 2033", //16
			        "Activity 2034", //17
			        "Activity 2035", //18
			        "Activity 2036", //19
			        "Activity 2037", //20
			        "Activity 2038", //21
			        "Activity 2039", //22
			        "Stage2040", // 23
			        "Activity 2041", //24
			        "Activity 2042", //25
			        "Activity 2043", //26
			        "Activity 2044", //27
			        "Activity 2045", //28
			        "Activity 2046", //29
			        "Stage2050", // 30
			        "Activity 2051", //31
			        "Activity 2052", //32
			        "Activity 2053", //33
			        "Activity 2054", //34
			        "Activity 2055", //35
			        "Stage2060", // 36
			        "Activity 2061", //37
			        "Activity 2062", //38
			        "Activity 2063", //39
			        "Activity 2064", //40
			        "Activity 2065", //41
			        "Activity 2066", //42
			        "Activity 2067", //43
			        }; 
		IconNode[] nodes = new IconNode[strs.length];
		for(int i=0; i<strs.length; i++) {
			nodes[i]=new IconNode(strs[i]);
		}
		nodes[0].add(nodes[1]);
		nodes[0].add(nodes[12]);
		nodes[12].add(nodes[13]);
		nodes[12].add(nodes[23]);
		nodes[12].add(nodes[30]);
		nodes[12].add(nodes[36]);
		for(int i=2;i<12;i++) {
			nodes[1].add(nodes[i]);
		}
		for(int i=14; i<23; i++) {
			nodes[13].add(nodes[i]);
		}
		for(int i=24; i<30; i++) {
			nodes[23].add(nodes[i]);
		}
		for(int i=31; i<36; i++) {
			nodes[30].add(nodes[i]);
		}
		for(int i=37; i<44; i++) {
			nodes[36].add(nodes[i]);
		}
		nodes[0].setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
		for(int i=1; i<44; i++) {
			if(i==1 || i== 12 || i == 13 || i == 23 || i == 30 || i == 36) {
				nodes[i].setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
			}
			else {
				nodes[i].setIconName("computer");
			}
		}
		//tree 
		JTree tree = new JTree(nodes[0]);  
		tree.putClientProperty("JTree.icons", makeIcons());
		tree.setCellRenderer(new IconNodeRenderer());
		
		Oopt oopt = new Oopt();
		Stage1000 s1000 = new Stage1000();
		Stage2000 s2000 = new Stage2000();
		Stage2030 s2030 = new Stage2030();
		Stage2040 s2040 = new Stage2040();
		Stage2050 s2050 = new Stage2050();
		Stage2060 s2060 = new Stage2060();
		//activity
		Activity1001 a1001 = new Activity1001();
		Activity1002 a1002 = new Activity1002(risk);
		Activity1003 a1003 = new Activity1003(req);
		Activity1004 a1004 = new Activity1004();
		Activity1005 a1005 = new Activity1005();
		Activity1006 a1006 = new Activity1006();
		Activity1007 a1007 = new Activity1007();
		Activity1008 a1008 = new Activity1008();
		Activity1009 a1009 = new Activity1009(req);
		Activity1010 a1010 = new Activity1010(req);
		Activity2031 a2031 = new Activity2031(req, uc);
		Activity2032 a2032 = new Activity2032();
		Activity2033 a2033 = new Activity2033();
		Activity2034 a2034 = new Activity2034();
		Activity2035 a2035 = new Activity2035();
		Activity2036 a2036 = new Activity2036();
		Activity2037 a2037 = new Activity2037();
		Activity2038 a2038 = new Activity2038(req, uc);
		Activity2039 a2039 = new Activity2039();
		Activity2041 a2041 = new Activity2041();	
		Activity2042 a2042 = new Activity2042();
		Activity2043 a2043 = new Activity2043();
		Activity2044 a2044 = new Activity2044();
		Activity2045 a2045 = new Activity2045();
		Activity2046 a2046 = new Activity2046();
		Activity2051 a2051 = new Activity2051();	
		Activity2052 a2052 = new Activity2052();
		Activity2053 a2053 = new Activity2053();
		Activity2054 a2054 = new Activity2054();
		Activity2055 a2055 = new Activity2055();
		Activity2061 a2061 = new Activity2061();
		Activity2062 a2062 = new Activity2062();
		Activity2063 a2063 = new Activity2063();
		Activity2064 a2064 = new Activity2064();
		Activity2065 a2065 = new Activity2065();
		Activity2066 a2066 = new Activity2066();
		Activity2067 a2067 = new Activity2067();
		/*
		tree.setModel(new DefaultTreeModel(
			new IconNode("OOPT") {
				{
					IconNode node_1;
					IconNode node_2;
					node_1 = new IconNode("Stage1000");
						node_1.add(new IconNode("Activity 1001"));
						node_1.add(new IconNode("Activity 1002"));
						node_1.add(new IconNode("Activity 1003"));
						node_1.add(new IconNode("Activity 1004"));
						node_1.add(new IconNode("Activity 1005"));
						node_1.add(new IconNode("Activity 1006"));
						node_1.add(new IconNode("Activity 1007"));
						node_1.add(new IconNode("Activity 1008"));
						node_1.add(new IconNode("Activity 1009"));
						node_1.add(new IconNode("Activity 1010"));
					add(node_1);
					node_1 = new IconNode("Stage2000");
						//node_1.add(new IconNode("Stage2010"));
						//node_1.add(new IconNode("Stage2020"));
						node_2 = new IconNode("Stage2030");
							node_2.add(new IconNode("Activity 2031"));
							node_2.add(new IconNode("Activity 2032"));
							node_2.add(new IconNode("Activity 2033"));
							node_2.add(new IconNode("Activity 2034"));
							node_2.add(new IconNode("Activity 2035"));
							node_2.add(new IconNode("Activity 2036"));
							node_2.add(new IconNode("Activity 2037"));
							node_2.add(new IconNode("Activity 2038"));
							node_2.add(new IconNode("Activity 2039"));
						node_1.add(node_2);
						node_2 = new IconNode("Stage2040");
							node_2.add(new IconNode("Activity 2041"));
							node_2.add(new IconNode("Activity 2042"));
							node_2.add(new IconNode("Activity 2043"));
							node_2.add(new IconNode("Activity 2044"));
							node_2.add(new IconNode("Activity 2045"));
							node_2.add(new IconNode("Activity 2046"));
							node_2.add(new IconNode("Activity 2047"));
						node_1.add(node_2);
						node_2 = new IconNode("Stage2050");
							node_2.add(new IconNode("Activity 2051"));
							node_2.add(new IconNode("Activity 2052"));
							node_2.add(new IconNode("Activity 2053"));
							node_2.add(new IconNode("Activity 2054"));
							node_2.add(new IconNode("Activity 2055"));
						node_1.add(node_2);
						node_2 = new IconNode("Stage2060");
							node_2.add(new IconNode("Activity 2061"));
							node_2.add(new IconNode("Activity 2062"));
							node_2.add(new IconNode("Activity 2063"));
							node_2.add(new IconNode("Activity 2064"));
							node_2.add(new IconNode("Activity 2065"));
							node_2.add(new IconNode("Activity 2066"));
							node_2.add(new IconNode("Activity 2067"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
		*/
		splitPane.setLeftComponent(new JScrollPane(tree));
		
		JMenuBar menuBar_1 = new JMenuBar();
		frame.setJMenuBar(menuBar_1);
		
		JMenu File = new JMenu("File");
		menuBar_1.add(File);
		
		JMenuItem mntmNew = new JMenuItem("New");
		File.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		File.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		File.add(mntmSave);
	    mntmSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					IconNode node = (IconNode)tree.getLastSelectedPathComponent();
					String s = (String)node.getUserObject();
					if(s.equals("Activity 1001")) {
						nodes[2].setIconName("floppyDrive");	
					}
					else if(s.equals("Activity 1002")) {
		        		 nodes[3].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1003")) {
		        		 nodes[4].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1004")) {
		        		 nodes[5].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1005")) {
		        		 nodes[6].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1006")) {
		        		 nodes[7].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1007")) {
		        		 nodes[8].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1008")) {
		        		 nodes[9].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1009")) {
		        		 nodes[10].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 1010")) {
		        		 nodes[1].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2031")) {
		        		 nodes[14].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2032")) {
		        		 nodes[15].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2033")) {
		        		 nodes[16].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2034")) {
		        		 nodes[17].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2035")) {
		        		 nodes[18].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2036")) {
		        		 nodes[19].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2037")) {
		        		 nodes[20].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2038")) {
		        		 nodes[21].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2039")) {
		        		 nodes[22].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2041")) {
		        		 nodes[24].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2042")) {
		        		 nodes[25].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2043")) {
		        		 nodes[26].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2044")) {
		        		 nodes[27].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2045")) {
		        		 nodes[28].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2046")) {
		        		 nodes[29].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2051")) {
		        		 nodes[31].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2052")) {
		        		 nodes[32].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2053")) {
		        		 nodes[33].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2054")) {
		        		 nodes[34].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2055")) {
		        		 nodes[35].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2061")) {
		        		 nodes[37].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2062")) {
		        		 nodes[38].setIconName("floppyDrive");
		        	 }
		        	 else  if(s.equals("Activity 2063")) {
		        		 nodes[39].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2064")) {
		        		 nodes[40].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2065")) {
		        		 nodes[41].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2066")) {
		        		 nodes[42].setIconName("floppyDrive");
		        	 }
		        	 else if(s.equals("Activity 2067")) {
		        		 nodes[43].setIconName("floppyDrive");
		        	 }
					tree.putClientProperty("JTree.icons", makeIcons());
					
				}
		 });
	
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_1);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
	         public void valueChanged(TreeSelectionEvent arg0) {
	        	 IconNode node=(IconNode)tree.getLastSelectedPathComponent();
	        	 if(node==null) {
	        		 return;
	        	 }
	        	 String s=(String) node.getUserObject();
	        	 if(s.equals("OOPT")) {
	        		 splitPane.setRightComponent(oopt);
	        	 }
	        	 else if(s.equals("Stage1000")) {
	        		 splitPane.setRightComponent(s1000);
	        	 }
	        	 else if(s.equals("Stage2000")) {
	        		 splitPane.setRightComponent(s2000);
	        	 }
	        	 else if(s.equals("Stage2030")) {
	        		 splitPane.setRightComponent(s2030);
	        	 }
	        	 else if(s.equals("Stage2040")) {
	        		 splitPane.setRightComponent(s2040);
	        	 }
	        	 else if(s.equals("Stage2050")) {
	        		 splitPane.setRightComponent(s2050);
	        	 }
	        	 else if(s.equals("Stage2060")) {
	        		 splitPane.setRightComponent(s2060);
	        	 }
	        	 else if(s.equals("Activity 1001")) {
	        		 splitPane.setRightComponent(a1001);
	        	 }
	        	 else if(s.equals("Activity 1002")) {
	        		 splitPane.setRightComponent(a1002);
	        	 }
	        	 else if(s.equals("Activity 1003")) {
	        		 a1003.syncRequirement(req);
	        		 splitPane.setRightComponent(a1003);
	        	 }
	        	 else if(s.equals("Activity 1004")) {
	        		 splitPane.setRightComponent(a1004);
	        	 }
	        	 else if(s.equals("Activity 1005")) {
	        		 splitPane.setRightComponent(a1005);
	        	 }
	        	 else if(s.equals("Activity 1006")) {
	        		 splitPane.setRightComponent(a1006);
	        	 }
	        	 else if(s.equals("Activity 1007")) {
	        		 splitPane.setRightComponent(a1007);
	        	 }
	        	 else if(s.equals("Activity 1008")) {
	        		 splitPane.setRightComponent(a1008);
	        	 }
	        	 else if(s.equals("Activity 1009")) {
	        		 a1009.syncComboBox(req.getAllName());
	        		 splitPane.setRightComponent(a1009);
	        	 }
	        	 else if(s.equals("Activity 1010")) {
	        		 a1010.syncRequirement(req);
	        		 splitPane.setRightComponent(a1010);
	        	 }
	        	 else if(s.equals("Activity 2031")) {
	        		 splitPane.setRightComponent(a2031);
	        	 }
	        	 else if(s.equals("Activity 2032")) {
	        		 splitPane.setRightComponent(a2032);
	        	 }
	        	 else if(s.equals("Activity 2033")) {
	        		 splitPane.setRightComponent(a2033);
	        	 }
	        	 else if(s.equals("Activity 2034")) {
	        		 splitPane.setRightComponent(a2034);
	        	 }
	        	 else if(s.equals("Activity 2035")) {
	        		 splitPane.setRightComponent(a2035);
	        	 }
	        	 else if(s.equals("Activity 2036")) {
	        		 splitPane.setRightComponent(a2036);
	        	 }
	        	 else if(s.equals("Activity 2037")) {
	        		 splitPane.setRightComponent(a2037);
	        	 }
	        	 else if(s.equals("Activity 2038")) {
	        		 a2038.syncComboBox(req.getAllName());
	        		 splitPane.setRightComponent(a2038);
	        	 }
	        	 else if(s.equals("Activity 2039")) {
	        		 splitPane.setRightComponent(a2039);
	        	 }
	        	 else if(s.equals("Activity 2041")) {
	        		 splitPane.setRightComponent(a2041);
	        	 }
	        	 else if(s.equals("Activity 2042")) {
	        		 splitPane.setRightComponent(a2042);
	        	 }
	        	 else if(s.equals("Activity 2043")) {
	        		 splitPane.setRightComponent(a2043);
	        	 }
	        	 else if(s.equals("Activity 2044")) {
	        		 splitPane.setRightComponent(a2044);
	        	 }
	        	 else if(s.equals("Activity 2045")) {
	        		 splitPane.setRightComponent(a2045);
	        	 }
	        	 else if(s.equals("Activity 2046")) {
	        		 splitPane.setRightComponent(a2046);
	        	 }
	        	 else if(s.equals("Activity 2051")) {
	        		 splitPane.setRightComponent(a2051);
	        	 }
	        	 else if(s.equals("Activity 2052")) {
	        		 splitPane.setRightComponent(a2052);
	        	 }
	        	 else if(s.equals("Activity 2053")) {
	        		 splitPane.setRightComponent(a2053);
	        	 }
	        	 else if(s.equals("Activity 2054")) {
	        		 splitPane.setRightComponent(a2054);
	        	 }
	        	 else if(s.equals("Activity 2055")) {
	        		 splitPane.setRightComponent(a2055);
	        	 }
	        	 else if(s.equals("Activity 2061")) {
	        		 splitPane.setRightComponent(a2061);
	        	 }
	        	 else if(s.equals("Activity 2062")) {
	        		 splitPane.setRightComponent(a2062);
	        	 }
	        	 else  if(s.equals("Activity 2063")) {
	        		 splitPane.setRightComponent(a2063);
	        	 }
	        	 else if(s.equals("Activity 2064")) {
	        		 splitPane.setRightComponent(a2064);
	        	 }
	        	 else if(s.equals("Activity 2065")) {
	        		 splitPane.setRightComponent(a2065);
	        	 }
	        	 else if(s.equals("Activity 2066")) {
	        		 splitPane.setRightComponent(a2066);
	        	 }
	        	 else if(s.equals("Activity 2067")) {
	        		 splitPane.setRightComponent(a2067);
	        	 }
	        	
	         }
	      });
		//testing code
		frame.setVisible(true);
		
	}
	
	//testing code
	

	
}
