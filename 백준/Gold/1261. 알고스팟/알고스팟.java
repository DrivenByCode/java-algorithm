import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 13549와 같음
// https://www.acmicpc.net/problem/13549
public class Main {
    private static int m, n; // 행(세로길이), 열(가로길이)
    private static int[][] maze;
    private static final int dx[] = {0, 1, 0, -1}; // 상 우 하 좌
    private static final int dy[] = {1, 0, -1, 0}; // 상 우 하 좌
    private static int[][] dist;

    private static class Point implements Comparable<Point> {
        private final int x;
        private final int y;
        private final int breakCount; // 지금까지 부신 벽 개수

        private Point(int x, int y, int breakCount) {
            this.x = x;
            this.y = y;
            this.breakCount = breakCount;
        }

        @Override
        public int compareTo(Point other) {
            return this.breakCount - other.breakCount;
        }
    }

    private static int dijkstra() {
        // 우선 순위 큐로 제일 벽을 적게 부신 경우를 맨 앞으로 정렬
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(new Point(1, 1, 0));

        dist = new int[n + 1][m + 1];

        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        dist[1][1] = 0;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            int breakCount = curr.breakCount;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (1 <= nx && nx <= n && 1 <= ny && ny <= m) {
                    if (breakCount + maze[nx][ny] < dist[nx][ny]) {
                        dist[nx][ny] = dist[x][y] + maze[nx][ny];
                        queue.offer(new Point(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }
        return dist[n][m];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 가로 크기 (열)
        n = Integer.parseInt(st.nextToken()); // 세로 크기 (행)

        maze = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                maze[i][j] = input.charAt(j - 1) - '0';
            }
        }

        System.out.println(dijkstra());
    }
}
