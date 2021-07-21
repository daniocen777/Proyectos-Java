package com.danicode.restaurantedata.entity;

import java.util.List;

public class TipoRestaurante extends CommonEntity {
	private int idTipoRestaurante;
	private String descripcion;
	// 1 TipoRestaurante => muchos Restaurantes
	List<Restaurante> restaurantes;

	public TipoRestaurante() {
		super();
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public TipoRestaurante(int idTipoRestaurante, String descripcion) {
		super();
		this.idTipoRestaurante = idTipoRestaurante;
		this.descripcion = descripcion;
	}

	public int getIdTipoRestaurante() {
		return idTipoRestaurante;
	}

	public void setIdTipoRestaurante(int idTipoRestaurante) {
		this.idTipoRestaurante = idTipoRestaurante;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoRestaurante [idTipoRestaurante=" + idTipoRestaurante + ", descripcion=" + descripcion + "]";
	}
}
