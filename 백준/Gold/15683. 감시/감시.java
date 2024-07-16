import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int n, m;
    private static int minBlindSpots = Integer.MAX_VALUE;
    private static ArrayList<Point> cctvs = new ArrayList<>();

    private static final int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서
    private static final int[] dy = {0, 1, 0, -1};

    private static class Point {
        private final int x;
        private final int y;

        private Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isInBounds(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static void fill(int x, int y, int direction, int[][] map, ArrayList<Point> covered) {
        int nx = x;
        int ny = y;
        while (true) {
            nx += dx[direction];
            ny += dy[direction];
            if (!isInBounds(nx, ny) || map[nx][ny] == 6) break;
            if (map[nx][ny] == 0) {
                map[nx][ny] = -1; // 감시할 수 있는 영역을 -1로 표시
                covered.add(new Point(nx, ny));
            }
        }
    }

    private static void unfill(int[][] tempMap, ArrayList<Point> covered) {
        for (Point p : covered) {
            tempMap[p.x][p.y] = 0;
        }
    }

    private static void dfs(int depth) {
        if (depth == cctvs.size()) {
            int blindSpots = countBlindSpots(map);
            minBlindSpots = Math.min(minBlindSpots, blindSpots);
            return;
        }

        Point cctv = cctvs.get(depth);
        int type = map[cctv.x][cctv.y];

        for (int i = 0; i < 4; i++) {
            ArrayList<Point> covered = new ArrayList<>();
            switch (type) {
                case 1:
                    fill(cctv.x, cctv.y, i, map, covered);
                    dfs(depth + 1);
                    unfill(map, covered);
                    break;
                case 2:
                    fill(cctv.x, cctv.y, i, map, covered);
                    fill(cctv.x, cctv.y, (i + 2) % 4, map, covered);
                    dfs(depth + 1);
                    unfill(map, covered);
                    break;
                case 3:
                    fill(cctv.x, cctv.y, i, map, covered);
                    fill(cctv.x, cctv.y, (i + 1) % 4, map, covered);
                    dfs(depth + 1);
                    unfill(map, covered);
                    break;
                case 4:
                    fill(cctv.x, cctv.y, i, map, covered);
                    fill(cctv.x, cctv.y, (i + 1) % 4, map, covered);
                    fill(cctv.x, cctv.y, (i + 2) % 4, map, covered);
                    dfs(depth + 1);
                    unfill(map, covered);
                    break;
                case 5:
                    fill(cctv.x, cctv.y, 0, map, covered);
                    fill(cctv.x, cctv.y, 1, map, covered);
                    fill(cctv.x, cctv.y, 2, map, covered);
                    fill(cctv.x, cctv.y, 3, map, covered);
                    dfs(depth + 1);
                    unfill(map, covered);
                    break;
            }
        }
    }

    private static int countBlindSpots(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) count++;
            }
        }
        return count;
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
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new Point(i, j));
                }
            }
        }

        dfs(0);

        System.out.println(minBlindSpots);
    }
}
