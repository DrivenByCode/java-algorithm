import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {0, 1, 0, -1}; // 상 우 좌 하
    private static int[] dy = {1, 0, -1, 0};
    private static int[][] map;
    private static int n, m;
    private static boolean[][][] visited;

    private static class Point {
        private final int x;
        private final int y;
        private final int distance;
        private final int breakCount; // 벽을 깬 횟수, 최대 한번만 깰 수 있음

        private Point(int x, int y, int distance, int breakCount) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.breakCount = breakCount;
        }
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        // 시작하는 칸도 포함
        queue.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Point currentNode = queue.poll();
            int x = currentNode.x;
            int y = currentNode.y;
            if (x == n - 1 && y == m - 1) {
                // 끝나는 칸도 포함
                return currentNode.distance;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (map[nx][ny] == 0 && !visited[nx][ny][currentNode.breakCount]) {
                        visited[nx][ny][currentNode.breakCount] = true;
                        queue.offer(new Point(nx, ny, currentNode.distance + 1, currentNode.breakCount));
                    }
                    if (map[nx][ny] == 1 && !visited[nx][ny][1]) {
                        if (currentNode.breakCount == 0) {
                            visited[nx][ny][1] = true;
                            queue.offer(new Point(nx, ny, currentNode.distance + 1, 1));
                        }
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2]; // 만약 visited[nx][ny][1]이 true면 부신 적 있는 것.

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());

    }
}
