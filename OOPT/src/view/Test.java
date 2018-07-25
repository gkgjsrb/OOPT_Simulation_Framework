package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Test extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Motivation", null, scrollPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Project Objective", null, scrollPane_1, null);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane.setViewportView(textPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Scope", null, scrollPane_2, null);

		JTextPane textPane_2 = new JTextPane();
		scrollPane.setViewportView(textPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Functional Requirement", null, scrollPane_3, null);

		JTextPane textPane_3 = new JTextPane();
		scrollPane.setViewportView(textPane_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("Non-Functional Requirement", null, scrollPane_4, null);

		JTextPane textPane_4 = new JTextPane();
		scrollPane.setViewportView(textPane_4);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		tabbedPane.addTab("Resource Estimation", null, scrollPane_5, null);

		JTextPane textPane_5 = new JTextPane();
		scrollPane.setViewportView(textPane_5);

		JScrollPane scrollPane_6 = new JScrollPane();
		tabbedPane.addTab("Other Information", null, scrollPane_6, null);

		JTextPane textPane_6 = new JTextPane();
		scrollPane.setViewportView(textPane_6);
		
		String []header = {"abcd", "efgh", "i", "j"};
		String [][]contents = {
				{"a", "b", "c", "d"},
				{"a", "b", "c", "d"},
				{"a", "b", "c", "d"}
		};
		DefaultTableModel model = new DefaultTableModel(contents, header);
		
		JTable table = new JTable(model);
		JScrollPane scrollPane_7 = new JScrollPane(table);

		tabbedPane.addTab("New tab", null,scrollPane_7, null);
		
		
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
