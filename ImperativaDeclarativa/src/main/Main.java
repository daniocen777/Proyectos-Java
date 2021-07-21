package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        LinkedList<User> users = new LinkedList();
        users.add(new User("Dani", 24));
        users.add(new User("Lola", 19));
        users.add(new User("Pepe", 25));
        users.add(new User("Carmen", 14));
        users.add(new User("Karen", 25));
        users.add(new User("Lorenzo", 11));
        /* ------------------  Cantidad de usuarios mayores a 18 ------------------ */
        // Paradigma Imperativo
        int contador = 0;
        int contador2;
        for (User user : users) {
            if (user.getAge() > 18) {
                contador += 1;
            }
        }

        System.out.println("Cantidad: " + contador);

        // Paradigma Declarativo => Qué se debe hacer, no el cómo
        // stream => Posee todos los elementos de la lista
        // users.stream().count() => tipo Long
        contador = (int) users.stream().filter(user -> user.getAge() > 18).count();
        System.out.println("Cantidad: " + contador);

        /* ------------------ Cantidad de usuarios mayores a 18 y username comience con L ------------------ */
        contador2 = (int) users.stream().filter(user -> user.getAge() > 18).filter(user -> user.getUsername().startsWith("L")).count();
        System.out.println("Cantidad: " + contador2);

        /* ------------------ Se pueden crear Streams a traves de: ------------------ */
        // De una Colección (List)
        LinkedList<User> users2 = new LinkedList();
        Stream<User> usersStream = users2.stream();

        // Otro Stream
        Stream<User> filter = usersStream.filter(user -> user.getAge() > 20);

        // De un Arreglo
        int[] numbers = {1, 2, 3, 4};
        Stream numbersStream = Stream.of(numbers);

        // De una Secuencia
        Stream names = Stream.of("Lola", "Pepe", "María");

        /* ------------------ Tipos de métodos ------------------ */
        // 1. Estructura de proceso
        // 2. Iteración interna
        Stream<User> streamUser = users.stream();
        // Filter => return Abstracción (nuevo stream)
        System.out.println(streamUser.filter(user -> user.getAge() > 18));

        /* ------------------ Convertir a una lista a partes de una abstracción ------------------ */
        List<User> userList = users.stream().filter(user -> user.getAge() > 18).collect(Collectors.toList());
        System.out.println(userList);

        /* ------------------ Transformar valores ------------------ */
        // De una lista de números, a sus cuadrados
        // map => Permite tranformar objetos del stream en otros objetos
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> numberArray = numbers2.stream().map(number -> number * number).collect(Collectors.toList());
        System.out.println(numbers2);
        System.out.println(numberArray);

        /* ------------------ Unir Colecciones ------------------ */
        List<String> names1 = Arrays.asList("Dani 1", "Dani 2");
        List<String> names2 = Arrays.asList("Dani 3", "Dani 4");
        List<String> names3 = Arrays.asList("Dani 5", "Dani 6");
        // Una única lista
        List<String> result = Stream.of(names1, names2, names3).flatMap(element -> element.stream()).collect(Collectors.toList());
        System.out.println(result);

        /* ------------------ ForEach => trabajar cada elemento ------------------ */
        // Cuadrado de una lista
        List<Integer> basicNumbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> nu = List.of(1, 2, 3, 4);
        nu.stream().map(num -> num * num).forEach(element -> System.out.println(element));
        basicNumbers.stream().map(num -> num * num).forEach(element -> System.out.println(element));

        /* ------------------ Encontrar elementos ------------------ */
        // Saber si estudiante tiene al menos una nota no aprobatoria
        // Retorna boolean si tiene al menos una mala nota
        // -- Primara forma
        List<Integer> notas = List.of(15, 12, 16, 20, 13, 14);
        boolean resultadoPrimeraForma = (notas.stream().filter(nota -> nota < 11).count()) > 0;
        System.out.println("¿Hay notas desaprobatorias? => " + resultadoPrimeraForma);
        // -- Segunda forma
        boolean resultadoSegundaForma = notas.stream().anyMatch(nota -> nota < 11);
        System.out.println("¿Hay notas desaprobatorias? => " + resultadoSegundaForma);

        // Nombre del usuario de edad = 25
        // findAny() => Devuelve sólo 1 elemento
        // findFirst => Devuelve el primero qe cumple condición
        User usuario = users.stream().filter(user -> user.getAge() == 25).findAny().get();
        // System.out.println("usuario => " + usuario.getUsername());
        // Todos que tengas 25
        List<User> usuariosDe25 = users.stream().filter(user -> user.getAge() == 25).collect(Collectors.toList());
        // System.out.println(usuariosDe25);
        // El primer usuario que tenga 25
        User primerUsuario = users.stream().filter(user -> user.getAge() == 25).findFirst().get();
        // System.out.println(primerUsuario);
        // -- Si no se encuentra un usuario con la condición => No usar get(), Usar OrElse
        User userDefoult = new User("Sin nombre", 30);
        User usuarioNoCumple = users.stream().filter(user -> user.getAge() == 25).findFirst().orElse(userDefoult);

        /* ------------------ Funciones Matemáticas ------------------ */
        // Cantidad de elementos
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Long cantidad = numeros.stream().count();
        System.out.println("Cantidad => " + cantidad);
        // Sumar elementos | intStream => mapToINt
        int suma = numeros.stream().mapToInt(num -> num).sum();
        System.out.println("suma => " + suma);
        // Promedio
        double promedio = numeros.stream().mapToInt(num -> num).average().orElse(0);
        System.out.println("promedio => " + promedio);
        // Menor número
        int min = numeros.stream().mapToInt(num -> num).min().getAsInt();
        System.out.println("Valor mínimo => " + min);
        // Mayor número
        int max = numeros.stream().mapToInt(num -> num).max().getAsInt();
        System.out.println("Valor máximo => " + max);

        /* ------------------ Reducción de Elementos ------------------ */
        // Es un acumulador que devuelve un solo valor
        // reduce(calorInicial, (valorADevolver, elementoDeLaLista) -> valor)
        // Symar todos los elementos
        int resultadoAcumulador = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(0, (acumulador, elemento) -> acumulador + elemento);
        System.out.println("resultadoAcumulador => " + resultadoAcumulador);
        // -- Generar nuevo String con | a partir de los lenguajes:
        // String res = "dd";
        String res = Stream.of("java", "C", "Python", "Ruby").reduce("", (acumulador, lenguaje) -> acumulador + " | " + lenguaje);
        String res2 = res.substring(2, res.length() - 1);
        System.out.println("resultadoAcumulador => " + res2);

        /* ------------------ Elementos Únicos ------------------ */
        List<String> nombres = List.of("Dani 1", "Dani 2", "Dani 3", "Dani 2", "Dani 4", "Dani 5");
        nombres.stream().distinct().forEach(elemento -> System.out.println(elemento));

        /* ------------------ Ordenamiento ------------------ */
        List<Integer> numForOrder = List.of(2, 4, 1, 10, 4, 12, 5, 19);
        // sorted() => return stream
        // Ordenando ascendente
        List<Integer> numbersOrderAsc = numForOrder.stream().sorted().collect(Collectors.toList());
        System.out.println("Números ordernados ascendente => " + numbersOrderAsc);
        // Ordenando descendente
        List<Integer> numbersOrderDesc = numForOrder.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Números ordernados descendente=> " + numbersOrderDesc);
        // -- Ordenamientos de objetos
        LinkedList<Book> books = new LinkedList<>();
        books.add(new Book("La Orestiada", 600));
        books.add(new Book("El Ingenioso Idalgo Don Quijote de la Mancha", 300));
        books.add(new Book("2066", 100));
        books.add(new Book("El Tambor de Hojalata", 400));
        books.add(new Book("Casi un Objeto", 120));
        books.add(new Book("Animales Fantásticos", 250));
        books.add(new Book("Zoológicos", 250));
        // Mostrando 3 títulos más vendidos y ordenados por copia
        Comparator<Book> bookComparator = Comparator.comparing(book -> book.getCopies());
        books.stream().sorted(bookComparator.reversed()).limit(3).forEach(book -> System.out.println(book.getTitle()));
        // -- Condición para las comparaciones
        Comparator<Book> comparator;
        if (books.stream().count() > 4) {
            comparator = Comparator.comparing(book -> book.getCopies());
        } else {
            comparator = Comparator.comparing(book -> book.getTitle());
        }
        System.out.println("*******************");
        books.stream().sorted(comparator.reversed()).limit(3).forEach(book -> System.out.println(book.getTitle()));

        /* ------------------ Saltos ------------------ */
        // Segundo y tercer libro más vendido
        books.stream().sorted(comparator.reversed()).limit(3).skip(1).forEach(book -> System.out.println(book.getTitle()));

        /* ------------------ Métodos por referencia => Métodos Estáticos ------------------ */
        // Cubo de la lista
        List<Integer> numForCube = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // Aplicando métodos por refeencia => Clase/instancia::method
        numForCube.stream().map(Main::cube).forEach(System.out::println);

        /* ------------------ Métodos por referencia => Métodos de instancia ------------------ */
        calculator calculator = new calculator(); // instancia
        System.out.println("*******************");
        numForCube.stream().map(calculator::toCube).forEach(System.out::println);

        /* ------------------ Métodos por referencia => Métodos de instancia de un objeto arbitrario ------------------ */
        // Nombre de todos los usuarios
        System.out.println("*******************");
        users.stream().map(User::getUsername).forEach(System.out::println);

        /* ------------------ Métodos por referencia => Constructor por referencia ------------------ */
        // Crear nuevos usuarios e imprimir los nombres
        System.out.println("*******************");
        IUser iUser = User::new;
        User user1 = iUser.create("Memo 1", 12);
        User user2 = iUser.create("Memo 2", 21);
        User user3 = iUser.create("Memo 3", 40);
        User user4 = iUser.create("Memo 4", 56);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.stream().map(User::getUsername).forEach(System.out::println);
    }

    public static int cube(int number) {
        return number * number * number;
    }

}
