package view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;

import Model.Requirement;
import Model.Risk;
import Model.SystemOperation;
import Model.UseCase;

public class TextAreaEditor extends DefaultCellEditor {
	protected JScrollPane scrollpane;
	protected JTextArea textarea;
	
	public TextAreaEditor() {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
	}
	public TextAreaEditor(JTable table, Requirement req) {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
	    this.addCellEditorListener(new CellEditorListener() {
	    	
			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				req.setuName((String)table.getValueAt(table.getSelectedRow(), 2), table.getSelectedRow());
			}
		});
	}
	public TextAreaEditor(Requirement req, JTable table) {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
	    this.addCellEditorListener(new CellEditorListener() {
	    	
			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				req.setRef((String)table.getValueAt(table.getSelectedRow(), 0), table.getSelectedRow());
				req.setName((String)table.getValueAt(table.getSelectedRow(), 1), table.getSelectedRow());
				req.setCategory((String)table.getValueAt(table.getSelectedRow(), 2), table.getSelectedRow());
				
				
			}
			
		});
	}
	public TextAreaEditor(ArrayList<Risk> risk, JTable table, JTable table2) {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
		this.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub

				if(table.getSelectedColumn()==0) {
					risk.get(table.getSelectedRow()).setName((String)table.getValueAt(table.getSelectedRow(), 0));
					table2.setValueAt(table.getValueAt(table.getSelectedRow(), 0), table.getSelectedRow(), 0);
				}
				else if(table.getSelectedColumn()==1) {
					risk.get(table.getSelectedRow()).setProbability(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1)));
					risk.get(table.getSelectedRow()).setWeight();
					table.setValueAt(risk.get(table.getSelectedRow()).getWeight(), table.getSelectedRow(), 3);
				}
				else if(table.getSelectedColumn()==2) {
					risk.get(table.getSelectedRow()).setSignificance(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 2)));
					risk.get(table.getSelectedRow()).setWeight();
					table.setValueAt(risk.get(table.getSelectedRow()).getWeight(), table.getSelectedRow(), 3);
				}
				/*
				model.setRowCount(0);
				model2.setRowCount(0);
				
				for(int i=0; i<risk.size(); i++) {
					Object[] add= {risk.get(i).getName(),risk.get(i).getProbability(),risk.get(i).getSignificance(),
							risk.get(i).getWeight()};
					Object[] add2= {risk.get(i).getName(),risk.get(i).getPlan()};
					model.addRow(add);
					model2.addRow(add2);
				}
				*/
			}	
		});
	}
	
	public TextAreaEditor(JTable table2, ArrayList<Risk> risk, JTable table) {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
		this.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(table2.getSelectedColumn()==0) {
					risk.get(table2.getSelectedRow()).setName((String)table2.getValueAt(table2.getSelectedRow(), 0));
					table.setValueAt(table2.getValueAt(table2.getSelectedRow(), 0), table2.getSelectedRow(), 0);
				}
				else if(table2.getSelectedColumn()==1) {
					risk.get(table2.getSelectedRow()).setPlan((String)table2.getValueAt(table2.getSelectedRow(), 1));
				}
				
				/*
				model.setRowCount(0);
				model2.setRowCount(0);
				
				for(int i=0; i<risk.size();i++) {
					Object[] add= {risk.get(i).getName(),risk.get(i).getProbability(),risk.get(i).getSignificance(),
							risk.get(i).getWeight()};
					Object[] add2= {risk.get(i).getName(),risk.get(i).getPlan()};
					model.addRow(add);
					model2.addRow(add2);
				}
				*/
			}
		});	
	}
	public TextAreaEditor(ArrayList<UseCase> uc,JTable table, JTabbedPane panel) {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
	    this.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			//system
			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				int index = panel.getSelectedIndex();
					if(table.getSelectedRow()==0) {
						uc.get(index).setName((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==1) {
						uc.get(index).setActor((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==2) {
						uc.get(index).setPurpose((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==3) {
						uc.get(index).setOverview((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==4) {
						uc.get(index).setType((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==5) {
						uc.get(index).setCross((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==6) {
						uc.get(index).setPreRequistes((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					//ui
					else if(table.getSelectedRow()==7) {
						uc.get(index).setTypical((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==8) {
						uc.get(index).setAlternative((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==9) {
						uc.get(index).setExceptional((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==10) {
						uc.get(index).setUI((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
				}	
	    });
	}
	
	public TextAreaEditor(JTable table, ArrayList<SystemOperation> op, JTabbedPane panel) {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
	    this.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			//system
			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				int index = panel.getSelectedIndex();
					if(table.getSelectedRow()==0) {
						op.get(index).setName((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==1) {
						op.get(index).setResponsibility((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==2) {
						op.get(index).setType((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==3) {
						op.get(index).setCross((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==4) {
						op.get(index).setNotes((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==5) {
						op.get(index).setException((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==6) {
						op.get(index).setOutput((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==7) {
						op.get(index).setPreconditions((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
					else if(table.getSelectedRow()==8) {
						op.get(index).setPostconditions((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
					}
				}	
	    });
	}
	
	public TextAreaEditor(JTable table, ArrayList<UseCase> uc, JTabbedPane panel, IWorkspace workspace) {
		super(new JCheckBox());
		scrollpane = new JScrollPane();
		textarea = new JTextArea(); 
		textarea.setLineWrap(true);;
		textarea.setWrapStyleWord(true);
	    scrollpane.getViewport().add(textarea);
	    this.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				int index = panel.getSelectedIndex();
				if(table.getSelectedRow()==0) {
					panel.setTitleAt(panel.getSelectedIndex(), (String)table.getValueAt(0, table.getSelectedColumn()));
					uc.get(index).setName((String)table.getValueAt(0, table.getSelectedColumn()));
					Collection<INode> allNodes = workspace.getGraphFile().getGraph().getAllNodes();
					for (INode aNode : allNodes) {
						if(aNode.getClass().equals(UseCaseNode.class)) {
							UseCaseNode a = (UseCaseNode)aNode;
							if(a.getId().equals(uc.get(index).getId())) {
								a.getName().setText((String)table.getValueAt(0, table.getSelectedColumn()));
							}
						}
					}
				}
				else if(table.getSelectedRow()==1) {
					uc.get(index).setActor((String)table.getValueAt(1, table.getSelectedColumn()));
				}
				else if(table.getSelectedRow()==2) {
					uc.get(index).setDes((String)table.getValueAt(2, table.getSelectedColumn()));
				}
			}
	    });
	}
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		textarea.setText((String) value);
		
		return scrollpane;
	}
	  
	public Object getCellEditorValue() {
		return textarea.getText();
	}
	
}
