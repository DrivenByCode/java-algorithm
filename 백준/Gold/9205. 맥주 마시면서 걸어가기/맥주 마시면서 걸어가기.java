import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/9205

public class Main {

    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean bfs(List<Point> locations, int n) {
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 2];
        queue.offer(locations.get(0));
        visited[0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 현재 위치가 페스티벌 위치일 경우
            if (current.x == locations.get(n + 1).x && current.y == locations.get(n + 1).y) {
                return true;
            }

            for (int i = 1; i <= n + 1; i++) {
                if (!visited[i] && canReach(current, locations.get(i))) {
                    queue.offer(locations.get(i));
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    public static boolean canReach(Point from, Point to) {
        int dist = Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
        return dist <= 1000;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 편의점 수
            List<Point> locations = new ArrayList<>(); // 집, 편의점, 페스티벌 위치

            for (int i = 0; i <= n + 1; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                locations.add(new Point(x, y));
            }

            if (bfs(locations, n)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }
}
