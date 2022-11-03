package com.pe.test.pacientes.entities;

import java.time.LocalDate;

public class Paciente {

    private Long idPaciente;
    private Long idTipoDocide;
    private String noDocide;
    private String noApepat;
    private String noApemat;
    private String noNombres;
    private Long idSexo;
    private LocalDate feNacimiento;
    private String noLugarNacimiento;
    private String noDireccion;
    private String coUbigeo;

    public Paciente() {
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdTipoDocide() {
        return idTipoDocide;
    }

    public void setIdTipoDocide(Long idTipoDocide) {
        this.idTipoDocide = idTipoDocide;
    }

    public String getNoDocide() {
        return noDocide;
    }

    public void setNoDocide(String noDocide) {
        this.noDocide = noDocide;
    }

    public String getNoApepat() {
        return noApepat;
    }

    public void setNoApepat(String noApepat) {
        this.noApepat = noApepat;
    }

    public String getNoApemat() {
        return noApemat;
    }

    public void setNoApemat(String noApemat) {
        this.noApemat = noApemat;
    }

    public String getNoNombres() {
        return noNombres;
    }

    public void setNoNombres(String noNombres) {
        this.noNombres = noNombres;
    }

    public Long getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Long idSexo) {
        this.idSexo = idSexo;
    }

    public LocalDate getFeNacimiento() {
        return feNacimiento;
    }

    public void setFeNacimiento(LocalDate feNacimiento) {
        this.feNacimiento = feNacimiento;
    }

    public String getNoLugarNacimiento() {
        return noLugarNacimiento;
    }

    public void setNoLugarNacimiento(String noLugarNacimiento) {
        this.noLugarNacimiento = noLugarNacimiento;
    }

    public String getNoDireccion() {
        return noDireccion;
    }

    public void setNoDireccion(String noDireccion) {
        this.noDireccion = noDireccion;
    }

    public String getCoUbigeo() {
        return coUbigeo;
    }

    public void setCoUbigeo(String coUbigeo) {
        this.coUbigeo = coUbigeo;
    }
}
