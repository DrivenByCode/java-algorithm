import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dist = new int[n - 1];
        int[] cost = new int[n - 1];
        int[] minCost = new int[n - 1]; // 현재까지 접근 가능한 주유소 중에 최저 비용을 찾음.
        int min = Integer.MAX_VALUE;

        long answer = 0;
        long totalDist = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
            totalDist += dist[i];
        }

        // 접근 가능한 주유소의 비용만 넣음 (n-1개)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            min = Math.min(cost[i], min);
            minCost[i] = min;
        }

        for (int i = 0; i < n - 1; i++) {
            if (cost[i] == minCost[i]) {
                int start = i;
                int saveMinCost = minCost[i];
                int phase = dist[i];
                // 현재 부터 이후에 최저비용의 주유소가 갱신되는 지점 직전까지를 찾고, 현재까지의 최저비용 주요소에서 그 거리(최저 주유비용이 갱신되는 직전)만큼 주유한다.
                while (start + 1 < n - 1) {
                    if (saveMinCost > minCost[++start]) {
                        i = start - 1;
                        break;
                    }
                    phase += dist[start];
                }

                answer += (long) phase * saveMinCost;
                totalDist -= phase;
                totalDist = totalDist > 0 ? totalDist : 0;
                if (totalDist == 0) break;
            } else {
                answer += (long) cost[i] * dist[i];
                totalDist -= dist[i];
            }
        }


        System.out.println(answer);
    }
}
