import localstorage.LinkedList;
import localstorage.LocalStorage;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Bienvenido a sistema de gestion de Biblioteca");
            System.out.println("Menu de opciones");
            System.out.println("1 - Ingresar un libro");
            System.out.println("2 - Ingresar varios libros");
            System.out.println("3 - Borrar un libro");

            System.out.print("Escribe tu opción: ");
            int option = scanner.nextInt();
            LinkedList<String, String> booksData;
            switch (option){
                case 1:
                    Book item = new Book();
                    String title, author;

                    scanner = new Scanner(System.in);

                    System.out.println("Ingresa titulo del libro:");
                    title = scanner.nextLine();
                    System.out.println("Ingresa el autor del libro:");
                    author = scanner.nextLine();
                    item.setTitle(title);
                    item.setAuthor(author);
                    booksData = (LinkedList<String, String>) LocalStorage.getItem("books");
                    if (LocalStorage.keyExist("books")){
                        booksData.add(item.toJSON());

                        System.out.println(booksData.size());
                        LocalStorage.removeKey("books");
                        LocalStorage.setItem("books", booksData.toArray());
                        System.out.println("Haz ingresado un nuevo libro!");
                    }else{
                        LinkedList<String, String> books = new LinkedList<>();
                        books.add(item.toJSON());
                        LocalStorage.setItem("books", books.toArray());
                        System.out.println("Haz ingresado el primer libro a tu colección!");
                    }
                    break;
                case 2:
                    System.out.println("\nListando todos los libros...");
                    System.out.println(LocalStorage.getItem("books"));
//                    if( LocalStorage.getItem("books").equals("0")){
//                        System.out.println("No hay libros agregados\n");
//                    }else{
//                        booksData = (LinkedList<String, String>) LocalStorage.getItem("books");
//                        for (int i = 0; i < booksData.size(); i++) {
//                            Pattern pattern = Pattern.compile("\"(.+?)\":\"(.+?)\"");
//                            Matcher matcher = pattern.matcher(booksData.get(i));
//
//                            String authorValue = "";
//                            String titleValue = "";
//
//                            while (matcher.find()) {
//                                String key = matcher.group(1);
//                                String value = matcher.group(2);
//                                if (key.equals("author")) {
//                                    authorValue = value;
//                                } else if (key.equals("title")) {
//                                    titleValue = value;
//                                }
//                            }
//                            System.out.println((i + 1) + " Libro: "+ titleValue+" / Autor: "+authorValue);
//                        }
//                    }
                    break;
                case 3:
                    System.out.println("Eliminar libro");

                    int indexOfBook = 0;
                    scanner = new Scanner(System.in);

                    System.out.println("Ingresa el indice del libro que desea eliminar: ");
                    indexOfBook = scanner.nextInt();

                    booksData = (LinkedList<String, String>) LocalStorage.getItem("books");

                    System.out.println(booksData);
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (true);


    }

}
