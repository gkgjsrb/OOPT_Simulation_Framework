package view;


import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Model.Button;
import Model.StageText;

public class Mapping extends JFrame{
	private JPanel ui;
	private JPanel panel;
	private JSplitPane splitPane;
	private JButton button;
	public Mapping() {
		setTitle("System Operation Mapping");
		setBounds(100, 100, 928, 617);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				ui.revalidate();
			}
		});
		splitPane = new JSplitPane();
		add(splitPane);
		panel = new JPanel();
		splitPane.setLeftComponent(this.ui);
		button = new JButton("Commit");
		panel.add(button);
		splitPane.setRightComponent(panel);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Component[] c = ui.getComponents();
				for(Component temp : c) {
					MouseListener[] m = temp.getMouseListeners();
					for(MouseListener t : m) {
						temp.removeMouseListener(t);
					}
					if(temp.getClass().equals(Button.class)) {
						Button b = (Button) temp;
						b.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Simulation sm = new Simulation(b.getGraph());
								Thread thread = new Thread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										try {
											sm.startSimulation();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}	
									}
									
								});
								thread.start();
								//Thread.sleep(1000);
								//
							}
						});
					}
					
				}
				ui.revalidate();
				ui.repaint();
			}
		});
		
		
	}
	public void setUI(JPanel ui) {
		this.ui = ui;
		splitPane.setLeftComponent(this.ui);
		splitPane.setDividerLocation((int)this.ui.getSize().getWidth());
		this.revalidate();
		//System.out.println("2" +this.ui.getSize().getWidth());
	}
	
}
