package com.pe.test.pacientes.entities;

public class TipoDocumentoIdentidad {

    private Long idTipoDocumentoIdentidad;
    private String descripcionTipoDocumentoIdentidad;
    private String codigoIeds;
    private Boolean flEstado;

    public TipoDocumentoIdentidad() {
    }

    public Long getIdTipoDocumentoIdentidad() {
        return idTipoDocumentoIdentidad;
    }

    public void setIdTipoDocumentoIdentidad(Long idTipoDocumentoIdentidad) {
        this.idTipoDocumentoIdentidad = idTipoDocumentoIdentidad;
    }

    public String getDescripcionTipoDocumentoIdentidad() {
        return descripcionTipoDocumentoIdentidad;
    }

    public void setDescripcionTipoDocumentoIdentidad(String descripcionTipoDocumentoIdentidad) {
        this.descripcionTipoDocumentoIdentidad = descripcionTipoDocumentoIdentidad;
    }

    public String getCodigoIeds() {
        return codigoIeds;
    }

    public void setCodigoIeds(String codigoIeds) {
        this.codigoIeds = codigoIeds;
    }

    public Boolean getFlEstado() {
        return flEstado;
    }

    public void setFlEstado(Boolean flEstado) {
        this.flEstado = flEstado;
    }
}
