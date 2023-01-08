To get items from localstorage to LinkedList

LinkedList<String> texts = (LinkedList<String>) LocalStorage.getItem("textos");

for (int i = 0; i < texts.size(); i++) {
    String item = texts.get(i);
}

To get int value from to int
int value = (int) LocalStorage.getItem("numero");
System.out.println(value);

To get string value from String

String value = (String) LocalStorage.getItem("valorAleatorio2");
