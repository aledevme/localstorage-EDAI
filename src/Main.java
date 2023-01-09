import localstorage.LinkedList;
import localstorage.LocalStorage;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner(System.in);
//        System.out.println("Bienvenido a sistema de gestion de Biblioteca");
//        System.out.println("Menu de opciones");
//        System.out.println("1 - Ingresar un libro");
//        System.out.println("2 - Ingresar varios libros");
//        System.out.println("3 - Borrar un libro");
//
//        System.out.print("Escribe tu opción: ");
//        int option = scanner.nextInt();
//        switch (option){
//            case 1:
//                Book bookItem = new Book();
//                System.out.print("Ingrese titulo del libro: ");
//                scanner.nextLine();
//                String bookName = scanner.nextLine();
//                bookItem.setTitle(bookName);
//
//                System.out.print("Ingrese autor del libro: ");
//                scanner.nextLine();
//                String author = scanner.nextLine();
//                bookItem.setAuthor(author);
//
//                LinkedList<Book> books = new LinkedList<>();
//                books.add(bookItem);
//                books.add(bookItem);
//                LocalStorage.setItem("books", books.toArray());
//                break;
//            case 2:
//                System.out.println("Haz escogido ingresar varios libros");
//                LinkedList<Book> booksList = (LinkedList<Book>) LocalStorage.getItem("books");
//                System.out.println(booksList);
//
//                System.out.println(booksList.size());
//                for (int i = 0; i < booksList.size(); i++) {
//                    Book item = (Book) booksList.get(i);
//                    System.out.println(item.getAuthor());
//                }
//
//                break;
//            case 3:
//                System.out.println("Haz escogido ingresar varios libros");
//                break;
//            default:
//                System.out.println("Opción no valida");
//                break;
//        }


        //LinkedList<String, String> books = new LinkedList<>();
//        Book item = new Book();
//        item.setTitle("peter pan");
//        item.setAuthor("peter pan");
//        // Crea dos libros y los añade a la lista
//        books.add(item.toJSON());
//        books.add(item.toJSON());

        // Guarda el array en el archivo con el método setItem de LocalStorage
        //LocalStorage.setItem("books", books.toArray());

        //ejemplo de recuperar listado de objetos del archivo de texto
        LinkedList<String, String> booksData = (LinkedList<String, String>) LocalStorage.getItem("books");

        for (int i = 0; i < booksData.size(); i++) {

            Pattern pattern = Pattern.compile("\"(.+?)\":\"(.+?)\"");
            Matcher matcher = pattern.matcher(booksData.get(i));

            String author = null;
            String title = null;
            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2);
                if (key.equals("author")) {
                    author = value;
                } else if (key.equals("title")) {
                    title = value;
                }
            }

            System.out.println(author);
            System.out.println(title);
        }


    }
}
