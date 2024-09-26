import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        TreeSet<String> books = new TreeSet<>();
        TreeMap<String, Integer> countBooks = new TreeMap();

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            books.add(name);
            countBooks.put(name, countBooks.getOrDefault(name, 0) + 1);
        }

        int max = 0;
        
        String name = "";

        for (String book : books) {

            int count = countBooks.get(book);
            if (max < count) {
                max = count;
                name = book;
            }
        }

        System.out.println(name);
    }
}