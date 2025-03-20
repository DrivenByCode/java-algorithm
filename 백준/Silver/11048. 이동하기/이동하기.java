import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] candy = new int[n][m];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                candy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = candy[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (0 < i) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + candy[i][j]); // 위에서 오는 경우
                if (0 < j) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + candy[i][j]); // 왼쪽에서 오는 경우
                if (0 < i && 0 < j) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + candy[i][j]); // 대각선에서 오는 경우
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
