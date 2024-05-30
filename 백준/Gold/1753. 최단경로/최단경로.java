import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dist;

    private static class Node implements Comparable<Node> {
        private final int vertex;
        private final int distance;

        private Node(final int vertex, final int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }

    private static void dijkstra(List<List<Node>> graph, int start) {
        int n = graph.size();
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.distance > dist[current.vertex]) {
                continue;
            }

            for (Node neighbor : graph.get(current.vertex)) {
                int newDist = current.distance + neighbor.distance;
                if (newDist < dist[neighbor.vertex]) {
                    pq.offer(new Node(neighbor.vertex, newDist));
                    // 최소 값 갱신
                    dist[neighbor.vertex] = newDist;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 그래프 초기화
        List<List<Node>> graph = new ArrayList<>();


        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; ++i) {
            graph.add(new ArrayList<>());
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        dijkstra(graph, k);

        for (int i = 1; i <= V; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }
}