import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static class Star {
        private double x, y;

        private Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int node;
        private double weight;

        private Edge(int node, double weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // 가까운 거리순으로 정렬
            return Double.compare(this.weight, o.weight);
        }
    }

    private static double calculateDistance(Star a, Star b) {
        // 유클리드 거리
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Star[] stars = new Star[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            stars[i] = new Star(Double.parseDouble(input[0]), Double.parseDouble(input[1]));
        }

        // 시작 노드를 0으로 설정
        pq.offer(new Edge(0, 0));
        double result = 0;

        // 프림 알고리즘
        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.node]) {
                continue;
            }

            visited[current.node] = true;
            result += current.weight;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    pq.offer(new Edge(i, calculateDistance(stars[current.node], stars[i])));
                }
            }
        }

        System.out.printf("%.2f\n", result);
    }
}
