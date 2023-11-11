import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().toUpperCase();

        HashMap<Character, Integer> alphabetCount = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            alphabetCount.put(c, alphabetCount.getOrDefault(c, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        int count = 0;
        char key = 0;

        for (Map.Entry<Character, Integer> entry : alphabetCount.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                count = 0;
                key = entry.getKey();
            } else if (max == entry.getValue()) {
                count++;
            }
        }

        if (count > 0) {
            System.out.println("?");
            return;
        }
        System.out.println(key);
    }
}
