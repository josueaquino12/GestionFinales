package ar.com.unpaz.modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/*----Esta clase abstracta proporciona implementaciones predeterminadas 
para la mayoría de los métodos en diseño de la interfaz de TableModel.-----*/

public class AlumnoTableModel extends AbstractTableModel {

	/**
	 * Se crea una Lista del objeto Alumno
	 */
	private static final long serialVersionUID = 1L;
	private List<Alumno> alumno;

	// Constructor
	public AlumnoTableModel(List<Alumno> alumno) {
		super();
		this.alumno = alumno;
	}

	// Se crea un arreglo de String para armar las columnas mencionando los
	// atributos
	private static String[] COLUMNAS = { "DNI", "Nombre", "Apellido", "Email" };

	// Retorna el recuento de columnas
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMNAS.length;
	}

	@Override
	// retorna el recuento de las filas
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.alumno.size();
	}

	@Override

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object retorno = null;
		// Se crea un objeto del tipo Alumno para obtener el elemento en la posición
		// especificada en esta lista.
		Alumno c = this.alumno.get(rowIndex);
		// Se crea un switch para retornar y llenar las filas en la tabla con los datos
		// obtenidos de los Alumnos registrados
		switch (columnIndex) {
		case 0:
			retorno = c.getDNI();
			break;
		case 1:
			retorno = c.getNombre();
			break;
		case 2:
			retorno = c.getApellido();
			break;
		case 3:
			retorno = c.getEmail();
			break;
		}
		return retorno;
	}

	public String getColumnName(int index) {

		return COLUMNAS[index];
	}

}
