import localstorage.LinkedList;
import localstorage.LocalStorage;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList<Integer> values2 = (LinkedList<Integer>) LocalStorage.getItem("valores2");

        for (int i = 0; i < values2.size(); i++) {
            System.out.println(values2.get(i));
        }
    }
}