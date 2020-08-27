package ar.com.unpaz.taller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ar.com.unpaz.modelo.Finales;
public class FinalesDAO {


	private static final String LISTAFINALESJOIN = "SELECT F.ID,F.DNI,A.NOMBRE,A.APELLIDO,F.ID_MATERIA,M.DESCRIPCION,F.NOTA,F.FECHA_FINAL, dbo.porcarrera(F.DNI, 7) AS PROMEDIO FROM FINALES F INNER JOIN ALUMNO A ON F.DNI = A.DNI INNER JOIN MATERIA M ON F.ID_MATERIA = M.ID";

	private static final String MAXIDFINAL = "SELECT ISNULL(MAX(ID),0) + 1 AS MAXID FROM FINALES";

	private static final String INSERTARFINALES = "INSERT INTO FINALES(ID, DNI, ID_MATERIA, NOTA, FECHA_FINAL) values(?,?,?,?,?)";

	private static final String BORRARFINALES = "DELETE FROM FINALES WHERE ID = ?";

	private static final String ACTUALIZARNOTA = "update finales set nota = ?  where DNI = ? and id_materia = ?";

	
	//trae todos los datos de las finales registradas en la BD
	public ArrayList<Finales> getFinales() {

		ArrayList<Finales> finales = new ArrayList<Finales>();
		try {

			Connection con = Conexion.getConexion();

			PreparedStatement ps = con.prepareStatement(LISTAFINALESJOIN);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Finales f = new Finales();

				f.setId(rs.getInt("ID"));
				f.setAlumno(rs.getInt("DNI"));
				f.setNombre(rs.getString("NOMBRE"));
				f.setApellido(rs.getString("APELLIDO"));
				f.setMateria(rs.getInt("ID_MATERIA"));
				f.setDescripMateria(rs.getString("DESCRIPCION"));
				f.setNota(rs.getFloat("NOTA"));
				f.setFechafinal(rs.getDate("FECHA_FINAL"));
				f.setPromedio(rs.getBigDecimal("PROMEDIO"));

				finales.add(f);
			}

			rs.close();
			ps.close();
			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return finales;
	}

	// obtiene el valor mayor del ID que se creara
	private int getMaxIdFinal(Connection con) throws SQLException {
		int retorno = -1;

		PreparedStatement ps = con.prepareStatement(MAXIDFINAL);
		ResultSet rs = ps.executeQuery();
		rs.next();
		retorno = rs.getInt("MAXID");
		rs.close();
		ps.close();

		return retorno;
	}

	// agrega finales a la BD
	public int insertarFinales(Finales f) {
		int retorno = 0;
		Connection con = null;
		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false);
			int maxID = this.getMaxIdFinal(con);

			PreparedStatement ps = con.prepareStatement(INSERTARFINALES);
			ps.setInt(1, maxID);
			ps.setInt(2, f.getAlumno());
			ps.setInt(3, f.getMateria());
			ps.setFloat(4, f.getNota());
			ps.setDate(5, f.getFechafinal());

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

	
	//elimina las finales seleccionadas
	public int BorrarFinales(Finales f) {
		int retorno = 0;
		Connection con = null;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(BORRARFINALES);
			ps.setInt(1, f.getId());
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

	
	//modifica solamente la nota de la final seleccionada 
	public int ActualizarNotaFinal(Finales f) {

		int retorno = 0;
		Connection con = null;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(ACTUALIZARNOTA);

			ps.setFloat(1, f.getNota());
			ps.setInt(2, f.getAlumno());
			ps.setInt(3,f.getMateria());

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
	
	
	

}
