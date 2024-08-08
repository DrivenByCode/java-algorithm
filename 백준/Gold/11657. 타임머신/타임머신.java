import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11657
public class Main {
    private static List<Edge>[] edges;
    private static long[] dist;

    private static class Edge {
        private final int vertex;
        private final int cost;

        private Edge(final int vertex, final int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    private static boolean bellmanFord(int n) {
        dist[1] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Edge edge : edges[j]) {
                    if (dist[j] != Long.MAX_VALUE && dist[edge.vertex] > dist[j] + edge.cost) {
                        dist[edge.vertex] = dist[j] + edge.cost;
                    }
                }
            }
        }

        for (int j = 1; j <= n; j++) {
            for (Edge edge : edges[j]) {
                if (dist[j] != Long.MAX_VALUE && dist[edge.vertex] > dist[j] + edge.cost) {
                    return true; // 음수 사이클 존재
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
        }

        if (bellmanFord(n)) {
            System.out.println("-1");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == Long.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }
}