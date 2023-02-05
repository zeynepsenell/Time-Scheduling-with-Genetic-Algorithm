import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/*
 * For visualization of time planning.
 */
public class TimeTableDrawing extends JFrame {

	String[] columns = {"        ", "Monntag","Dienstag","Mittwoch","Donnerstag","Freitag"};
	DefaultTableModel model = new DefaultTableModel(columns, 4);
	JTable table = new JTable(model);
	
	public TimeTableDrawing() {
		this.init();
	}
		
	/**
	 * For initializing table.
	 */
	private void init() {
		JScrollPane pane = new JScrollPane(table);
		this.addTimeSlots();
		this.columnSizes();
		table.setRowHeight(200);

		this.setPreferredSize(new Dimension(1100,865));
		this.add(pane);
		this.pack();
	}

	/**
	 * Adding time slots to table.
	 */
	private void addTimeSlots() {
		this.model.setValueAt("09:00-11:00", 0, 0);
		this.model.setValueAt("11:00-13:00", 1, 0);
		this.model.setValueAt("13:00-15:00", 2, 0);
		this.model.setValueAt("15:00-17:00", 3, 0);
	}
	
	private void columnSizes() {
		TableColumnModel columnModel = table.getColumnModel();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for(int i = 0 ; i<6 ; i++) {
			columnModel.getColumn(i).setPreferredWidth(180);
		}
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
}

