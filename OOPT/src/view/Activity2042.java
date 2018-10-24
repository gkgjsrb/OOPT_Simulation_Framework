package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.horstmann.violet.workspace.Workspace;

import Model.Button;
import Model.Graph;

//define ui
public class Activity2042 extends JPanel {

	private JFrame parent = (JFrame) this.getTopLevelAncestor();
	private int Objectcnt=1;
	
	private JButton button;
	private JButton button_1;
	
	private Point origin;
	private Point mousePt;
	private Boolean button_selected;
	private int button_number;

	private Boolean selected;
	private Button selectedBtn;
	private JTextField selectedField;

	private JPanel panel;
	private JPanel panel_1;

	private ArrayList<Button> bt = new ArrayList<Button>();
	
	
	public Activity2042(ArrayList<Graph> id) {
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLocation(6, 0);
		panel.setSize(151, 595);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLocation(157, 0);
		panel_1.setSize(771, 595);
		panel_1.setLayout(null);
		panel_1.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		panel_1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				origin = e.getPoint();
				
				if (button_selected && button_number == 1) {
					JTextField tempText = new JTextField("text");
					tempText.setLocation(origin);
					tempText.setSize(100, 30);
					tempText.addFocusListener(new FocusListener() {
						@Override
						public void focusGained(FocusEvent e) {
							// TODO Auto-generated method stub
							System.out.println(tempText.getText());
						}

						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub

						}
					});
					tempText.addMouseMotionListener(new MouseMotionListener() {

						@Override
						public void mouseDragged(MouseEvent e) {
							// TODO Auto-generated method stub
							int dx = e.getX() - mousePt.x;
							int dy = e.getY() - mousePt.y;
							
							if (selectedField != null) {
								selectedField.setLocation(selectedField.getX() + dx, selectedField.getY() + dy);
							}
							// mousePt = e.getPoint();
							
							panel_1.repaint();
						}

						@Override
						public void mouseMoved(MouseEvent e) {
							// TODO Auto-generated method stub

						}

					});
					tempText.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							if (e.getClickCount() == 2) {
								System.out.println("Double Click");
							}
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							System.out.println("Click");
							Color a = new Color(51, 143, 252);
							tempText.setBackground(a);
							tempText.setForeground(Color.WHITE);
							mousePt = e.getPoint();
							selectedField = tempText;
							selected = true;
							selectedBtn = null;
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}

					});
					panel_1.add(tempText);
					panel_1.revalidate();
					panel_1.repaint();
					button.setEnabled(true);
					button_selected = false;
					button_number = 0;
				} 
				else if (button_selected && button_number == 2) {
					Button tempButton = new Button(Objectcnt);
					tempButton.setLocation(origin);
					tempButton.setSize(100, 30);
					tempButton.addMouseMotionListener(new MouseMotionListener() {
						@Override
						public void mouseDragged(MouseEvent e) {
							// TODO Auto-generated method stub
							// System.out.println(origin.x + " " +
							// e.getPoint().x );
							int dx = e.getX() - mousePt.x;
							int dy = e.getY() - mousePt.y;
							
							if (selectedBtn != null) {
								for(Button tmp_bt : bt) {
									if(tmp_bt.getId()==selectedBtn.getId()) {
										tmp_bt.setLocation(selectedBtn.getX()+dx, selectedBtn.getY()+dy);
									}
								}
							}
							
							// mousePt = e.getPoint();
							panel_1.repaint();
						}

						@Override
						public void mouseMoved(MouseEvent e) {
							// TODO Auto-generated method stub

						}
					});
					tempButton.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							if (e.getClickCount() == 2) {
								System.out.println("Double Click");
								JDialog jd = new JDialog(parent);
								jd.setLayout(null);
								jd.setSize(400, 150);
								jd.setResizable(false);
								jd.setTitle("Input Operation Name");
								jd.show();
								JComboBox<String> cb = new JComboBox<String>();
								cb.setSize(200, 40);
								cb.setLocation(100,0);
								for(Graph tmp_id : id) {
									cb.addItem(tmp_id.getName());
								}
								JButton jb = new JButton("»Æ¿Œ");
								jb.setSize(80, 40);
								jb.setLocation(160, 60);
								jd.add(cb);
								jd.add(jb);
								
								jb.addActionListener(new ActionListener() {
						
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										if(selectedBtn!=null) {
											int index = cb.getSelectedIndex();
											String sel = cb.getItemAt(index);
											for(Button tmp_bt : bt) {
												if(tmp_bt.getId()==selectedBtn.getId()) {
													for(Graph tmp_id : id) {
														if(tmp_id.getName().equals(sel)) {
															tmp_bt.setid(tmp_id);
															tmp_bt.setText(tmp_bt.getid().getName());
														}
													}
												}
											}
										}
										jd.dispose();
									}
								});
							}
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							System.out.println("Click");
							tempButton.setForeground(Color.RED);
							mousePt = e.getPoint();
							selectedBtn = tempButton;
							selected = true;
							selectedField = null;
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}

					});
					panel_1.add(tempButton);
					panel_1.revalidate();
					panel_1.repaint();
					button_1.setEnabled(true);
					button_selected = false;
					button_number = 0;
					Objectcnt++;
					bt.add(tempButton);
				}
				
				selected = false;
				if (selectedBtn != null)
					selectedBtn.setForeground(Color.BLACK);
				if (selectedField != null) {
					selectedField.setBackground(Color.WHITE);
					selectedField.setForeground(Color.BLACK);
				}
				selectedBtn = null;
				selectedField = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				mousePt = e.getPoint();
				// System.out.println("mouse : " + mousePt.x );
				panel_1.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		button = new JButton("Textbox");
		button.setLocation(23, 120);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!button_selected) {
					button_number = 1;
					button.setEnabled(false);
					button_selected = true;
				}

			}

		});
		button.setSize(100, 30);
		panel.add(button);

		button_1 = new JButton("Button");
		button_1.setLocation(23, 240);
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!button_selected) {
					button_number = 2;
					button_1.setEnabled(false);
					button_selected = true;
				}
			}
		});
		button_1.setSize(100, 30);
		panel.add(button_1);
		
		button_selected = false;
		selected = false;
		
		setLayout(null);
		add(panel);
		add(panel_1);

	}

	
	
}
