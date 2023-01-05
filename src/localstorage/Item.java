package localstorage;

public class Item<T> {
    private final String key;
    private final T value;

    public Item(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
