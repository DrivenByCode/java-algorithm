import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int m, n;
    private static int[][] farm;
    private static int[] dx = {0, 1, 0, -1}; // 위에서 부터 시계방향
    private static int[] dy = {1, 0, -1, 0}; // 위에서 부터 시계방향
    private static Queue<Point> queue = new LinkedList<>();

    private static class Point {
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int ripenTomato() {
        int days = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point now = queue.poll();
                int x = now.x;
                int y = now.y;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                        if (farm[nx][ny] == 0) {
                            farm[nx][ny] = 1;
                            queue.offer(new Point(nx, ny));
                        }
                    }
                }
            }
            days++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (farm[i][j] == 0) {
                    return -1;
                }
            }
        }

        return days - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 가로 칸수 : 열
        n = Integer.parseInt(st.nextToken()); // 세로 칸수 : 행

        farm = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                farm[i][j] = Integer.parseInt(st.nextToken());
                if (farm[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        System.out.println(ripenTomato());
    }
}
