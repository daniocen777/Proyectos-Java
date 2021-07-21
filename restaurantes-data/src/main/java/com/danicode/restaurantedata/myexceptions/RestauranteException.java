package com.danicode.restaurantedata.myexceptions;

import com.danicode.restaurantedata.enums.CodigoEnum;

/*
 * Excepciones personalizadas para Restaurante
 * */

public class RestauranteException extends Exception {

	private static final long serialVersionUID = 1L; // Serial por defecto
	private int errorCode;

	public RestauranteException() {

	}

	/*
	 * @param mensaje => Mensaje de error para el usuario
	 */
	public RestauranteException(String mensaje, CodigoEnum codigoEnum) {
		super(mensaje);
		this.errorCode = codigoEnum.getCode();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
