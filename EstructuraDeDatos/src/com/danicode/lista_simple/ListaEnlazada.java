package com.danicode.lista_simple;

public class ListaEnlazada {

    protected Nodo primerNodo;

    public ListaEnlazada() {
        this.primerNodo = null;
    }

    public ListaEnlazada agregarAlInicio(int dato) {
        Nodo nodo = new Nodo(dato);
        nodo.setReferencia(this.primerNodo);
        this.primerNodo = nodo;
        return this;
    }

    public void agregarAlFinal(int dato) {
        Nodo nodo = new Nodo(dato);
        nodo.setReferencia(null); // El nodo final no apunta a nada
        if (this.primerNodo == null) {
            this.primerNodo = nodo;
            return;
        }
        Nodo temporal = this.primerNodo;
        while (temporal.getReferencia() != null) {
            temporal = temporal.getReferencia();
        }
        // for (temporal = this.primerNodo; temporal.getReferencia() != null; temporal = temporal.getReferencia());
        temporal.setReferencia(nodo);

    }

    public Nodo buscar(int dato) {
        Nodo nodo;
        for (nodo = this.primerNodo; nodo != null; nodo = nodo.getReferencia()) {
            if (dato == nodo.getDato()) {
                return nodo;
            }
        }
        return null;
    }

    public void eliminar(int dato) {
        boolean encontrado = false;
        Nodo nodoActual = this.primerNodo;
        Nodo nodoAnterior = null;

        while (nodoActual != null && !encontrado) {
            encontrado = nodoActual.getDato() == dato;
            if (!encontrado) {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.getReferencia();
            }
        }

        if (nodoActual != null) {
            if (nodoActual == this.primerNodo) {
                this.primerNodo = nodoActual.getReferencia();
            } else {
                nodoAnterior.setReferencia(nodoActual.getReferencia());
            }
        }
    }

    public void verLista() {
        Nodo nodo;
        nodo = this.primerNodo;
        while (nodo != null) {
            System.out.println(nodo.getDato());
            nodo = nodo.getReferencia();
        }
    }
}
