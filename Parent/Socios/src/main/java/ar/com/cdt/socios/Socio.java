package ar.com.cdt.socios;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

public class Socio {

	private Long id;

	@NotEmpty(message = "Los datos son requeridos.")
	private String datos;

	@NotEmpty(message = "El nombre es requerido.")
	private String nombre;

	private Calendar created = Calendar.getInstance();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreated() {
		return this.created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
