package passwordcomplejo;

import java.util.Scanner;
import javax.sound.midi.SysexMessage;

/**
 * Crear funcion que retorne boolean si password tiene al menos 6 caracteres,
 * una mayuscula, 1 minuscula y un numero
 */
public class PasswordComplejo {

    private static boolean esPasswordComplejo(String password) {
        return password.length() >= 6 && password.matches(".*\\d.*")
                && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println("El Password es complejo: " + esPasswordComplejo(userInput));
    }

}
