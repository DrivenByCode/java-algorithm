import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int n, m;
    private static boolean[][] visited;
    private static int[] dx = {0, 1, 0, -1}; // 상우하좌
    private static int[] dy = {1, 0, -1, 0};
    private static int max = Integer.MIN_VALUE;

    private static void getMaxValueByBlocks(int x, int y, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(sum, max);
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (!visited[nx][ny]) {
                    // 'ㅜ'인 경우를 분류하기 위해
                    if (depth == 2) {
                        visited[nx][ny] = true;
                        getMaxValueByBlocks(x, y, depth + 1, sum + map[nx][ny]);
                        visited[nx][ny] = false;
                    }
                    visited[nx][ny] = true;
                    getMaxValueByBlocks(nx, ny, depth + 1, sum + map[nx][ny]);
                    visited[nx][ny] = false;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                getMaxValueByBlocks(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }
}