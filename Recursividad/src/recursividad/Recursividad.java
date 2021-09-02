package recursividad;

public class Recursividad {

    public static void main(String[] args) {
        recursiveMethod(4);
        /* Retorna:
            n es menor que 1
            n => 1
            n => 2
            n => 3
            n => 4
         */
    }

    private static void recursiveMethod(int n) {
        if (n < 1) {
            System.err.println("n es menor que 1");
        } else {
            recursiveMethod(n - 1);
            System.err.println("n => " + n);
        }
    }
}
