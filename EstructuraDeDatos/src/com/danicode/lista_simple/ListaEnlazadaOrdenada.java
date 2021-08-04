package com.danicode.lista_simple;

public class ListaEnlazadaOrdenada extends ListaEnlazada {

    public ListaEnlazadaOrdenada() {
        super();
    }

    // Se tiene que crear siempre el caso cuando el nodo está vacio
    public ListaEnlazadaOrdenada insertarOrden(int dato) {
        Nodo nodoNuevo = new Nodo(dato);
        if (this.primerNodo == null) {
            this.primerNodo = nodoNuevo;
        } else if (dato < this.primerNodo.getDato()) {
            nodoNuevo.setReferencia(this.primerNodo);
            this.primerNodo = nodoNuevo;
        } else {
            Nodo nodoAnterior;
            Nodo nodoInicial;
            nodoAnterior = nodoInicial = this.primerNodo;
            // Si hay algo que recorrer
            while (nodoInicial.getReferencia() != null && dato > nodoInicial.getDato()) {
                nodoAnterior = nodoInicial;
                nodoInicial = nodoInicial.getReferencia();
            }

            if (dato > nodoInicial.getDato()) {
                nodoAnterior = nodoInicial;
            }

            nodoNuevo.setReferencia(nodoAnterior.getReferencia());
            nodoAnterior.setReferencia(nodoNuevo);
        }

        return this;
    }
}
