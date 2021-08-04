package com.danicode;

import com.danicode.lista_simple.ListaEnlazada;
import com.danicode.lista_simple.ListaEnlazadaOrdenada;

public class Main {

    public static void main(String[] args) {
        ListaEnlazadaOrdenada listaEnlazadaOrdenada = new ListaEnlazadaOrdenada();
        listaEnlazadaOrdenada.insertarOrden(4);
        listaEnlazadaOrdenada.insertarOrden(2);
        listaEnlazadaOrdenada.insertarOrden(3);
        listaEnlazadaOrdenada.insertarOrden(7);
        listaEnlazadaOrdenada.insertarOrden(5);
        listaEnlazadaOrdenada.verLista();
    }
}
