import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int m, n;
    private static int[][] map;
    private static int[] dx = {0, 1, 0, -1}; // 위부터 시계방향
    private static int[] dy = {1, 0, -1, 0}; // 위부터 시계방향
    private static int[][] dist;

    private static class Point implements Comparable<Point> {
        private final int x;
        private final int y;
        private final int height;

        private Point(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Point other) {
            return other.height - this.height;
        }
    }

    private static int bfs() {
        // 내리막길로만 이동하는 경로를 효율적으로 찾기위해 pq 사용
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Point(0, 0, map[0][0]));

        dist[0][0] = 1;

        while (!priorityQueue.isEmpty()) {
            Point current = priorityQueue.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if (map[x][y] > map[nx][ny]) {
                        // 기존에 방문안한 경로는 우선순위 큐에 넣음
                        if (dist[nx][ny] == 0) {
                            priorityQueue.offer(new Point(nx, ny, map[nx][ny]));
                        }

                        // 다음에 갈 경로에 현재 경로수를 더해서 업데이트 -> 각 지점에 도달하는 데 사용된 경로의 수
                        dist[nx][ny] += dist[x][y];
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }
}
