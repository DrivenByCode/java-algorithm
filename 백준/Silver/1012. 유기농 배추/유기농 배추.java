import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] farm;
    private static boolean[][] visited;
    private static final int[] dx = {0, 1, 0, -1}; // 위에서 부터 시계 방향
    private static final int[] dy = {1, 0, -1, 0}; // 위에서 부터 시계 방향
    private static int answer = 0;

    private static class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = currentPoint.x + dx[i];
                int ny = currentPoint.y + dy[i];
                if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if (!visited[nx][ny] && farm[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
        answer++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tcase = 0; tcase < t; tcase++) {
            st = new StringTokenizer(br.readLine());

            // 초기화
            answer = 0;
            m = Integer.parseInt(st.nextToken()); // 행 - 세로길이 (문제에선 오표기)
            n = Integer.parseInt(st.nextToken()); // 열 - 가로길이 (문제에선 오표기)
            int k = Integer.parseInt(st.nextToken()); // 배추의 갯수
            farm = new int[m][n];
            visited = new boolean[m][n];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                farm[x][y] = 1;
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    //                    System.out.print(farm[i][j] + " ");
                    if (farm[i][j] == 1) {
                        if (!visited[i][j]) {
                            visited[i][j] = true;
                            bfs(i, j);
                        }
                    }
                }
                //                System.out.println();
            }
            System.out.println(answer);
        }
    }
}
