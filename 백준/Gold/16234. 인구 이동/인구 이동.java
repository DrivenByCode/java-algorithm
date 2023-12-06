import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] map;
    private static int n, l, r;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};
    private static boolean[][] visited;
    private static int days = 0;

    private static boolean canOpenBorders(int now, int next) {
        return l <= Math.abs(now - next) && Math.abs(now - next) <= r;
    }

    private static boolean isWithinBounds(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> unionCountries = new ArrayList<>();
        queue.add(point);

        int totalPopulation = 0;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            unionCountries.add(current);
            totalPopulation += map[current.x][current.y];
            for (int k = 0; k < 4; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];
                if (isWithinBounds(nx, ny) && canOpenBorders(map[current.x][current.y], map[nx][ny]) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        // 나라 개수가 하나이하면 연합을 이루지 못해 국경선을 열 수가 없음.
        if (unionCountries.size() <= 1) return false;

        int averagePopulation = totalPopulation / unionCountries.size();
        for (Point country : unionCountries) {
            map[country.x][country.y] = averagePopulation;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 인구 이동이 한번이라도 일어났는지 체크하는 flag
            boolean isPopulationShifted = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        if (bfs(new Point(i, j))) isPopulationShifted = true;
                    }
                }
            }

            if (!isPopulationShifted) break;

            days++;

            // 방문 배열 초기화 후, 다시 인구 이동
            for (boolean[] arr : visited) {
                Arrays.fill(arr, false);
            }
        }

        System.out.println(days);
    }
}

