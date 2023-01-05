package localstorage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class LocalStorage<T> {

    private static final LinkedList<Object> storage = new LinkedList<>();
    private static final String FILE_NAME = "storage.txt";

    public static <T> void setItem(String key, T value) throws IOException {
        int index = getIndex(key);
        if (index == -1) {
            storage.add(new Item<T>(key, value));
        } else {
            storage.set(index, new Item<T>(key, value));
        }
        writeToFile(key, value);
    }

    public static <T> T getItem(String key) {
        int index = getIndex(key);
        if (index == -1) {
            return null;
        }
        return ((Item<T>) storage.get(index)).getValue();
    }

    public static void removeItem(String key) throws IOException {
        int index = getIndex(key);
        if (index != -1) {
            storage.remove(index);
        }
        writeToFile(key, null);
    }

    private static int getIndex(String key) {
        for (int i = 0; i < storage.size(); i++) {
            Item item = (Item) storage.get(i);
            if (item.getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean keyExists(String key) {
        return getIndex(key) != -1;
    }

    private static void writeToFile(String key, Object value) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(key + ": " + (value != null ? value.toString() : "null"));
            bw.newLine();
        }
    }
}

