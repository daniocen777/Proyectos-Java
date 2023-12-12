package listas_enlazadas;

public class ListaSimple {
    private Nodo inicio;
    private Nodo fin;
    private int cantidadElementos;

    // Se crea la lista
    public ListaSimple() {
        this.inicio = null;
        this.fin = null;
    }

    // saber si lista isEmpty
    public boolean listaVacia() {
        return inicio == null && fin == null;
    }

    // Agregar al inicio
    public void agregarAlInicio(int dato) {
        // Cuando no hay datos
        if (listaVacia()) {
            inicio = fin = new Nodo(dato);
        } else {
            // Cuando hay datos
            inicio = new Nodo(dato, inicio);
        }
        cantidadElementos++;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public Nodo getFin() {
        return fin;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }
}
