import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static ArrayList<ArrayList<Node>> tree;
    private static boolean[] visited;
    private static int maxDistacne = 0;
    private static int maxVertex = 0;

    private static class Node {
        private final int vertex;
        private final int distance;

        private Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    private static void dfs(int start, int distance) {
        if (tree.get(start) == null) {
            return;
        }

        if (distance > maxDistacne) {
            maxVertex = start;
            maxDistacne = distance;
        }

        visited[start] = true;

        for (Node node : tree.get(start)) {
            if (!visited[node.vertex]) {
                dfs(node.vertex, distance + node.distance);
            }
        }
    }

    private static int findDiameter() {
        // 1부터 가장 먼 거리의 점을 찾고.
        dfs(1, 0);
        Arrays.fill(visited, false);
        // 가장 먼거리의 점에서 다시 가장 먼거리 점을 찾음. 이게 지름.
        dfs(maxVertex, 0);
        return maxDistacne;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        tree = new ArrayList<>();
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            while (true) {
                int vertex2 = Integer.parseInt(st.nextToken());
                if (vertex2 == -1) break;

                int distance = Integer.parseInt(st.nextToken());

                tree.get(vertex1).add(new Node(vertex2, distance));
                tree.get(vertex2).add(new Node(vertex1, distance));
            }
        }

        System.out.println(findDiameter());
    }
}