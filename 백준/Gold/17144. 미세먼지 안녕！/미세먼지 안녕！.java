import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int r, c, t;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int airCleanerTop, airCleanerBottom;

    private static class Coord {
        private final int x;
        private final int y;

        private Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void spreadDust() {
        int[][] temp = new int[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = map[i][j] / 5;
                    int spreadCount = 0;

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 1 && nx <= r && ny >= 1 && ny <= c && map[nx][ny] != -1) {
                            temp[nx][ny] += spreadAmount;
                            spreadCount++;
                        }
                    }
                    map[i][j] -= spreadAmount * spreadCount;
                }
            }
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    private static void activateAirCleaner() {
        // Upper part

        // 아래로 이동
        for (int i = airCleanerTop - 1; i > 1; i--) {
            map[i][1] = map[i - 1][1];
        }

        // 좌측으로 이동
        for (int i = 1; i < c; i++) {
            map[1][i] = map[1][i + 1];
        }

        // 위로 이동
        for (int i = 1; i < airCleanerTop; i++) {
            map[i][c] = map[i + 1][c];
        }

        // 우측으로 이동
        for (int i = c; i > 2; i--) {
            map[airCleanerTop][i] = map[airCleanerTop][i - 1];
        }

        // 공기청정기 위쪽 바로 우측 0
        map[airCleanerTop][2] = 0;

        // Lower part

        // 위로 이동
        for (int i = airCleanerBottom + 1; i < r; i++) {
            map[i][1] = map[i + 1][1];
        }

        // 좌측으로 이동
        for (int i = 1; i < c; i++) {
            map[r][i] = map[r][i + 1];
        }

        // 아래로 이동
        for (int i = r; i > airCleanerBottom; i--) {
            map[i][c] = map[i - 1][c];
        }

        // 우측으로 이동
        for (int i = c; i > 2; i--) {
            map[airCleanerBottom][i] = map[airCleanerBottom][i - 1];
        }

        // 공기청정기 아래쪽 바로 우측 0
        map[airCleanerBottom][2] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (airCleanerTop == 0) {
                        airCleanerTop = i;
                    } else {
                        airCleanerBottom = i;
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {
            spreadDust();
            activateAirCleaner();
        }

        int sum = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] != -1) {
                    sum += map[i][j];
                }
            }
        }

        System.out.println(sum);
    }
}
