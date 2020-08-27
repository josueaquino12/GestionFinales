package ar.com.unpaz.modelo;


/*Clase Anio para setear y obtener los datos u objetos que se clasifican en la tabla Materia 
 * especificando que materias pertenecen a cada a�o*/

public class Anio{

	private int anio;
	private String descripcion;
	
	public Anio (int anio, String descripcion) {
		this.anio = anio;
		this.descripcion = descripcion;
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
		
		return  anio  + " - " + descripcion;
	}
	
	
	
}
