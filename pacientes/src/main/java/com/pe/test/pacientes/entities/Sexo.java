package com.pe.test.pacientes.entities;

public class Sexo {

    private Long idSexo;
    private String descripcionSexo;
    private Boolean flEstado;

    public Sexo() {
    }

    public Long getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Long idSexo) {
        this.idSexo = idSexo;
    }

    public String getDescripcionSexo() {
        return descripcionSexo;
    }

    public void setDescripcionSexo(String descripcionSexo) {
        this.descripcionSexo = descripcionSexo;
    }

    public Boolean getFlEstado() {
        return flEstado;
    }

    public void setFlEstado(Boolean flEstado) {
        this.flEstado = flEstado;
    }
}
