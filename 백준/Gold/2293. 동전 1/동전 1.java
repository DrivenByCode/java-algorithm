import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        dp[0] = 1; // 합이 k원일 경우 k원으로 만들 수 있는 경우의 수

        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                // dp[j] 이전의 동전을 기준으로 누적시킨 경우의 수
                // dp[j - coins[i]] 현재 동전(i)을 사용하여 만들 수 있는 경우의 수
                dp[j] += dp[j - coins[i]];
            }
        }

        System.out.println(dp[k]);
    }
}
