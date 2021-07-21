package com.danicode.restaurantedata.entity;

import java.time.LocalDateTime;

/*
 * Clase que contiene propiedades comunes a todas las clases
 * */
public class CommonEntity {
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaModificacion;
	private int estatus;

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int isEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

}
