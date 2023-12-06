import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = input.length();

        int[][] dp = new int[n][2];

        char tmp = input.charAt(0);
        if (Character.isLowerCase(tmp)) {
            // 문자입력
            dp[0][0] = 1;
            // 문자입력 + 마름모입력
            dp[0][1] = 2;
        } else {
            // 문자입력 + 별 입력
            dp[0][0] = 2;
            // 마름모 입력 + 문자입력
            dp[0][1] = 2;
        }

        for (int i = 1; i < n; i++) {
            char now = input.charAt(i);
            if (Character.isLowerCase(now)) {
                dp[i][0] = Math.min(dp[i - 1][0] + 1, dp[i - 1][1] + 2);

                // dp[i - 1][0] + 2 -> 문자입력 + 마름모 입력
                // dp[i - 1][1] + 2 ->  문자입력 + 별입력
                dp[i][1] = Math.min(dp[i - 1][0] + 2, dp[i - 1][1] + 2);
            } else {
                // dp[i - 1][0] + 2 -> 문자입력 + 별 입력
                // dp[i - 1][1] + 2 -> 문자입력 + 마름모 입력
                dp[i][0] = Math.min(dp[i - 1][0] + 2, dp[i - 1][1] + 2);


                dp[i][1] = Math.min(dp[i - 1][0] + 2, dp[i - 1][1] + 1);
            }
        }

        System.out.println(Math.min(dp[n - 1][0], dp[n - 1][1]));
    }
}
