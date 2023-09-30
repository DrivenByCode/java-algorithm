import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        private final int vertex;
        private final int weight;

        private Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private static class TreeDiameter {
        private ArrayList<Node>[] adjList;
        private boolean[] visited;
        private int maxDistance;
        private int maxVertex;

        private TreeDiameter(int n) {
            adjList = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }
            visited = new boolean[n + 1];
        }

        private void addEdge(int parent, int child, int weight) {
            adjList[parent].add(new Node(child, weight));
            adjList[child].add(new Node(parent, weight));
        }

        private void dfs(int vertex, int distance) {
            if (vertex < 1 || adjList.length <= vertex || adjList[vertex] == null) {
                return;  // 인덱스 범위를 벗어나거나, adjList[vertex]가 null인 경우 종료
            }
            visited[vertex] = true;
            if (distance > maxDistance) {
                maxDistance = distance;
                maxVertex = vertex;
            }
            for (Node node : adjList[vertex]) {
                if (!visited[node.vertex]) {
                    dfs(node.vertex, distance + node.weight);
                }
            }
        }

        private int findDiameter() {
            dfs(1, 0); // 가장 먼 노드를 찾습니다. 이때 찾은 노드를 maxVertex로 저장합니다.
            Arrays.fill(visited, false);  // visted 배열 초기화
            dfs(maxVertex, 0); // maxVertex부터 가장 먼 노드까지의 거리를 찾습니다. 이때, 찾은 maxDistance의 길이가 트리의 지름이 됩니다.
            return maxDistance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeDiameter treeDiameter = new TreeDiameter(n);

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            treeDiameter.addEdge(parent, child, weight);
        }

        System.out.println(treeDiameter.findDiameter());
    }
}