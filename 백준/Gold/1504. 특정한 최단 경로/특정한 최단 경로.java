import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int n, e;
    private static ArrayList<Node>[] graph;
    private static int[] dist;

    private static class Node implements Comparable<Node> {
        private final int vertex;
        private final int distance;

        private Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentVertex = currentNode.vertex;
            int currentDistance = dist[currentVertex];

            if (currentDistance > dist[currentVertex]) {
                continue;
            }

            for (Node nextNode : graph[currentVertex]) {
                int newCost = currentDistance + nextNode.distance;
                int nextVertex = nextNode.vertex;

                if (newCost < dist[nextVertex]) {
                    dist[nextVertex] = newCost;
                    priorityQueue.offer(new Node(nextVertex, newCost));
                }
            }
        }

        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int path1 = 0;
        int path2 = 0;

        int dist1 = dijkstra(1, v1);
        int dist2 = dijkstra(v1, v2);
        int dist3 = dijkstra(v2, n);
        int dist4 = dijkstra(1, v2);
        int dist5 = dijkstra(v2, v1);
        int dist6 = dijkstra(v1, n);

        if (dist1 == -1 || dist2 == -1 || dist3 == -1) {
            path1 = -1;
        } else {
            path1 = dist1 + dist2 + dist3;
        }

        if (dist4 == -1 || dist5 == -1 || dist6 == -1) {
            path2 = -1;
        } else {
            path2 = dist4 + dist5 + dist6;
        }

        if (path1 == -1 && path2 == -1) {
            System.out.println(-1);
        } else if (path1 == -1) {
            System.out.println(path2);
        } else if (path2 == -1) {
            System.out.println(path1);
        } else {
            System.out.println(Math.min(path1, path2));
        }
    }
}
