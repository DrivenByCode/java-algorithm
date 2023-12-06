import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// dp[2] = 3
// dp[4] = dp[2] * dp[2] + 2;
// dp[6] = dp[4] * dp[2] + dp[2] * 2 + 2
// dp[n] = dp[n-4] * 3 + 2 * (dp[n-6] +...+ dp[n-8] + ... + dp[0]);
// https://blog.naver.com/ajy7424/222608567553

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        if (N % 2 == 0) {
            dp[0] = 1;
            dp[2] = 3;
            for (int i = 4; i <= N; i += 2) {
                dp[i] = dp[i - 2] * 3;
                for (int j = i - 4; j >= 0; j -= 2) {
                    dp[i] += dp[j] * 2;
                }
            }
        }

        System.out.println(dp[N]);
    }
}