import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, t;
    private static List<Point> points = new ArrayList<>();
    private static Map<Point, List<Point>> adjList = new HashMap<>();

    private static class Point implements Comparable<Point> {
        private final int x;
        private final int y;

        private Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Point other) {
            if (this.y == other.y) {
                return this.x - other.x;
            }
            return this.y - other.y;
        }
    }

    private static void buildGraph() {
        Collections.sort(points);

        for (Point point : points) {
            adjList.put(point, new ArrayList<>());
        }

        int windowStart = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            while (windowStart < i && points.get(i).y - points.get(windowStart).y > 2) {
                windowStart++;
            }

            for (int j = windowStart; j < i; j++) {
                Point p2 = points.get(j);
                if (Math.abs(p1.x - p2.x) <= 2) {
                    adjList.get(p1).add(p2);
                    adjList.get(p2).add(p1);
                }
            }
        }
    }

    private static int bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Integer> distances = new HashMap<>();
        queue.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int currentDistance = distances.get(current);

            if (current.y == t) {
                return currentDistance;
            }

            for (Point neighbor : adjList.get(current)) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, currentDistance + 1);
                    queue.add(neighbor);
                }
            }
        }
        return -1;  // 목표 높이에 도달할 수 없는 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }

        // 시작점 (0, 0)을 리스트에 추가
        Point start = new Point(0, 0);
        points.add(start);
        
        // 그래프 구축
        buildGraph();

        // BFS 수행하여 최소 이동 횟수 찾기
        int result = bfs(start);
        System.out.println(result);
    }
}
