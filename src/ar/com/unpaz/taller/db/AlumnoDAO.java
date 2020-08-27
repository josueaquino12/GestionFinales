
package ar.com.unpaz.taller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import ar.com.unpaz.modelo.Alumno;

public class AlumnoDAO {

	// Se crean las consultas SQL
	private static final String LISTAALUMNOS = "SELECT A.DNI, A.NOMBRE, A.APELLIDO, A.EMAIL " + "FROM ALUMNO A ";

	private static final String ORDERBYALUMNOS = "ORDER BY A.NOMBRE";

	private static final String ORDERBYDNI = "ORDER BY A.DNI";

	private static final String INSERTARALUMNO = "INSERT INTO ALUMNO (DNI, NOMBRE, APELLIDO, EMAIL) values(?,?,?,?)";

	private static final String BORRARALUMNO = "DELETE FROM ALUMNO WHERE DNI = ?";

	private static final String MAXALUMNO = "SELECT ISNULL(MAX(DNI),0) + 1 AS MAXID FROM ALUMNO ";

	private static final String ACTUALIZARALUMNO = "UPDATE ALUMNO SET NOMBRE = ?, APELLIDO = ?, EMAIL = ? WHERE DNI = ?";

	public ArrayList<Alumno> getALUMNO() {
		// Se crea una lista
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		try {

			// Se establece una conexion con un objeto del tipo conexion
			Connection con = Conexion.getConexion();
			// Se crea una preparacion de consulta con la conexion a la BD obteniendo por
			// parametros las consultas escritas
			PreparedStatement ps = con.prepareStatement(LISTAALUMNOS + ORDERBYALUMNOS);
			//se ejecuta la consulta
			ResultSet rs = ps.executeQuery();

			//se hace un ciclo para encontrar los datos que se encuentre en cada columna de la tabla de BD
			while (rs.next()) {
				Alumno a = new Alumno();
				a.setDNI(rs.getInt("DNI"));
				a.setNombre(rs.getString("NOMBRE"));
				a.setApellido(rs.getString("APELLIDO"));
				a.setEmail(rs.getString("EMAIL"));

				//los datos encontrados se agregaran y se guardaran en la lista
				alumnos.add(a);
			}

			rs.close();
			ps.close();
			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return alumnos;
	}

	//Ordena los datos por dni
	public ArrayList<Alumno> getAlumnosOrderByDNI() {

		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		try {

			Connection con = Conexion.getConexion();

			PreparedStatement ps = con.prepareStatement(LISTAALUMNOS + ORDERBYDNI);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Alumno a = new Alumno();
				a.setDNI(rs.getInt("DNI"));
				a.setNombre(rs.getString("NOMBRE"));
				a.setApellido(rs.getString("APELLIDO"));
				a.setEmail(rs.getString("EMAIL"));

				alumnos.add(a);
			}

			rs.close();
			ps.close();
			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return alumnos;
	}

	//obtiene el siguiente numero mayor del dni a crear
	private int getMaxIdAlumno(Connection con) throws SQLException {
		int retorno = -1;

		PreparedStatement ps = con.prepareStatement(MAXALUMNO);
		ResultSet rs = ps.executeQuery();
		rs.next();
		retorno = rs.getInt("MAXID");
		rs.close();
		ps.close();

		return retorno;
	}

	//agrega alumnos con los siguientes datos
	public int insertarAlumno(Alumno a) {
		int retorno = 0;
		Connection con = null;
		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false);
			int maxDNI = this.getMaxIdAlumno(con);

			PreparedStatement ps = con.prepareStatement(INSERTARALUMNO);
			ps.setInt(1, maxDNI);
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getApellido());
			ps.setString(4, a.getEmail());

			retorno = ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			ps.close();
			con.close();

		} catch (SQLException ex) {
			try {
				ex.printStackTrace();
				con.rollback();
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return retorno;

	}

	//modifica datos de los alumnos
	public int ActualizarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		int retorno = 0;
		Connection con = null;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(ACTUALIZARALUMNO);
			ps.setString(1, alumno.getNombre());
			ps.setString(2, alumno.getApellido());
			ps.setString(3, alumno.getEmail());
			ps.setInt(4, alumno.getDNI());
			retorno = ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			ps.close();
			con.close();

		} catch (SQLException ex) {
			try {
				ex.printStackTrace();
				con.rollback();
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return retorno;
	}

	//elimina de la BD el registro del alumno seleccionado
	public int BorrarAlumno(Alumno alumno) {
		int retorno = 0;
		Connection con = null;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(BORRARALUMNO);
			ps.setInt(1, alumno.getDNI());
			retorno = ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			ps.close();
			con.close();

		} catch (SQLException ex) {
			try {
				ex.printStackTrace();
				con.rollback();
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return retorno;
	}
	
	//desde un combobox se lista y se filtra todos el dni, nombre y apellido del alumno
	public void listarAlumnoComboBox(JComboBox combo) {

		DefaultComboBoxModel value;

		try {

			Connection con = Conexion.getConexion();

			PreparedStatement ps = con.prepareStatement(LISTAALUMNOS + ORDERBYALUMNOS);
			ResultSet rs = ps.executeQuery();
			value = new DefaultComboBoxModel();
			combo.setModel(value);

			while (rs.next()) {

				value.addElement(new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}

			rs.close();
			ps.close();
			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
