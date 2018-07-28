package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.BoxLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Model.Requirement;
import Model.Risk;

import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI(Requirement req, Risk risk) {
		initialize(req, risk);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Requirement req, Risk risk) {
		frame = new JFrame();
		frame.setBounds(100, 100, 928, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();

		splitPane.setResizeWeight(0.2);
		splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(splitPane);
		
		//tree 
		JTree tree = new JTree();
		//stage
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
		Activity2031 a2031 = new Activity2031();
		Activity2032 a2032 = new Activity2032();
		Activity2033 a2033 = new Activity2033();
		Activity2034 a2034 = new Activity2034();
		Activity2035 a2035 = new Activity2035();
		Activity2036 a2036 = new Activity2036();
		Activity2037 a2037 = new Activity2037();
		Activity2038 a2038 = new Activity2038();
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
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("OOPT") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("Stage1000");
						node_1.add(new DefaultMutableTreeNode("Activity 1001"));
						node_1.add(new DefaultMutableTreeNode("Activity 1002"));
						node_1.add(new DefaultMutableTreeNode("Activity 1003"));
						node_1.add(new DefaultMutableTreeNode("Activity 1004"));
						node_1.add(new DefaultMutableTreeNode("Activity 1005"));
						node_1.add(new DefaultMutableTreeNode("Activity 1006"));
						node_1.add(new DefaultMutableTreeNode("Activity 1007"));
						node_1.add(new DefaultMutableTreeNode("Activity 1008"));
						node_1.add(new DefaultMutableTreeNode("Activity 1009"));
						node_1.add(new DefaultMutableTreeNode("Activity 1010"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Stage2000");
						node_1.add(new DefaultMutableTreeNode("Stage2010"));
						node_1.add(new DefaultMutableTreeNode("Stage2020"));
						node_2 = new DefaultMutableTreeNode("Stage2030");
							node_2.add(new DefaultMutableTreeNode("Activity 2031"));
							node_2.add(new DefaultMutableTreeNode("Activity 2032"));
							node_2.add(new DefaultMutableTreeNode("Activity 2033"));
							node_2.add(new DefaultMutableTreeNode("Activity 2034"));
							node_2.add(new DefaultMutableTreeNode("Activity 2035"));
							node_2.add(new DefaultMutableTreeNode("Activity 2036"));
							node_2.add(new DefaultMutableTreeNode("Activity 2037"));
							node_2.add(new DefaultMutableTreeNode("Activity 2038"));
							node_2.add(new DefaultMutableTreeNode("Activity 2039"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Stage2040");
							node_2.add(new DefaultMutableTreeNode("Activity 2041"));
							node_2.add(new DefaultMutableTreeNode("Activity 2042"));
							node_2.add(new DefaultMutableTreeNode("Activity 2043"));
							node_2.add(new DefaultMutableTreeNode("Activity 2044"));
							node_2.add(new DefaultMutableTreeNode("Activity 2045"));
							node_2.add(new DefaultMutableTreeNode("Activity 2046"));
							node_2.add(new DefaultMutableTreeNode("Activity 2047"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Stage2050");
							node_2.add(new DefaultMutableTreeNode("Activity 2051"));
							node_2.add(new DefaultMutableTreeNode("Activity 2052"));
							node_2.add(new DefaultMutableTreeNode("Activity 2053"));
							node_2.add(new DefaultMutableTreeNode("Activity 2054"));
							node_2.add(new DefaultMutableTreeNode("Activity 2055"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Stage2060");
							node_2.add(new DefaultMutableTreeNode("Activity 2061"));
							node_2.add(new DefaultMutableTreeNode("Activity 2062"));
							node_2.add(new DefaultMutableTreeNode("Activity 2063"));
							node_2.add(new DefaultMutableTreeNode("Activity 2064"));
							node_2.add(new DefaultMutableTreeNode("Activity 2065"));
							node_2.add(new DefaultMutableTreeNode("Activity 2066"));
							node_2.add(new DefaultMutableTreeNode("Activity 2067"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
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
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_1);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
	         public void valueChanged(TreeSelectionEvent arg0) {
	        	 DefaultMutableTreeNode node=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
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
