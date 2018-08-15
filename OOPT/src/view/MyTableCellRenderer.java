package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if(value == "1") {
			cell.setBackground(Color.BLACK);
			cell.setForeground(Color.BLACK);
		}
		else {
			cell.setBackground(Color.WHITE);
					
		}
		return cell;
	}
}
