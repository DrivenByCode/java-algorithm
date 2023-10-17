import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] lab;
    private static final int[] dx = {0, 1, 0, -1}; // 상 우 하 좌
    private static final int[] dy = {1, 0, -1, 0}; // 상 우 하 좌
    private static boolean[][] checked; // 벽을 세우거나 바이러스가 퍼짐을 나타냄.
    private static int max = Integer.MIN_VALUE;

    private static boolean isInLab(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static void buildWall(int cnt) {
        // 3번 째 벽을 지으면 바이러스를 퍼트림
        if (cnt == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    buildWall(cnt + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    private static void spreadVirus() {
        checked = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 현재 위치에 바이러스가 있으면 dfs로 바이러스를 퍼트림
                if (lab[i][j] == 2) {
                    dfs(i, j, checked);
                }
            }
        }
        // 최댓값 갱신
        max = Math.max(max, countMaxSafeArea(checked));
    }

    private static void dfs(int x, int y, boolean[][] checked) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isInLab(nx, ny)) {
                // 다음칸이 빈칸이고, 벽을 세우거나 바이러스가 퍼진 곳이 아니면 아니라면 바이러스를 퍼트림
                if (lab[nx][ny] == 0 && !checked[nx][ny]) {
                    checked[nx][ny] = true;
                    dfs(nx, ny, checked);
                }
            }
        }
    }

    private static int countMaxSafeArea(boolean[][] checked) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!checked[i][j] && lab[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로의 길이 - 행
        m = Integer.parseInt(st.nextToken()); // 가로의 길이 - 열

        lab = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        buildWall(0);

        System.out.println(max);
    }
}
