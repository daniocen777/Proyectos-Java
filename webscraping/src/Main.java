import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // String link = "https://www.bbc.com/";
        LinkedList<String> links = new LinkedList<>();
        links.add("https://www.bbc.com/");
        links.add("https://www.bbc.com/news/world-asia-68476289");
        links.add("https://www.bbc.com/news/world-middle-east-68511112");
        links.add("https://www.bbc.com/sport/football/live/cp30n35g72et");
        links.add("https://www.bbc.com/sport/live/formula1/67916938");
        // * Descargar la web
        // parallel() => procesar en varios hilos (segun el procesador de pc)
        // Long timeStart = System.nanoTime();
        links.stream().parallel().forEach(Main::getWebContent);
        // Long timeEnd = System.nanoTime();
        // System.out.println("Tiempo de proceso: " + (timeEnd - timeStart));
    }

    // private synchronized static String getWebContent => Espera que termine la funcion
    private static String getWebContent(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Descargar contenido
            // String encoding = conn.getContentEncoding();
            InputStream inputStream = conn.getInputStream();
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return "Error";
        }

    }
}