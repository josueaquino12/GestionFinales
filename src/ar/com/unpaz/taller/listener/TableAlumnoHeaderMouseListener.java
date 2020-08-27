package ar.com.unpaz.taller.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import ar.com.unpaz.modelo.AlumnoTableModel;

import ar.com.unpaz.taller.db.AlumnoDAO;

//*Esta clase cumple la funcion de acomodar y ordenar ciertos datos que se presenta 
// en la tabla especificada cuando se hace un click en una columna//
public class TableAlumnoHeaderMouseListener extends MouseAdapter {

	JTable table;

	public TableAlumnoHeaderMouseListener(JTable table) {
		super();
		this.table = table;
	}

	public void mouseClicked(MouseEvent event) {

		Point point = event.getPoint();
		int column = table.columnAtPoint(point);

		AlumnoTableModel c = null;
		switch (column) {
		case 0:

			c = new AlumnoTableModel((new AlumnoDAO()).getAlumnosOrderByDNI());

			break;
		case 1:
			c = new AlumnoTableModel((new AlumnoDAO()).getALUMNO());

			break;
		}

		if (c != null) {
			table.setModel(c);
			table.getTableHeader().setReorderingAllowed(false);
		}
	}

}
