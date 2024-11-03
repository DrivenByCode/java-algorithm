import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        // 갈 수 없는 돌 목록
        boolean[] isNotVisitable = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            int blockedStone = Integer.parseInt(br.readLine());
            isNotVisitable[blockedStone] = true;
        }

        // 모든 경우의 수를 저장할 dp 배열 (각 위치마다 점프 길이를 HashMap으로 저장)
        Map<Integer, Integer>[] dp = new HashMap[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = new HashMap<>();
        }

        // 첫 번째 돌에서 시작 (돌 번호 1, 점프 길이 0, 점프 횟수 0)
        dp[1].put(0, 0);

        // DP 연산
        for (int i = 1; i <= n; i++) {
            // i번째 돌에 대해 가능한 점프 길이 반복
            for (Map.Entry<Integer, Integer> entry : dp[i].entrySet()) {
                int x = entry.getKey();      // 현재 점프 길이
                int cnt = entry.getValue();  // 현재까지의 점프 횟수

                // x-1, x, x+1 이동
                for (int j = -1; j <= 1; j++) {
                    int nextJump = x + j; // 다음 점프 길이
                    int nextPos = i + nextJump; // 다음 위치

                    // 점프가 유효한지 확인: 점프 길이 1 이상, 범위 내, 갈 수 없는 돌이 아님
                    if (0 < nextJump && nextPos <= n && !isNotVisitable[nextPos]) {
                        // 해당 점프 길이가 이미 존재하면 최소 점프 횟수 갱신
                        if (dp[nextPos].containsKey(nextJump)) {
                            dp[nextPos].put(nextJump, Math.min(dp[nextPos].get(nextJump), cnt + 1));
                        } else { // 새로운 점프 길이인 경우 추가
                            dp[nextPos].put(nextJump, cnt + 1);
                        }
                    }
                }
            }
        }

        // 마지막 돌까지 갈 수 있는 최소 점프 횟수 찾기
        if (dp[n].isEmpty()) {
            System.out.println(-1);
        } else {
            int minJumps = Integer.MAX_VALUE;
            for (int jumps : dp[n].values()) {
                minJumps = Math.min(minJumps, jumps);
            }
            System.out.println(minJumps);
        }
    }
}
