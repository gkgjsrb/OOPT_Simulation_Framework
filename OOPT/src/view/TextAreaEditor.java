package view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import Model.Requirement;
import Model.Risk;
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
	public TextAreaEditor(Requirement req, JTable table, ArrayList<UseCase> uc, JTabbedPane panel) {
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
				String tmp = panel.getSelectedComponent().getName();
				for(int i=0; i<uc.size();i++) {
					if(uc.get(i).getName().equals(tmp)) {
						if(table.getSelectedRow()==0) {
							uc.get(i).setName((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==1) {
							uc.get(i).setActor((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==2) {
							uc.get(i).setPurpose((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==3) {
							uc.get(i).setOverview((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==4) {
							uc.get(i).setType((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==5) {
							uc.get(i).setRelated_requirement((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==6) {
							uc.get(i).setPreRequistes((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==7) {
							uc.get(i).setTypical((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==8) {
							uc.get(i).setAlternative((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						else if(table.getSelectedRow()==9) {
							uc.get(i).setExceptional((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						}
						break;
					}
				}
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
				for(int i=0; i<req.get_length();i++) {
					req.setRef((String)table.getValueAt(i, 0), i);
					req.setName((String)table.getValueAt(i, 1), i);
					req.setCategory((String)table.getValueAt(i, 2), i);
				}
			}
			
		});
	}
	public TextAreaEditor(Risk risk, JTable table, DefaultTableModel model, DefaultTableModel model2) {
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
				table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()), table.getSelectedRow(), table.getSelectedColumn());
				if(table.getSelectedColumn()==0) {
					risk.setName((String)table.getValueAt(table.getSelectedRow(), 0),table.getSelectedRow());
				}
				else if(table.getSelectedColumn()==1) {
					risk.setPro(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1)),table.getSelectedRow());
					risk.setWeight((Integer)risk.getPro(table.getSelectedRow()), (Integer)risk.getSig(table.getSelectedRow()), table.getSelectedRow());
				}
				else if(table.getSelectedColumn()==2) {
					risk.setSig(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 2)),table.getSelectedRow());
					risk.setWeight((Integer)risk.getPro(table.getSelectedRow()), (Integer)risk.getSig(table.getSelectedRow()), table.getSelectedRow());
				}
				for(int i=0; i<risk.get_length(); i++) {
					model.removeRow(0);
					model2.removeRow(0);
				}
				for(int i=0; i<risk.get_length(); i++) {
					Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
					Object[] add2= {risk.getName(i),risk.getPlan(i)};
					model.addRow(add);
					model2.addRow(add2);
				}
			}	
		});
	}
	
	public TextAreaEditor(JTable table2, Risk risk, DefaultTableModel model, DefaultTableModel model2) {
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
				table2.setValueAt(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()), table2.getSelectedRow(), table2.getSelectedColumn());
				if(table2.getSelectedColumn()==0) {
					risk.setName((String)table2.getValueAt(table2.getSelectedRow(), 0), table2.getSelectedRow());
				}
				else if(table2.getSelectedColumn()==1) {
					risk.setPlan((String)table2.getValueAt(table2.getSelectedRow(), 1), table2.getSelectedRow());
				}
				
				for(int i=0; i<risk.get_length() ;i++) {
					model.removeRow(0);
					model2.removeRow(0);
				}
				for(int i=0; i<risk.get_length();i++) {
					Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
					Object[] add2= {risk.getName(i),risk.getPlan(i)};
					model.addRow(add);
					model2.addRow(add2);
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
