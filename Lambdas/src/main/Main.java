package main;

public class Main {

    public static void main(String[] args) {
        // Interfaces funcionales => Agregando funcionalidad a la interface
        /* Interface ISaludar */
        ISaludar saludar = (String usuario)
                -> System.out.println("Holas mundos: " + usuario);

        saludar.Saludar("Danicode");

        ISaludar SegundoSaludo = usuario
                -> {
            if (usuario.equals("danicode")) {
                System.out.println("Holas: " + usuario);
            } else {
                System.out.println("¿Quién eres?");
            }
        };
        SegundoSaludo.Saludar("danicode");

        /* Interface ISuma */
        ISuma suma = (valor1, valor2) -> valor1 + valor2;
        System.out.println("Suma: " + suma.suma(12, 2));

        suma = (valor1, valor2) -> {
            int resultado = valor1 + valor2;
            System.out.println("Suma: " + resultado);
            return resultado;
        };
        
        suma.suma(100, 2220);
    }

}
