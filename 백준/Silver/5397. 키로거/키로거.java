import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String input = br.readLine();
            if (input == null || input.isEmpty()) break;

            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iterator = list.listIterator();

            for (char c : input.toCharArray()) {
                if (c == '<') {
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                    }
                } else if (c == '>') {
                    if (iterator.hasNext()) {
                        iterator.next();
                    }
                } else if (c == '-') {
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();
                    }
                } else {
                    iterator.add(c);
                }
            }

            StringBuilder result = new StringBuilder();
            for (char ch : list) {
                result.append(ch);
            }

            System.out.println(result);
        }
    }
}
