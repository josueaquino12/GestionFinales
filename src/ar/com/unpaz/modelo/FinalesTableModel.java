package ar.com.unpaz.modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/*----Esta clase abstracta proporciona implementaciones predeterminadas 
para la mayoría de los métodos en diseño de la interfaz de TableModel.-----*/

public class FinalesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Finales> finales;

	// Se crea un arreglo de String para armar las columnas mencionando los
	// atributos
	private static String[] COLUMNAS = { "ID", "DNI", "NOMBRE", "APELLIDO", "ID_MATERIA", "DESCRIPCION", "NOTA",
			"FECHA_FINAL", "PROMEDIO" };
    //Constructor
	public FinalesTableModel(List<Finales> finales) {
		super();
		this.finales = finales;

	}

	// Retorna el recuento de columnas
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMNAS.length;
	}

	// retorna el recuento de las filas
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.finales.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object retorno = null;
		// Se crea un objeto del tipo Alumno para obtener el elemento en la posición
		// especificada en esta lista.
		Finales c = this.finales.get(rowIndex);
		// Se crea un switch para retornar y llenar las filas en la tabla con los datos
		// obtenidos de los Alumnos registrados

		switch (columnIndex) {
		case 0:
			retorno = c.getId();
			break;
		case 1:
			retorno = c.getAlumno();
			break;
		case 2:
			retorno = c.getNombre();
			break;
		case 3:
			retorno = c.getApellido();
			break;
		case 4:
			retorno = c.getMateria();
			break;
		case 5:
			retorno = c.getDescripMateria();
			break;
		case 6:
			retorno = c.getNota();
			break;
		case 7:
			retorno = c.getFechafinal();
			break;
		case 8:
			retorno = c.getPromedio();
			break;
		}
		return retorno;
	}

	public String getColumnName(int index) {
		return COLUMNAS[index];
	}

}
