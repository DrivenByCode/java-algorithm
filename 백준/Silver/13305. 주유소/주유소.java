import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dist = new int[n - 1];
        int[] cost = new int[n - 1];

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
        }

        // 접근 가능한 주유소 중에 최저 비용을 찾음.
        int minCost = Arrays.stream(cost).min().orElse(0);

        for (int i = 0; i < n; i++) {
            if (cost[i] == minCost) {
                answer += totalDist * cost[i];
                totalDist = 0;
                break;
            } else {
                answer += cost[i] * dist[i];
                totalDist -= dist[i];
            }
        }


        System.out.println(answer);
    }
}
