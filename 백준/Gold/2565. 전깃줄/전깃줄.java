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
        
        // 전깃줄의 연결 종료 위치를 나타냄. A전봇대 특정 위치에서 부터 연결되어있는 B전봇대 위치
        int[] poles = new int[501];

        // A전봇대의 특정 위치까지 LIS갯수
        int[] dp = new int[501];
        Arrays.fill(poles, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            poles[a] = b;
        }

        // 증가하는 순서로 연결되어있는 전깃줄의 갯수
        int max = 0;
        for (int i = 0; i < 501; i++) {
            // 전깃줄이 추가되지 않은 i번째 A 전봇대
            if (poles[i] != -1) {
                dp[i] = 1;
                // i번째 전봇대 즉, 현재 고려하고 있는 전봇대 보다 이전에 위치한 전봇대 j
                for (int j = 0; j < i; j++) {
                    // poles[j] < poles[i] : 증가하는 순서로 전기줄이 연결되어 있는 경우
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

