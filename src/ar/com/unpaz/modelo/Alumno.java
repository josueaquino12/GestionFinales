package ar.com.unpaz.modelo;

	/*Clase Alumno para setear y obtener los datos u objetos que se presentan en la tabla Alumno*/

public class Alumno {
	int DNI;
	String nombre;
	String apellido;
	String email;
	public Alumno(int DNI, String nombre, String apellido) {
	    this.DNI = DNI;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public Alumno() {
		// TODO Auto-generated constructor stub
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		
		return this.DNI+" - "+this.nombre+" "+this.apellido;
		
	}
	
}
