import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 5];
        Arrays.fill(dp, -1);
        for (int j = 3; j <= n; j++) {
            if (j % 5 == 0) {
                dp[j] = j / 5;
            } else if (j % 5 == 1) {
                dp[j] = j / 5 + 1;
            } else if (j % 5 == 2) {
                if (j > 7) {
                    dp[j] = j / 5 + j % 5;
                }
            } else if (j % 5 == 3) {
                dp[j] = (j / 5) + 1;
            } else if (j % 5 == 4) {
                if (j > 4) {
                    dp[j] = (j / 5 - 1) + 3;
                }
            }

        }

        System.out.println(dp[n]);
    }
}
