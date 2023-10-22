import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int c, r;
    private static char[][] map;
    private static int[] dx = {0, 1, 0, -1}; // 위에서 부터 시계 방향
    private static int[] dy = {1, 0, -1, 0}; // 위에서 부터 시계 방향
    private static boolean[][] visited;
    private static int max = Integer.MIN_VALUE;

    private static class Point {
        private int x;
        private int y;
        private int dist;

        private Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            for (int size = 0; size < queue.size(); size++) {
                Point now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                        // 육지로만 갈 수 있고, 갔던 데 또 못감.
                        if (map[nx][ny] == 'L' && !visited[nx][ny]) {
                            queue.offer(new Point(nx, ny, now.dist + 1));
                            visited[nx][ny] = true;
                            max = Math.max(max, now.dist + 1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()); // 세로의 크기 (행)
        c = Integer.parseInt(st.nextToken()); // 가로의 크기 (열)

        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[r][c];
                    visited[i][j] = true;
                    // i,j 부터 시작하여 육지를 발견하면 가장 먼 위치의 거리 갱신
                    bfs(new Point(i, j, 0));
                }
            }
        }

        System.out.println(max);
    }
}
