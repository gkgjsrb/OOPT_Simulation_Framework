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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
		splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(splitPane);
		
		
		JTree tree = new JTree();
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
							node_2.add(new DefaultMutableTreeNode("Activity  2031"));
							node_2.add(new DefaultMutableTreeNode("Activity  2032"));
							node_2.add(new DefaultMutableTreeNode("Activity  2033"));
							node_2.add(new DefaultMutableTreeNode("Activity  2034"));
							node_2.add(new DefaultMutableTreeNode("Activity  2035"));
							node_2.add(new DefaultMutableTreeNode("Activity  2036"));
							node_2.add(new DefaultMutableTreeNode("Activity  2037"));
							node_2.add(new DefaultMutableTreeNode("Activity  2038"));
							node_2.add(new DefaultMutableTreeNode("Activity  2039"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Stage2040");
							node_2.add(new DefaultMutableTreeNode("Activity  2041"));
							node_2.add(new DefaultMutableTreeNode("Activity  2042"));
							node_2.add(new DefaultMutableTreeNode("Activity  2043"));
							node_2.add(new DefaultMutableTreeNode("Activity  2044"));
							node_2.add(new DefaultMutableTreeNode("Activity  2045"));
							node_2.add(new DefaultMutableTreeNode("Activity  2046"));
							node_2.add(new DefaultMutableTreeNode("Activity  2047"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Stage2050");
							node_2.add(new DefaultMutableTreeNode("Activity  2051"));
							node_2.add(new DefaultMutableTreeNode("Activity  2052"));
							node_2.add(new DefaultMutableTreeNode("Activity  2053"));
							node_2.add(new DefaultMutableTreeNode("Activity  2054"));
							node_2.add(new DefaultMutableTreeNode("Activity  2055"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Stage2060");
							node_2.add(new DefaultMutableTreeNode("Activity  2061"));
							node_2.add(new DefaultMutableTreeNode("Activity  2062"));
							node_2.add(new DefaultMutableTreeNode("Activity  2063"));
							node_2.add(new DefaultMutableTreeNode("Activity  2064"));
							node_2.add(new DefaultMutableTreeNode("Activity  2065"));
							node_2.add(new DefaultMutableTreeNode("Activity  2066"));
							node_2.add(new DefaultMutableTreeNode("Activity  2067"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
		splitPane.setLeftComponent(new JScrollPane(tree));
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("200px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("230px:grow"),},
			new RowSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("37px:grow"),
				RowSpec.decode("37px:grow"),
				RowSpec.decode("37px:grow"),
				RowSpec.decode("37px:grow"),
				RowSpec.decode("37px:grow"),
				RowSpec.decode("37px:grow"),
				RowSpec.decode("37px:grow"),}));
		
		JLabel lblNewLabel = new JLabel("Motivation");
		panel.add(lblNewLabel, "1, 2, center, center");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "3, 2, fill, fill");
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JLabel lblNewLabel_1 = new JLabel("Project Objective");
		panel.add(lblNewLabel_1, "1, 3, center, center");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "3, 3, fill, fill");
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("Scope");
		panel.add(lblNewLabel_2, "1, 4, center, center");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2, "3, 4, fill, fill");

		JTextPane textPane_2 = new JTextPane();
		scrollPane_2.setViewportView(textPane_2);
		
		JLabel lblNewLabel_3 = new JLabel("Draft Functional Requirement");
		panel.add(lblNewLabel_3, "1, 5, center, center");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel.add(scrollPane_3, "3, 5, fill, fill");

		JTextPane textPane_3 = new JTextPane();
		scrollPane_3.setViewportView(textPane_3);
		
		JLabel lblNewLabel_4 = new JLabel("Non-Functional Requirement");
		panel.add(lblNewLabel_4, "1, 6, center, center");
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel.add(scrollPane_4, "3, 6, fill, fill");

		JTextPane textPane_4 = new JTextPane();
		scrollPane_4.setViewportView(textPane_4);
		
		JLabel lblNewLabel_5 = new JLabel("Resource Estimation");
		panel.add(lblNewLabel_5, "1, 7, center, center");
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panel.add(scrollPane_5, "3, 7, fill, fill");

		JTextPane txtpnHumanEffortsHuman = new JTextPane();
		txtpnHumanEffortsHuman.setText("Human Efforts : \nHuman Resource : \nProject Duration : \nCost : ");
		scrollPane_5.setViewportView(txtpnHumanEffortsHuman);
		
		JLabel lblNewLabel_6 = new JLabel("Other Information");
		panel.add(lblNewLabel_6, "1, 8, center, center");
		
		JScrollPane scrollPane_6 = new JScrollPane();
		panel.add(scrollPane_6, "3, 8, fill, fill");

		JTextPane textPane_6 = new JTextPane();
		scrollPane_6.setViewportView(textPane_6);
		
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
	}

	
}
