import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int r, c;
    private static char[][] board;
    private static int maxLength = Integer.MIN_VALUE;
    private static final int[] dx = {0, 1, 0, -1}; // 상, 우, 하, 좌
    private static final int[] dy = {1, 0, -1, 0}; // 상, 우, 하, 좌
    private static int[] visitedAlphabets = new int[26];

    private static boolean isInsideBoard(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

    private static void dfs(int x, int y, int pathLength) {
        if (visitedAlphabets[board[x][y] - 'A'] > 1) {
            return;
        }

        maxLength = Math.max(maxLength, pathLength);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                if (visitedAlphabets[board[nx][ny] - 'A'] == 0) {
                    visitedAlphabets[board[nx][ny] - 'A']++;
                    dfs(nx, ny, pathLength + 1);
                    visitedAlphabets[board[nx][ny] - 'A']--;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visitedAlphabets[board[0][0] - 'A'] = 1;
        dfs(0, 0, 1);

        System.out.println(maxLength);
    }
}
