import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstStr = br.readLine();
        String secondStr = br.readLine();

        int firstStringLength = firstStr.length();
        int secondStringLength = secondStr.length();
        int[][] dp = new int[firstStringLength + 1][secondStringLength + 1];

        for (int i = 1; i <= firstStringLength; i++) {
            for (int j = 1; j <= secondStringLength; j++) {
                // 원소가 같을 경우 이전 것에서 + 1
                if (firstStr.charAt(i - 1) == secondStr.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[firstStringLength][secondStringLength]);
    }
}
