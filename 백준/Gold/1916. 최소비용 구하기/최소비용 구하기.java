import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static ArrayList<Node>[] city;
    private static int[] dist;

    private static class Node implements Comparable<Node> {
        private final int vertex;
        private final int cost;

        private Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(start, 0));
        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentVertex = currentNode.vertex;
            int currentCost = currentNode.cost;
            for (Node nextNode : city[currentVertex]) {
                int nextCost = nextNode.cost + currentCost;
                int nextVertex = nextNode.vertex;
                // 최소 거리 갱신
                if (nextCost < dist[nextVertex]) {
                    priorityQueue.offer(new Node(nextVertex, nextCost));
                    dist[nextVertex] = nextCost;
                }
            }
        }

        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        city = new ArrayList[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <= n; i++) {
            city[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            city[start].add(new Node(target, dist));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 정렬
        for (ArrayList<Node> nodeArrayList : city) {
            Collections.sort(nodeArrayList);
        }

        System.out.println(dijkstra(start, end));
    }
}
