import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// MST_ 크루스칼 - 유니온파인드 이용

public class Main {
    private static int[] parent;
    private static int[] rank;

    private static class Edge implements Comparable<Edge> {
        private final int v1;
        private final int v2;
        private final int cost;

        private Edge(final int v1, final int v2, final int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static void initUnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    private static int find(int x) {
        if (parent[x] != x) {
            return parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            if (rank[rootX] == rank[rootY]) {
                rank[rootY]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        initUnionFind(n);

        List<Edge> edgeList = new ArrayList<>();

        long totalCost = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            totalCost += c;

            edgeList.add(new Edge(a, b, c));
        }

        // 비용이 작은 순으로 정렬
        Collections.sort(edgeList);

        // 트리의 간선의 수는 정점의 수 - 1, edge = vertex - 1;
        int cnt = 0;

        long mstCost = 0;

        for (Edge eg : edgeList) {
            int fv1 = find(eg.v1);
            int fv2 = find(eg.v2);

            // 같은 집합에 속해있지 않으면
            if (fv1 != fv2) {
                mstCost += eg.cost;
                union(eg.v1, eg.v2);
                cnt++;
            }

            // 트리의 간선의 수는 정점의 수 - 1이므로 그걸 초과하면 멈추기
            if (cnt == n - 1) break;
        }

        // 모든 도시가 연결되지 않은 경우 처리
        if (cnt != n - 1) {
            System.out.println("-1");
            return;
        }

        System.out.println(totalCost - mstCost);
    }
}
