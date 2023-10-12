import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준_합 분해 (중복조합_DP)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][k + 1];


        for (int i = 0; i <= n; i++) {
            dp[i][1] = 1; // j = 1 일 때, 경우의 수는 항상 1.
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                for (int l = 0; l <= i; l++) {
                    dp[i][j] += dp[l][j - 1];
                    dp[i][j] %= (int) 1e9; // 바로 나머지 구함
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}