import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int n;
    private static boolean[][] visited;
    private static char[][] map;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static Queue<Point> q = new LinkedList<>();

    private static class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int bfs(Point p, boolean isBlind) {
        q.clear();
        q.add(p);
        visited[p.x][p.y] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (!visited[nx][ny]) {
                        if (isBlind) {
                            if ((map[x][y] == 'R' || map[x][y] == 'G') && (map[nx][ny] == 'R' || map[nx][ny] == 'G')) {
                                q.add(new Point(nx, ny));
                                visited[nx][ny] = true;
                            } else if (map[x][y] == map[nx][ny]) {
                                q.add(new Point(nx, ny));
                                visited[nx][ny] = true;
                            }
                        } else {
                            if (map[x][y] == map[nx][ny]) {
                                q.add(new Point(nx, ny));
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int normalCount = 0;
        int blindCount = 0;

        // 정상인 사람들
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    normalCount += bfs(new Point(i, j), false);
                }
            }
        }

        // 방문 배열 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

        // 적록색약
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    blindCount += bfs(new Point(i, j), true);
                }
            }
        }

        System.out.println(normalCount + " " + blindCount);
    }
}
