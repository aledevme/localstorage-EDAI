import localstorage.LocalStorage;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String key = "valorAleatorio";
        int valuex = new Random().nextInt();
        LocalStorage.setItem(key, valuex);
        int value = (int) LocalStorage.getItem("valorAleatorio");
        System.out.println(value);
    }
}