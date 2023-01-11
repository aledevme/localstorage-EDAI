To get items from localstorage to LinkedList and process object data

    LinkedList<String, String> booksData = (LinkedList<String, String>) LocalStorage.getItem("keyvalue");

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

To get int value from to int
    
    int value = (int) LocalStorage.getItem("keyvalue");
    System.out.println(value);

To get string value from String

    String value = (String) LocalStorage.getItem("keyvalue");

To list all keys

    String[] keys = LocalStorage.getAllKeys();

    for (int i = 0; i < keys.length; i++) {
        System.out.println(keys[i]);
    }