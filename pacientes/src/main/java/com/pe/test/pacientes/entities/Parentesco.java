package com.pe.test.pacientes.entities;

public class Parentesco {

    private Long idParentesco;
    private String noParentesco;
    private String ilActivo;

    public Parentesco() {
    }

    public Long getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(Long idParentesco) {
        this.idParentesco = idParentesco;
    }

    public String getNoParentesco() {
        return noParentesco;
    }

    public void setNoParentesco(String noParentesco) {
        this.noParentesco = noParentesco;
    }

    public String getIlActivo() {
        return ilActivo;
    }

    public void setIlActivo(String ilActivo) {
        this.ilActivo = ilActivo;
    }
}
