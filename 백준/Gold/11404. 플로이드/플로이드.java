import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
Input

3
2
1 2 100000
2 3 100000

Answer
0 100000 200000
0 0 100000
0 0 0
*/
public class Main {
    private static int n;
    private static int[][] map;
    private static int[][] dist;
    // 문제의 비용은 10만 보다 작거나 같은 수라고 했는데, 이는 a에서 b의 비용일 뿐, 다른 도시를 거치면 더 커질 수 있다.
    // (int) 1e5 + 1로 하면 안됨.
    // 반례는 위 참고.
    private final static int INF = (int) 1e8 + 1;

    private static void floydWarshall() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 대각선 즉, 자기자신 경로는 다 비용 0 처리
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = map[i][j];
                    // 해당 경로의 비용이 존재하지 않으면 무한대 처리
                    // 다음 로직에서 거쳐가는 경우를 고려하여 비용 업데이트
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
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        dist = new int[n + 1][n + 1];

        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            // 비용이 아직 입력되지 않았거나 입력되어 있는 비용 값이 더 크다면 작은 값으로 업데이트
            if (map[x][y] == 0 || map[x][y] > distance) {
                map[x][y] = distance;
            }
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] != INF) sb.append(dist[i][j] + " ");
                else {
                    // 거리가 무한대인 경우 0 처리
                    sb.append(0 + " ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}