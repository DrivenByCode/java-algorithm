import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][][] map;
    private static final int[] dx = {0, 0, 0, 1, 0, -1}; // 아래 위 상 우 하 좌
    private static final int[] dy = {0, 0, 1, 0, -1, 0}; // 아래 위 상 우 하 좌
    private static final int[] dz = {-1, 1, 0, 0, 0, 0}; // 아래 위 상 우 하 좌
    private static int n, m, h; // 세로(행), 가로(열), 높이
    private static Queue<Point> queue = new LinkedList<>();

    private static class Point {
        private final int x;
        private final int y;
        private final int z;

        private Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static int bfs() {
        int days = -1;  // 초기값을 -1로 설정하여 첫 번째 루프에서 0으로 만듭니다.

        while (!queue.isEmpty()) {
            int qSize = queue.size();

            for (int size = 0; size < qSize; size++) {
                Point currentNode = queue.poll();
                int px = currentNode.x;
                int py = currentNode.y;
                int pz = currentNode.z;

                for (int i = 0; i < 6; i++) {
                    int nx = px + dx[i];
                    int ny = py + dy[i];
                    int nz = pz + dz[i];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m && 0 <= nz && nz < h) {
                        if (map[nx][ny][nz] == 0) {
                            map[nx][ny][nz] = 1;
                            queue.add(new Point(nx, ny, nz));
                        }
                    }
                }
            }

            days++;  // 각 레벨(일)마다 증가합니다.
        }

        // 여기서는 모든 0이 1로 바뀌었는지 검사합니다.
        // 만약 아직도 0이 있다면 -1을 반환하며, 그렇지 않으면 days를 반환합니다.
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j][k] == 0) {
                        return -1;
                    }
                }
            }
        }

        return days;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 가로(열)
        n = Integer.parseInt(st.nextToken()); // 세로(행)
        h = Integer.parseInt(st.nextToken()); // 높이

        map = new int[n][m][h];

        // 층수
        for (int k = 0; k < h; k++) {
            // 행(세로)
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                // 열(가로)
                for (int j = 0; j < m; j++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 층수
        for (int k = 0; k < h; k++) {
            // 가로
            for (int i = 0; i < n; i++) {
                // 세로
                for (int j = 0; j < m; j++) {
                    if (map[i][j][k] == 1) {
                        queue.add(new Point(i, j, k));
                    }
                }
            }
        }

        int answer = bfs();

        System.out.println(answer);
    }
}