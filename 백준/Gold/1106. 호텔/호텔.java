import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int targetCustomers = Integer.parseInt(st.nextToken());
        int numCities = Integer.parseInt(st.nextToken());

        int[] dp = new int[targetCustomers + 101];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < numCities; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customers = Integer.parseInt(st.nextToken());

            for (int j = customers; j <= targetCustomers + 100; j++) {
                if (dp[j - customers] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - customers] + cost);
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = targetCustomers; i <= targetCustomers + 100; i++) {
            minCost = Math.min(minCost, dp[i]);
        }

        System.out.println(minCost);
    }
}
