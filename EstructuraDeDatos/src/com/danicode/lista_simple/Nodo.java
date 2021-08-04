package com.danicode.lista_simple;

public class Nodo {

    private int dato;
    private Nodo referencia;

    public Nodo(int dato) {
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getReferencia() {
        return referencia;
    }

    public void setReferencia(Nodo referencia) {
        this.referencia = referencia;
    }

}
