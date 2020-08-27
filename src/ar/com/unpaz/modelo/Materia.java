package ar.com.unpaz.modelo;
/*Clase Finales para setear y obtener los datos u objetos que se presentan en tabla Materia*/
public class Materia {
    public int idMateria;
    private String descripcion;
    private int anio;

    public Materia(int id, String descripcion) {
		this.idMateria = id;
		this.descripcion = descripcion;
	}

	public Materia() {
		// TODO Auto-generated constructor stub
	}

	public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "" + idMateria + " - " + descripcion ;
    }
}
