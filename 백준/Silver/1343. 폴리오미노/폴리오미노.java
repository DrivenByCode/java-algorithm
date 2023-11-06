import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();


        String one = "AAAA";
        String two = "BB";

        input = input.replace("XXXX", "AAAA");
        input = input.replace("XX", "BB");

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(input);
    }
}
