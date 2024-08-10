import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        final String star = "*";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                sb.append(" ".repeat(n - 1));
                sb.append(star);
                sb.append("\n");
            } else if (i == n - 1) {
                sb.append(star.repeat(i * 2 + 1)).append("\n");
            } else {
                sb.append(" ".repeat(n - i - 1)).append(star);
                sb.append(" ".repeat(2 * i - 1)).append(star).append("\n");
            }
        }

        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }
}
