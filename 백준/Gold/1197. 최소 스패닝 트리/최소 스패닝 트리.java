import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] rank;

    static class Edge implements Comparable<Edge> {
        private final int vertex1;
        private final int vertex2;
        private final int cost;

        private Edge(int vertex1, int vertex2, int cost) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.cost = cost;
        }

        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    private static void UnionFindInit(int size) {
        rank = new int[size + 1];
        parent = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private static int Find(int x) {
        if (parent[x] == x) return parent[x];
        return parent[x] = Find(parent[x]);
    }

    private static void Union(int x, int y) {
        int rootX = Find(x); // x의 부모노드 찾기
        int rootY = Find(y); // y의 부모노드 찾기

        // 이미 같은 그래프에 속해있을 때 false 반환
        if (rootX == rootY) return;

        // 랭크가 더 낮은 트리를 높은 트리에 붙임
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

        UnionFindInit(v + 1);

        ArrayList<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(vertex1, vertex2, cost));
        }

        // 간선들을 가중치에 따라 오름차순 정렬
        Collections.sort(edgeList);

        int answer = 0;

        for (Edge eg : edgeList) {
            int fv1 = Find(eg.vertex1);
            int fv2 = Find(eg.vertex2);
            if (fv1 != fv2) {
                answer += eg.cost;
                Union(eg.vertex1, eg.vertex2);
            }
//            System.out.println(eg.vertex1 + " " + eg.vertex2 + " " + eg.cost);
        }

        System.out.println(answer);
    }
}
