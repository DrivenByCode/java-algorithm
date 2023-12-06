import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String words = br.readLine();
        int n = Integer.parseInt(br.readLine());

        System.out.println(words.charAt(n - 1));
    }
}
