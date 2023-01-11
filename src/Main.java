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
            System.out.println("\n*Bienvenido a sistema de gestion de Biblioteca*");
            System.out.println("Menu de opciones:");
            System.out.println("1 - Ingresar un libro");
            System.out.println("2 - Ingresar varios libros");
            System.out.println("3 - Borrar un libro");
            System.out.println("4 - Obtener todas las llave valor");
            System.out.println("5 - Eliminar una llave");
            System.out.println("6 - Agregar una llave valor");

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
                        LocalStorage.removeKey("books");
                        LocalStorage.setItem("books", books.toArray());
                        System.out.println("Haz ingresado el primer libro a tu colección!");
                    }
                    break;
                case 2:
                    System.out.println("\nListando todos los libros...");
                    booksData = (LinkedList<String, String>) LocalStorage.getItem("books");
                    if(booksData.size() == 0){
                        System.out.println("Esta vacio");
                    }else{
                        for (int i = 0; i < booksData.size(); i++) {
                            Pattern pattern = Pattern.compile("\"(.+?)\":\"(.+?)\"");
                            Matcher matcher = pattern.matcher(booksData.get(i));

                            String authorValue = "";
                            String titleValue = "";

                            while (matcher.find()) {
                                String key = matcher.group(1);
                                String value = matcher.group(2);
                                if (key.equals("author")) {
                                    authorValue = value;
                                } else if (key.equals("title")) {
                                    titleValue = value;
                                }
                            }
                            System.out.println((i + 1) + " Libro: "+ titleValue+" / Autor: "+authorValue);
                        }
                    }
                    break;
                case 3:
                    booksData = (LinkedList<String, String>) LocalStorage.getItem("books");
                    scanner = new Scanner(System.in);
                    System.out.println("Ingresa el numero del libro:");
                    int indexOfBook = scanner.nextInt();
                    if(booksData.get(indexOfBook - 1) != null){
                        booksData.remove(indexOfBook - 1);
                        LocalStorage.removeKey("books");
                        LocalStorage.setItem("books", booksData.toArray());
                    }else{
                        System.out.println("No existe el libro para eliminarse");
                    }
                    break;
                case 4:
                    String[] keys = LocalStorage.getAllKeys();

                    for (int i = 0; i < keys.length; i++) {
                        System.out.println(keys[i]);
                    }
                    break;
                case 5:
                    scanner = new Scanner(System.in);
                    System.out.println("Ingresa el nombre de la llave:");
                    String keyNameInput = scanner.nextLine();
                    if(LocalStorage.keyExist(keyNameInput)){
                        LocalStorage.removeKey(keyNameInput);
                    }else{
                        System.out.println("No existe esa llave almacenada");
                    }
                    break;
                case 6:
                    scanner = new Scanner(System.in);
                    System.out.println("Ingresa el nombre de la llave:");
                    String keyNameInputToSave = scanner.nextLine();
                    System.out.println("Ingresa el valor de la llave:");
                    Object valueInput = scanner.nextLine();
                    LocalStorage.setItem(keyNameInputToSave, valueInput);
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (true);


    }

}
