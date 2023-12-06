import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int v;
    private static int[][] map;
    private static int[][] dist;
    private static final int INF = 400 * (int) 1e4 + 1;
    private static int min = Integer.MAX_VALUE;

    private static void floydWarshall() {
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                dist[i][j] = map[i][j];
                if (dist[i][j] == 0) {
                    dist[i][j] = INF;
                }
            }
        }

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                        // i == j는 사이클을 의미, dist[i][j] < min은 현재까지 발견된 사이클 중에서 최소 길이의 사이클을 찾는 것
                        if (i == j && dist[i][j] < min) {
                            min = dist[i][j];
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        map = new int[v + 1][v + 1];
        dist = new int[v + 1][v + 1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = c;
        }

        floydWarshall();

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}
