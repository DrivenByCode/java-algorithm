import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n;
    private static boolean[][] visited;
    private static char[][] map;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    private static class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void dfs(Point p, boolean isBlind) {
        if (!visited[p.x][p.y]) {
            visited[p.x][p.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (!visited[nx][ny]) {
                        if (isBlind) {
                            if ((map[p.x][p.y] == 'R' || map[p.x][p.y] == 'G') && (map[nx][ny] == 'R' || map[nx][ny] == 'G')) {
                                dfs(new Point(nx, ny), isBlind);
                            } else if (map[p.x][p.y] == map[nx][ny]) {
                                dfs(new Point(nx, ny), isBlind);

                            }
                        } else {
                            if (map[p.x][p.y] == map[nx][ny]) {
                                dfs(new Point(nx, ny), isBlind);
                            }
                        }
                    }
                }
            }
        }
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
                    dfs(new Point(i, j), false);
                    normalCount++;
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
                    dfs(new Point(i, j), true);
                    blindCount++;
                }
            }
        }

        System.out.println(normalCount + " " + blindCount);
    }
}