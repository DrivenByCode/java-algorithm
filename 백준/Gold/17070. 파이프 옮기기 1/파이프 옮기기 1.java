import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] home;
    private static int answer = 0;

    private static boolean isInHome(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void dfs(int x, int y, int type) {
        if (x == n - 1 && y == n - 1) {
            answer++;
            return;
        }

        if (type == 0 || type == 2) { // 가로 또는 대각선
            int nx = x;
            int ny = y + 1;
            if (isInHome(nx, ny) && home[nx][ny] != 1) {
                dfs(nx, ny, 0); // 가로로 이동, 모양(type)도 가로
            }
        }

        if (type == 1 || type == 2) { // 세로 또는 대각선
            int nx = x + 1;
            int ny = y;
            if (isInHome(nx, ny) && home[nx][ny] != 1) {
                dfs(nx, ny, 1); // 세로로 이동, 모양(type)도 세로
            }
        }

        if (type == 0 || type == 1 || type == 2) { // 가로, 세로, 대각선
            int nx = x + 1;
            int ny = y + 1;
            if (isInHome(nx, ny) && home[nx][ny] != 1 && home[nx][ny - 1] != 1 && home[nx - 1][ny] != 1) {
                dfs(nx, ny, 2); // 대각선으로 이동, 모양(type)도 대각선
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        home = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0); // 초기 파이프의 상태는 가로
        System.out.println(answer);
    }
}