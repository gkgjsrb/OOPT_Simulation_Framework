package view;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BoxLayout;
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
import javax.swing.tree.DefaultTreeModel;

import Model.Requirement;
import Model.Risk;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI(Requirement req, Risk risk, ArrayList uc) {
		initialize(req, risk, uc);
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
			        "Stage1000 - Plan and Elaboration", // 1
			        "Activity 1001 - Define Draft Plan", //2
			        "Activity 1002 - Create Preliminary Investigation Report", //3
			        "Activity 1003 - Define Requirements", //4
			        "Activity 1004 - Record Terms in Glossary", //5
			        "Activity 1005 - Implement Prototype", //6
			        "Activity 1006 - Define Business Use Case", //7
			        "Activity 1007 - Define Business Concept Model", //8
			        "Activity 1008 - Define Draft System Architecture", //9
			        "Activity 1009 - Define System Test Case", //10
			        "Activity 1010 - Refine Plan", //11
			        "Stage2000 - Build", // 12
			        "Stage2030 - Analyze", // 13
			        "Activity 2031 - Define Essential Use Cases", //14
			        "Activity 2032 - Refine Use Case Diagrams", //15
			        "Activity 2033 - Define Domain Model", //16
			        "Activity 2034 - Refine Glossary", //17
			        "Activity 2035 - Define System Sequence Diagrams", //18
			        "Activity 2036 - Define Operation Contracts", //19
			        "Activity 2037 - Define State Diagrams", //20
			        "Activity 2038 - Refine System Test Case", //21
			        "Activity 2039 - 2030 Phase Traceability Analysis", //22
			        "Stage2040 - Design", // 23
			        "Activity 2041 - Design Real Use Cases", //24
			        "Activity 2042 - Define Reports, UI and Storyboards", //25
			        "Activity 2043 - Refine System Architecture", //26
			        "Activity 2044 - Define Interaction Diagrams", //27
			        "Activity 2045 - Define Design Class Diagrams", //28
			        "Activity 2046 - Design Traceability Analysis", //29
			        "Stage2050 - Construct", // 30
			        "Activity 2051 - Implement Class & Methods Definitions", //31
			        "Activity 2052 - Implement Windows", //32
			        "Activity 2053 - Implement Reports", //33
			        "Activity 2054 - Implement DB Schema", //34
			        "Activity 2055 - Write Unit Test Code", //35
			        "Stage2060 - Test", // 36
			        "Activity 2061 - Unit Testing", //37
			        "Activity 2062 - Integration Testing", //38
			        "Activity 2063 - System Testing", //39
			        "Activity 2064 - Performance Testing", //40
			        "Activity 2065 - Acceptance Testing", //41
			        "Activity 2066 - Documentation Testing", //42
			        "Activity 2067 - Testing Traceability Analysis", //43
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
		//stage
		Oopt oopt = new Oopt();
		Stage1000 s1000 = new Stage1000();
		Stage2000 s2000 = new Stage2000();
		Stage2030 s2030 = new Stage2030();
		Stage2040 s2040 = new Stage2040();
		Stage2050 s2050 = new Stage2050();
		Stage2060 s2060 = new Stage2060();
		//activity
		Activity1001 a1001 = new Activity1001(tree);
		Activity1002 a1002 = new Activity1002(tree, risk);
		Activity1003 a1003 = new Activity1003(tree, req);
		Activity1004 a1004 = new Activity1004(tree);
		Activity1005 a1005 = new Activity1005(tree);
		Activity1006 a1006 = new Activity1006(tree);
		Activity1007 a1007 = new Activity1007(tree);
		Activity1008 a1008 = new Activity1008(tree);
		Activity1009 a1009 = new Activity1009(tree, req);
		Activity1010 a1010 = new Activity1010(tree, req);
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
	        	IconNode node=(IconNode)tree.getLastSelectedPathComponent();
	        	int index;
	        	 if(node==null) {
	        		 return;
	        	 }
	        	 //1000
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :nodes[2].setIconName("floppyDrive");
		        	 			break;
		        	 	case 1 :nodes[3].setIconName("floppyDrive");
	    	 					break;
		        	 	case 2 :nodes[4].setIconName("floppyDrive");
	    	 					break;
		        	 	case 3 :nodes[5].setIconName("floppyDrive");
	    	 					break;
		        	 	case 4 :nodes[6].setIconName("floppyDrive");
	    	 					break;
		        	 	case 5 :nodes[7].setIconName("floppyDrive");
	    	 					break;
		        	 	case 6 :nodes[8].setIconName("floppyDrive");
	    	 					break;
		        	 	case 7 :nodes[9].setIconName("floppyDrive");
	    	 					break;
		        	 	case 8 :nodes[10].setIconName("floppyDrive");
	    	 					break;
		        	 	case 9 :nodes[11].setIconName("floppyDrive");
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 //2030
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :nodes[14].setIconName("floppyDrive");
		        	 			break;
		        	 	case 1 :nodes[15].setIconName("floppyDrive");
	    	 					break;
		        	 	case 2 :nodes[16].setIconName("floppyDrive");
	    	 					break;
		        	 	case 3 :nodes[17].setIconName("floppyDrive");
	    	 					break;
		        	 	case 4 :nodes[18].setIconName("floppyDrive");
	    	 					break;
		        	 	case 5 :nodes[19].setIconName("floppyDrive");
	    	 					break;
		        	 	case 6 :nodes[20].setIconName("floppyDrive");
	    	 					break;
		        	 	case 7 :nodes[21].setIconName("floppyDrive");
	    	 					break;
		        	 	case 8 :nodes[22].setIconName("floppyDrive");
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 //2040
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(1))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :nodes[24].setIconName("floppyDrive");
		        	 			break;
		        	 	case 1 :nodes[25].setIconName("floppyDrive");
	    	 					break;
		        	 	case 2 :nodes[26].setIconName("floppyDrive");
	    	 					break;
		        	 	case 3 :nodes[27].setIconName("floppyDrive");
	    	 					break;
		        	 	case 4 :nodes[28].setIconName("floppyDrive");
	    	 					break;
		        	 	case 5 :nodes[29].setIconName("floppyDrive");
	    	 					break;
		        	 	//case 6 :splitPane.setRightComponent(a2047);
	    	 			//		break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 //2050
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(2))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :nodes[31].setIconName("floppyDrive");
		        	 			break;
		        	 	case 1 :nodes[32].setIconName("floppyDrive");
	    	 					break;
		        	 	case 2 :nodes[33].setIconName("floppyDrive");
	    	 					break;
		        	 	case 3 :nodes[34].setIconName("floppyDrive");
	    	 					break;
		        	 	case 4 :nodes[35].setIconName("floppyDrive");
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 //2060
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(3))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :nodes[37].setIconName("floppyDrive");
		        	 			break;
		        	 	case 1 :nodes[38].setIconName("floppyDrive");
	    	 					break;
		        	 	case 2 :nodes[39].setIconName("floppyDrive");
	    	 					break;
		        	 	case 3 :nodes[40].setIconName("floppyDrive");
	    	 					break;
		        	 	case 4 :nodes[41].setIconName("floppyDrive");
	    	 					break;
		        	 	case 5 :nodes[42].setIconName("floppyDrive");
	    	 					break;
		        	 	case 6 :nodes[43].setIconName("floppyDrive");
	    	 					break;		 
		        	 	default : break;
		        	}
	        	}
	        	 tree.putClientProperty("JTree.icons", makeIcons());
	    	}
	    });
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar_1.add(mnNewMenu_1);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
	         public void valueChanged(TreeSelectionEvent arg0) {
	        	 IconNode node=(IconNode)tree.getLastSelectedPathComponent();
	        	 int index;
	        	 if(node==null) {
	        		 return;
	        	 }
	        	 //stage
	        	 if(node.equals(node.getRoot())) {
	        		 splitPane.setRightComponent(oopt);
	        	 }
	        	 else if(node.getParent().equals(node.getRoot())) {
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :splitPane.setRightComponent(s1000);
		        	 			break;
		        	 	case 1 :splitPane.setRightComponent(s2000);
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :splitPane.setRightComponent(a1001);
		        	 			break;
		        	 	case 1 :splitPane.setRightComponent(a1002);
	    	 					break;
		        	 	case 2 :a1003.syncRequirement(req);
		        	 			splitPane.setRightComponent(a1003);
	    	 					break;
		        	 	case 3 :splitPane.setRightComponent(a1004);
	    	 					break;
		        	 	case 4 :splitPane.setRightComponent(a1005);
	    	 					break;
		        	 	case 5 :splitPane.setRightComponent(a1006);
	    	 					break;
		        	 	case 6 :splitPane.setRightComponent(a1007);
	    	 					break;
		        	 	case 7 :splitPane.setRightComponent(a1008);
	    	 					break;
		        	 	case 8 :a1009.syncComboBox(req.getAllName());
		        	 			splitPane.setRightComponent(a1009);
	    	 					break;
		        	 	case 9 :a1010.syncRequirement(req);
		        	 			splitPane.setRightComponent(a1010);
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1))) {
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :splitPane.setRightComponent(s2030);
		        	 			break;
		        	 	case 1 :splitPane.setRightComponent(s2040);
	    	 					break;
		        	 	case 2 :splitPane.setRightComponent(s2050);
	    	 					break;
		        	 	case 3 :splitPane.setRightComponent(s2060);
	    	 					break;
		        	 	default : break;
		        	 }
	        	 }
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :splitPane.setRightComponent(a2031);
		        	 			break;
		        	 	case 1 :splitPane.setRightComponent(a2032);
	    	 					break;
		        	 	case 2 :splitPane.setRightComponent(a2033);
	    	 					break;
		        	 	case 3 :splitPane.setRightComponent(a2034);
	    	 					break;
		        	 	case 4 :splitPane.setRightComponent(a2035);
	    	 					break;
		        	 	case 5 :splitPane.setRightComponent(a2036);
	    	 					break;
		        	 	case 6 :splitPane.setRightComponent(a2037);
	    	 					break;
		        	 	case 7 :a2038.syncComboBox(req.getAllName());
		        	 			splitPane.setRightComponent(a2038);
	    	 					break;
		        	 	case 8 :splitPane.setRightComponent(a2039);
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(1))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :splitPane.setRightComponent(a2041);
		        	 			break;
		        	 	case 1 :splitPane.setRightComponent(a2042);
	    	 					break;
		        	 	case 2 :splitPane.setRightComponent(a2043);
	    	 					break;
		        	 	case 3 :splitPane.setRightComponent(a2044);
	    	 					break;
		        	 	case 4 :splitPane.setRightComponent(a2045);
	    	 					break;
		        	 	case 5 :splitPane.setRightComponent(a2046);
	    	 					break;
		        	 	//case 6 :splitPane.setRightComponent(a2047);
	    	 			//		break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(2))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :splitPane.setRightComponent(a2051);
		        	 			break;
		        	 	case 1 :splitPane.setRightComponent(a2052);
	    	 					break;
		        	 	case 2 :splitPane.setRightComponent(a2053);
	    	 					break;
		        	 	case 3 :splitPane.setRightComponent(a2054);
	    	 					break;
		        	 	case 4 :splitPane.setRightComponent(a2055);
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }
	        	 else if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(3))){
		        	 index = node.getParent().getIndex(node);
		        	 switch(index) {
		        	 	case 0 :splitPane.setRightComponent(a2061);
		        	 			break;
		        	 	case 1 :splitPane.setRightComponent(a2062);
	    	 					break;
		        	 	case 2 :splitPane.setRightComponent(a2063);
	    	 					break;
		        	 	case 3 :splitPane.setRightComponent(a2064);
	    	 					break;
		        	 	case 4 :splitPane.setRightComponent(a2065);
	    	 					break;
		        	 	case 5 :splitPane.setRightComponent(a2066);
	    	 					break;
		        	 	case 6 :splitPane.setRightComponent(a2067);
	    	 					break;		 
		        	 	default : break;
		        	 }
	        	 }	        	 
	         }
	      });
		
		
		frame.setVisible(true);
	}
	
	//testing code
	

	
}
