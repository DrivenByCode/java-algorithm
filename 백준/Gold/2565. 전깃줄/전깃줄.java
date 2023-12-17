import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] poles = new int[501];
        int[] dp = new int[501];
        Arrays.fill(poles, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            poles[a] = b;
        }

        int max = 0;
        for (int i = 0; i < 501; i++) {
            // 전깃줄이 추가되지 않은 i번째 전봇대
            if (poles[i] != -1) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (poles[j] != -1 && poles[j] < poles[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }

        System.out.println(n - max);
    }
}

