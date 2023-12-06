import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] heights;
    private static int[][] dist;
    private static final int INF = (int) 1e3;

    private static void floydWarshall() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = heights[i][j];
                    if (dist[i][j] == 0) {
                        dist[i][j] = INF;
                    }
                }
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        heights = new int[n + 1][n + 1];
        dist = new int[n + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            heights[a][b] = 1;
        }

        floydWarshall();

        int cnt = 0;
        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                flag = false;
                if (dist[i][j] == INF) {
                    if (dist[j][i] == INF) {
                        flag = false;
                        break;
                    }
                    flag = true;
                } else {
                    flag = true;
                }
            }
            // i -> j 혹은 j -> i 거리를 i 기준 모두 알면 카운트 -> 본인의 등수를 알 수 있음.
            if (flag) cnt++;
        }

        System.out.println(cnt);
    }
}
