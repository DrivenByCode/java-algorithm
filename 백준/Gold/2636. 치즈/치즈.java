import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {0, 1, 0, -1}; // 상 우 하 좌
    private static int[] dy = {1, 0, -1, 0};
    private static int[][] adjMatrix;
    private static boolean[][] visited;
    private static int r, c;

    private static class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Point currentNode = q.poll();
            int x = currentNode.x;
            int y = currentNode.y;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (0 <= nx && nx < r && 0 <= ny && ny < c && !visited[nx][ny] && adjMatrix[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                    if (adjMatrix[nx][ny] == 0) {
                        adjMatrix[nx][ny] = -1;  // 외부 공기를 -1로 표시
                    }
                }
            }
        }
    }

    private static int meltCheese() {
        int melted = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (adjMatrix[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (0 <= nx && nx < r && 0 <= ny && ny < c && adjMatrix[nx][ny] == -1) {
                            adjMatrix[i][j] = 0;  // 외부 공기와 접촉한 치즈를 녹임
                            melted++;
                            break;
                        }
                    }
                }
            }
        }
        return melted;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        adjMatrix = new int[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int cheese = Integer.parseInt(st.nextToken());
                adjMatrix[i][j] = cheese;
            }
        }

        int time = 0; // 치즈가 모두 녹는데 걸리는 시간
        int lastMelted = 0; // 마지막으로 녹은 치즈의 개수
        while (true) {
            visited = new boolean[r][c];
            bfs(); // bfs를 통해 외부 공기를 표시
            int melted = meltCheese(); // 외부 공기와 접촉한 치즈를 녹임
            if (melted == 0) {
                break;
            }
            lastMelted = melted;
            time++;
        }
        System.out.println(time);
        System.out.println(lastMelted);
    }
}
