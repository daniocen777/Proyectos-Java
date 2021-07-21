package com.danicode.restaurantedata.enums;

/*
 * C�digos de error de la aplicaci�n
 * */

public enum CodigoEnum {
	SINTAXIS_SQL_ERROR_CODE(1064);

	private int code;

	private CodigoEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
