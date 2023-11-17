import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int number = a * b * c;
        HashMap<Character, Integer> countNumber = new HashMap<>();

        for (int i = 0; i <= 9; i++) {
            countNumber.put((char) (i + '0'), 0);
        }

        for (char ch : String.valueOf(number).toCharArray()) {
            countNumber.put(ch, countNumber.get(ch) + 1);
        }

        for (int i = 0; i <= 9; i++) {
            System.out.println(countNumber.get((char) (i + '0')));
        }
    }
}
