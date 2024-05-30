import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int[] depth;

    private static int findRoot(int n) {
        for (int i = 1; i <= n; i++) {
            if (parent[i] == -1) {
                return i;
            }
        }
        return -1; // 루트가 없는 경우는 없다 (문제 가정상 항상 루트가 있음)
    }

    private static void calculateDepth(int node, int d) {
        depth[node] = d;
        for (int i = 1; i < parent.length; i++) {
            if (parent[i] == node) {
                calculateDepth(i, d + 1);
            }
        }
    }

    private static int findLCA(int node1, int node2) {
        // node1을 더 깊은 노드로 맞춤
        if (depth[node1] < depth[node2]) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        // 깊이를 맞춤
        while (depth[node1] > depth[node2]) {
            node1 = parent[node1];
        }

        // 공통 조상을 찾음
        while (node1 != node2) {
            node1 = parent[node1];
            node2 = parent[node2];
        }

        return node1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine()); // 노드 수
            parent = new int[n + 1];
            depth = new int[n + 1];
            Arrays.fill(parent, -1);

            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // a가 b의 부모
                parent[b] = a;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 루트를 찾아서 그 깊이를 0으로 설정
            int root = findRoot(n);
            calculateDepth(root, 0);

            // 두 노드를 같은 깊이로 맞춘 후 공통 조상을 찾기
            int lca = findLCA(node1, node2);
            sb.append(lca).append("\n");
        }

        System.out.print(sb);
    }
}