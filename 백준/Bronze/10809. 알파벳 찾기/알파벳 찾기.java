import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            sb.append(input.indexOf(alphabet)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
