import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 1, 0, -1}; // 상 우 하 좌
    private static int[] dy = {1, 0, -1, 0}; // 상 우 하 좌

    private static class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 빙하 개수 계산
    private static void countIcebergs(Point now) {
        Queue<Point> q = new LinkedList<>();
        q.add(now);
        visited[now.x][now.y] = true;

        while (!q.isEmpty()) {
            Point currentNode = q.poll();
            int x = currentNode.x;
            int y = currentNode.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    // 다음에 갈 좌표에 빙산이 있고 방문하지 않은 곳이라면
                    if (map[nx][ny] > 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    // 빙하 녹이기
    private static void meltIcebergs() {
        int[][] temp = new int[n][m];

        // 큐에 넣을 필요없이 모든 위치를 다 돌면됨.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 현재 위치에 빙하가 있다면
                if (map[i][j] > 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                            if (map[nx][ny] == 0) {
                                temp[i][j]++;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 음수라면 0이 되고, 양수면 map[i][j] - temp[i][j] 값이 됨
                map[i][j] = Math.max(map[i][j] - temp[i][j], 0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            // 방문 배열 초기화
            visited = new boolean[n][m];
            int count = 0;

            // 빙하 개수 카운트 하기.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        countIcebergs(new Point(i, j));
                        count++;
                    }
                }
            }

            if (count == 0) {
                System.out.println(0);
                break;
            } else if (count >= 2) {
                System.out.println(time);
                break;
            }

            meltIcebergs();
            time++;
        }
    }
}
