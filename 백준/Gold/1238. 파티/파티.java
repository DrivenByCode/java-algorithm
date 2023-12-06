import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Edge>[] adjList, reverseAdjList;
    private static int[] distance, reverseDistance;

    private static class Edge implements Comparable<Edge> {
        private final int vertex;
        private final int time;

        private Edge(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }

        @Override
        public int compareTo(Edge other) {
            // 오름차순 정렬
            return this.time - other.time;
        }
    }

    private static void dijkstra(int start, ArrayList<Edge>[] graph, int[] dist) {
        // 우선순위 큐를 이용하여 어차피 단방향만 주어지지만 A -> X -> A 할 수 있는 경로만 들어온다고 주어짐
        // 만약 X->A도 주어지면 PQ가 자동으로 시간순으로 정렬하여 주기 때문에 X까지 갈 때와 올 때 모두 최단거리를 계산가능
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge currentNode = pq.poll();
            int currentVertex = currentNode.vertex;
            int currentCost = currentNode.time;

            if (currentCost > dist[currentVertex]) continue;

            for (Edge neighbor : graph[currentVertex]) {
                int newCost = currentCost + neighbor.time;
                if (dist[neighbor.vertex] > newCost) {
                    dist[neighbor.vertex] = newCost;
                    pq.offer(new Edge(neighbor.vertex, newCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        reverseAdjList = new ArrayList[n + 1];
        distance = new int[n + 1];
        reverseDistance = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(reverseDistance, Integer.MAX_VALUE);

        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
            reverseAdjList[i] = new ArrayList<>();
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            adjList[start].add(new Edge(end, time));
            reverseAdjList[end].add(new Edge(start, time)); // 방향을 반대로
        }

        dijkstra(x, adjList, distance); // X 마을에서 각 마을로 가는 최단 거리
        dijkstra(x, reverseAdjList, reverseDistance); // 각 마을에서 X 마을로 가는 최단 거리

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(distance[i] + reverseDistance[i], max); // 왕복 시간 계산
        }

        System.out.println(max);
    }
}

