package com.danicode.concurrencia.modelos;

import java.util.LinkedList;
import java.util.Queue;

public class Peluqueria {
    private static final int NUM_PELUQUERAS = 3;
    private static final int NUM_CLIENTES = 10;

    // Cola (ultimo que entra, ultimo que sale
    private Queue<Cliente> colaClientes = new LinkedList<>();
    // Objeto de bloqueo
    Object lock = new Object();

    private Thread[] peluqueras;

    public Peluqueria() {
        // iniciar los hilos
        peluqueras = new Thread[NUM_PELUQUERAS];
        // crear los hilos
        for (int i = 0; i < NUM_PELUQUERAS; i++) {
            peluqueras[i] = new Thread(new Peluquera(this, "Peluquera " + (i + 1)));
        }
    }

    public void iniciar() {
        for (Thread peluquera : peluqueras) {
            peluquera.start();
        }
        // Agregar los clientes
        for (int i = 1; i <= NUM_CLIENTES; i++) {
            Cliente cliente = new Cliente("Cliente " + i);
            agregarCliente(cliente);
        }
    }

    /* synchronized => Evitar que dos hilos accedan simultaneamente al mismo cliente
       (bloque de codigo ya ejecutandose por otro hilo) */

    public void agregarCliente(Cliente cliente) {
        synchronized (lock) {
            colaClientes.offer(cliente);
            // notify se usa en multihilos para controlar la sincronizacion y comunicacion entre hilos
            // Se llama en un objeto y notifica a otro hilo
            lock.notify();
        }
    }

    public Cliente obtenerCliente() throws InterruptedException {
        synchronized (lock) {
            while (colaClientes.isEmpty()) {
                // wait() => se usa en multihilos para suspender la ejecucion de un hilo hasta que cumpla
                // alguna condicion
                lock.wait();
            }
            // poll() => estructura de datos (colas) para eliminar y devolver el elemento en la parte frontal
            // de la cola (si la cola no esta vacia). Devuelve null, si la cola esta vacia
            // [1, 2, 3, 4, 5].poll() => retorna 1 y la nueva cola sera [2, 3, 4, 5]
            return colaClientes.poll();
        }
    }
}
