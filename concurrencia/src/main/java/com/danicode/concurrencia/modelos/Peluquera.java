package com.danicode.concurrencia.modelos;

import java.util.Random;

public class Peluquera implements Runnable {

    private final Peluqueria peluqueria;
    private final String nombre;

    public Peluquera(Peluqueria peluqueria, String nombre) {
        this.peluqueria = peluqueria;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                Cliente cliente = peluqueria.obtenerCliente();
                System.out.println(nombre + " comienza a atender a " + cliente.getNombre());
                // tiempo para atenderlo
                int tiempoAtencion = random.nextInt(10) + 1;
                Thread.sleep(tiempoAtencion * 1000);
                System.out.println(
                        nombre + " terminó de atender a " + cliente.getNombre() + " en " + tiempoAtencion + " segundos");

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
