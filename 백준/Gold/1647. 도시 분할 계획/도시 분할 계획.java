import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] rank;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        final private int vertex1;
        final private int vertex2;
        final private int cost;

        private Edge(int vertex1, int vertex2, int cost) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            // 오름차순 정렬
            return this.cost - other.cost;
        }
    }

    static private void UnionFindInit(int size) {
        parent = new int[size + 1];
        rank = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            rank[i] = 1;
            parent[i] = i;
        }
    }

    private static int Find(int x) {
        if (parent[x] == x) return parent[x];
        return parent[x] = Find(parent[x]);
    }

    private static void Union(int x, int y) {
        int rootX = Find(x);
        int rootY = Find(y);

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

        int v = Integer.parseInt(st.nextToken());

        int e = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edgeArrayList = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeArrayList.add(new Edge(a, b, c));
        }

        UnionFindInit(v);

        Collections.sort(edgeArrayList);

        ArrayList<Integer> selectedEdges = new ArrayList<>();
        int result = 0;
        int edgeCount = 0;  // 실제로 선택된 엣지의 수를 세기 위함.

        for (Edge eg : edgeArrayList) {
            int fv1 = Find(eg.vertex1);
            int fv2 = Find(eg.vertex2);

            if (fv1 != fv2) {
                result += eg.cost;
                selectedEdges.add(eg.cost);  // 추가된 부분
                Union(eg.vertex1, eg.vertex2);
                edgeCount++;
            }

            if (edgeCount == v - 1) break;

        }

        // 가장 큰 비용을 가진 엣지를 제거
        Collections.sort(selectedEdges);
        result -= selectedEdges.get(selectedEdges.size() - 1);  // 추가된 부분

        System.out.println(result);
    }
}
