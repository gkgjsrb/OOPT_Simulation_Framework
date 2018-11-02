package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import Model.Button;
import Model.Datainfo;
import Model.UIDesign;
import Model.UMLDiagram;

//define ui
public class Activity2042 extends JTabbedPane {

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
	
	private JPanel totalpanel;
	private JPanel panel;
	private JPanel panel_1;

	//private ArrayList<Button> bt = new ArrayList<Button>();
	private ArrayList<Button> buttonList;
	private ArrayList<JTextField> textList;
	private ArrayList<JLabel> labelList;
	
	public Activity2042(JTree tree, ArrayList<UMLDiagram> id, UIDesign ui) {
		
		buttonList = new ArrayList<>();
		textList = new ArrayList<>();
		labelList = new ArrayList<>();
		
		JSplitPane splitPane = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_2 = new JButton("Delete");
		JButton button_3 = new JButton("Commit");
		jpanel.add(button_2);
		jpanel.add(button_3);
		
		totalpanel = new JPanel();
		
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
		panel_1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
								int x = selectedField.getX() + dx;
								int y = selectedField.getY() + dy;
								if(x>= 0 && x+tempText.getWidth() <= panel_1.getSize().getWidth() && y>=0 && y+tempText.getHeight() <= panel_1.getSize().getHeight()) {
									selectedField.setLocation(x, y);
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
					tempText.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							if (e.getClickCount() == 2) {
								//System.out.println("Double Click");
							}
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							//System.out.println("Click");
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
					textList.add(tempText);
				} 
				else if (button_selected && button_number == 2) {
					Button tempButton = new Button("Button",Objectcnt);
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
								int x = selectedBtn.getX() + dx;
								int y = selectedBtn.getY() + dy;
//								for(Button tmp_bt : bt) {
//									if(tmp_bt.getId()==selectedBtn.getId()) {
//										tmp_bt.setLocation(selectedBtn.getX()+dx, selectedBtn.getY()+dy);
//									}
//								}
								if(x>= 0 && x+tempButton.getWidth() <= panel_1.getSize().getWidth() && y>=0 && y+tempButton.getHeight() <= panel_1.getSize().getHeight()) {
									selectedBtn.setLocation(x, y);
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
							if(e.getClickCount() == 2) {
								JDialog jd = new JDialog(parent);
								jd.setLayout(null);
								jd.setSize(400, 150);
								jd.setResizable(false);
								jd.setTitle("Input Button Name");
								jd.show();
								JTextField tf = new JTextField();
								tf.setSize(200,40);
								tf.setLocation(100, 0);
								
								JButton jb = new JButton("확인");
								jb.setSize(80, 40);
								jb.setLocation(160, 70);
								jd.add(tf);
								jd.add(jb);
								
								jb.addActionListener(new ActionListener() {
						
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										tempButton.setName(tf.getText());
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
					buttonList.add(tempButton);
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
		
		totalpanel.setLayout(null);
		totalpanel.add(panel);
		totalpanel.add(panel_1);
		
		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (selected) {
					if(selectedBtn != null) {
						panel_1.remove(selectedBtn);
						panel_1.revalidate();
						panel_1.repaint();
						
						buttonList.remove(selectedBtn);
					}
					else if(selectedField != null) {
						panel_1.remove(selectedField);
						panel_1.revalidate();
						panel_1.repaint();
						textList.remove(selectedField);
					}
				}
			}
		});
		button_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ui.setButtonList(buttonList);
				ui.setTextList(textList);
				//ui.setLabelList();
			}
		});
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(totalpanel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();
		addTab("UI Design", null, splitPane, null);
	}

	public JPanel getPanel(ArrayList<UMLDiagram> id) {
		JPanel j = new JPanel();
		j.setSize(panel_1.getSize());
		j.setLayout(null);
		j.setBackground(Color.WHITE);
		Component[] compts = panel_1.getComponents();
		Component[] copy = new Component[compts.length];
		int i = 0;
		for(Component c : compts) {
			if(c.getClass().equals(JTextField.class)) {
				JTextField temp = (JTextField) c;
				JTextField jt = new JTextField();
				jt.setText(temp.getText());
				jt.setSize(temp.getSize());
				jt.setLocation(temp.getLocation());
				//jt.addMouseListener(temp.getMouseListeners()[0]);
				copy[i] = jt;
			}
			else if(c.getClass().equals(Button.class)) {
				Button temp = (Button) c;
				Button bt = new Button(temp.getName(), temp.getId());
				bt.setText(temp.getText());
				bt.setSize(temp.getSize());
				bt.setLocation(temp.getLocation());
				//bt.addMouseListener(temp.getMouseListeners()[0]);
				bt.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							System.out.println("Double Click");
							JDialog jd = new JDialog(parent);
							jd.setLayout(null);
							jd.setSize(400, 150);
							jd.setResizable(false);
							jd.setTitle("Input Operation Name");
							jd.show();
//							JTextField tf = new JTextField();
//							tf.setSize(200,40);
//							tf.setLocation(100, 0);
							
							JComboBox<String> cb = new JComboBox<String>();
							cb.setSize(200, 40);
							cb.setLocation(100,10);
							for(UMLDiagram tmp_id : id) {
								cb.addItem(tmp_id.getName());
							}
							
							JButton jb = new JButton("확인");
							jb.setSize(80, 40);
							jb.setLocation(160, 70);
							//jd.add(tf);
							jd.add(cb);
							jd.add(jb);
							
							jb.addActionListener(new ActionListener() {
					
								@Override
								public void actionPerformed(ActionEvent arg0) {
									// TODO Auto-generated method stub
									
									int index = cb.getSelectedIndex();
									String sel = cb.getItemAt(index);
									for(UMLDiagram tmp_id : id) {
										if(sel.equals(tmp_id.getName())) {
											bt.setGraph(tmp_id);
										}
									}
										
//										for(Button tmp_bt : bt) {
//											if(tmp_bt.getId()==selectedBtn.getId()) {
//												tmp_bt.setText(tf.getText());
//												for(Graph tmp_id : id) {
//													if(tmp_id.getName().equals(sel)) {
//														tmp_bt.setid(tmp_id);
//														tmp_bt.setText(tmp_bt.getid().getName());
//													}
//												}
//											}
//										}
										
//										for(Button tmp_bt : bt) {
//											if(tmp_bt.getId()==selectedBtn.getId()) {
//												tmp_bt.setText(tf.getText());
//											}
//										}
									
									jd.dispose();
								}
							});
						}
						
					}
				});
				copy[i] = bt;
			}
			
			i++;
		}
		for(Component c : copy) {
			j.add(c);
		}
		return j;
	}
	public void save(Datainfo data, UIDesign ui) {
		data.syncUI();
		data.setUI(ui);
		
	}
	public void open(UIDesign ui) {
		buttonList = ui.getButtonList();
		textList = ui.getTextList();
		labelList = ui.getLabelList();
		
		for(Button tmp : buttonList) {
			panel_1.add(tmp);
			tmp.setSize(100, 30);
			tmp.addMouseMotionListener(new MouseMotionListener() {
				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					// System.out.println(origin.x + " " +
					// e.getPoint().x );
					int dx = e.getX() - mousePt.x;
					int dy = e.getY() - mousePt.y;
					
					if (selectedBtn != null) {
						int x = selectedBtn.getX() + dx;
						int y = selectedBtn.getY() + dy;
//						
						if(x>= 0 && x+tmp.getWidth() <= panel_1.getSize().getWidth() && y>=0 && y+tmp.getHeight() <= panel_1.getSize().getHeight()) {
							selectedBtn.setLocation(x, y);
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
			tmp.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getClickCount() == 2) {
						JDialog jd = new JDialog(parent);
						jd.setLayout(null);
						jd.setSize(400, 150);
						jd.setResizable(false);
						jd.setTitle("Input Button Name");
						jd.show();
						JTextField tf = new JTextField();
						tf.setSize(200,40);
						tf.setLocation(100, 0);
						
						JButton jb = new JButton("확인");
						jb.setSize(80, 40);
						jb.setLocation(160, 70);
						jd.add(tf);
						jd.add(jb);
						
						jb.addActionListener(new ActionListener() {
				
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								tmp.setName(tf.getText());
								jd.dispose();
							}
						});
						
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Click");
					tmp.setForeground(Color.RED);
					mousePt = e.getPoint();
					selectedBtn = tmp;
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
		}
		for(JTextField tmp : textList) {
			tmp.setSize(100, 30);
			tmp.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					System.out.println(tmp.getText());
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub

				}
			});
			tmp.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					int dx = e.getX() - mousePt.x;
					int dy = e.getY() - mousePt.y;
					
					if (selectedField != null) {
						int x = selectedField.getX() + dx;
						int y = selectedField.getY() + dy;
						if(x>= 0 && x+tmp.getWidth() <= panel_1.getSize().getWidth() && y>=0 && y+tmp.getHeight() <= panel_1.getSize().getHeight()) {
							selectedField.setLocation(x, y);
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
			tmp.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if (e.getClickCount() == 2) {
						//System.out.println("Double Click");
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					//System.out.println("Click");
					Color a = new Color(51, 143, 252);
					tmp.setBackground(a);
					tmp.setForeground(Color.WHITE);
					mousePt = e.getPoint();
					selectedField = tmp;
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
		}
		for(JLabel tmp : labelList) {
			
		}
		panel_1.revalidate();
		panel_1.repaint();
	}
	
}
