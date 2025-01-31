import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] rank;
    private static int[] parent;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        initUnionFind(n);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int isInclude = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(isInclude == 0) {
                union(a, b);
                continue;
            }

            if(isInclude == 1) {
                if(find(a) == find(b)) {
                    sb.append("YES");
                    sb.append("\n");
                } else {
                    sb.append("NO");
                    sb.append("\n");
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
    
    private static void initUnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 부모를 찾고
    private static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // 더 높은 랭크를 가진 곳에 붙힘
    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) {
            return;
        }

        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            if(rank[rootX] == rank[rootY]) {
                rank[rootY]++;
            }
        }
    }
}