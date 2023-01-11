package localstorage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalStorage<T> {
    private static File file;
    private static Map<String, Object> map;

    static {
        file = new File("local_storage.txt");
        map = new HashMap<>();
        loadFromFile();
    }

    private static void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] keyValue = line.split("=");
                if (keyValue.length != 2) {
                    continue;
                }
                String key = keyValue[0];
                String value = keyValue[1];
                if (value.equals("[]")) {
                    map.put(key, new LinkedList<>());
                } else if (value.startsWith("[") && value.endsWith("]")) {
                    String arrayString = value.substring(1, value.length() - 1);
                    String[] array = arrayString.split(", ");

                    LinkedList<String, String> list = new LinkedList<>();
                    for (int i = 0; i < array.length; i++) {
                        list.add(array[i]);
                    }
                    map.put(key, list);
                } else if (value.matches("^[-+]?\\d*\\.?\\d+$")) {
                    if (value.contains(".")) {
                        float floatValue = Float.parseFloat(value);
                        map.put(key, floatValue);
                    } else {
                        int intValue = Integer.parseInt(value);
                        map.put(key, intValue);
                    }
                } else {
                    map.put(key, value);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getItem(String key) {
        if(!map.containsKey(key)) loadFromFile();
        return map.get(key);
    }
    public static void setItem(String key, Object value) throws IOException {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            if (value.getClass().isArray()) {
                bw.append(key + "=" + "[");
                Object[] array = (Object[]) value;
                for (int i = 0; i < array.length; i++) {
                    bw.append(array[i].toString());
                    if (i < array.length - 1) {
                        bw.append(", ");
                    }
                }
                bw.append("]");
            } else {
                bw.append(key + "=" + value.toString());
            }
            bw.newLine();
            bw.close();
    }
    public static void removeKey(String key) throws IOException {
        if (keyExist(key)) {
            map.remove(key);
            writeToFile();
        }
    }

    public static String[] getAllKeys() {
        String[] keys = new String[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            keys[i] = key;
            i++;
        }
        return keys;
    }

    private static void writeToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value.getClass().isArray()) {
                bw.append(key + "=" + "[");
                Object[] array = (Object[]) value;
                for (int i = 0; i < array.length; i++) {
                    bw.append(array[i].toString());
                    if (i < array.length - 1) {
                        bw.append(", ");
                    }
                }
                bw.append("]");
            } else {
                bw.append(key + "=" + value.toString());
            }
            bw.newLine();
        }
        bw.close();
    }

    public static boolean keyExist(String key) {
        return map.containsKey(key);
    }
}

