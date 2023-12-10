import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            int[] arr = new int[k + 1];
            st = new StringTokenizer(br.readLine());

            int[] sum = new int[k + 1];
            int[][] dp = new int[k + 1][k + 1];

            for (int j = 1; j <= k; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
                sum[j] += (sum[j - 1] + arr[j]);
            }

            for (int gap = 1; gap <= k; gap++) {
                for (int start = 1; start + gap <= k; start++) {
                    int end = start + gap;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + sum[end] - sum[start - 1]);
                    }
                }
            }

            System.out.println(dp[1][k]);
        }
    }
}