import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1890 점프
// 시간 복잡도 : n^2
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        long[][] dp = new long[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1; // 출발점은 1개의 경로가 있음

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) break; // 마지막 칸에서는 더 이상 점프할 수 없음

                int jump = arr[i][j]; // 현재 칸에서 점프할 크기
                if (jump == 0) continue; // 점프 크기가 0이면 다음 칸으로 이동할 수 없음

                if (i + jump < n) { // 아래쪽으로 이동 가능한 경우
                    dp[i + jump][j] += dp[i][j];
                }

                if (j + jump < n) { // 오른쪽으로 이동 가능한 경우
                    dp[i][j + jump] += dp[i][j];
                }
            }
        }

        System.out.println(dp[n - 1][n - 1]); // 마지막 칸에 도달하는 경로의 수
    }
}
