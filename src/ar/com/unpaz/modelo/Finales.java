package ar.com.unpaz.modelo;

import java.sql.Date;

/*Clase Finales para setear y obtener los datos u objetos que se presentan en la tabla Finales*/
public class Finales {
	int id;
	public int materia;
	public int alumno;
	String nombre;
	String apellido;
	String descripMateria;
	float nota;
	Date fechafinal;
	java.math.BigDecimal promedio;
	


	public Finales(int idMateria, String nomMateria) {
		// TODO Auto-generated constructor stub
		this.materia = idMateria;
		this.descripMateria = nomMateria;
		
	}

	public Finales() {
		// TODO Auto-generated constructor stub
	}
	
	public java.math.BigDecimal getPromedio() {
		return promedio;
	}

	public void setPromedio(java.math.BigDecimal promedio) {
		this.promedio = promedio;
	}


	public String getDescripMateria() {
		return descripMateria;
	}

	public void setDescripMateria(String descripMateria) {
		this.descripMateria = descripMateria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getMateria() {
		return materia;
	}

	public void setMateria(int materia) {
		this.materia = materia;
	}

	public int getAlumno() {
		return alumno;
	}

	public void setAlumno(int alumno) {
		this.alumno = alumno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Date getFechafinal() {
		return fechafinal;
	}

	public void setFechafinal(Date fechafinal) {
		this.fechafinal = fechafinal;
	}
	
    public String toString() {
		
		return this.materia+" - "+this.descripMateria;
		
	}

}
