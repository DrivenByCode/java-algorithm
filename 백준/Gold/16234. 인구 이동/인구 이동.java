import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int n, l, r;
    private static int[] dx = {0, 1, 0, -1}; // 위부터 시계방향
    private static int[] dy = {1, 0, -1, 0}; // 위부터 시계방향
    private static boolean[][] visited;
    private static boolean[][] populationShiftedArray;
    private static int days = 0;

    // 1. 국경선 열렸는지 여부는 bfs로 확인 후, 방문과 인구가 이동 한 나라인지 여부 체크
    // 2. 그리고 하루 지나 분배. 국경선 열린 인구수 / 열린 나라 수
    private static boolean canOpen(int now, int next) {
        return l <= Math.abs(now - next) && Math.abs(now - next) <= r;
    }

    private static boolean isWithinBound(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void resetBooleanArray(boolean[][] bArray) {
        for (boolean[] arr : bArray) {
            Arrays.fill(arr, false);
        }
    }

    private static boolean bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);

        int count = 0;
        int sum = 0;

        // 한번이라도 인구의 이동이 있었는지 확인하는 flag
        boolean populationShifted = false;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = now.x + dx[k];
                int ny = now.y + dy[k];
                if (isWithinBound(nx, ny) && canOpen(map[now.x][now.y], map[nx][ny]) && !populationShiftedArray[nx][ny] && !visited[nx][ny]) {
                    // 처음 삽입된 지역도 이동처리 하지 않았다면 인구 이동 처리
                    if (!populationShiftedArray[now.x][now.y]) {
                        sum += map[now.x][now.y];
                        populationShiftedArray[now.x][now.y] = true;
                        count++;
                    }
                    sum += map[nx][ny];
                    count++;
                    populationShiftedArray[nx][ny] = true;
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                    populationShifted = true;
                }
            }
        }

        if (count != 0) {
            int population = sum / count;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (populationShiftedArray[i][j]) {
                        map[i][j] = population;
                    }
                }
            }
        }

        if (populationShifted) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new boolean[n][n];
        populationShiftedArray = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        resetBooleanArray(populationShiftedArray);
                        visited[i][j] = true;
                        if (bfs(new Point(i, j))) flag = true;
                    }
                }
            }

            if (!flag) break;

            days++;

            resetBooleanArray(visited);
        }


        System.out.println(days);
    }
}
