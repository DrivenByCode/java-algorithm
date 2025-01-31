import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] visited;
    private static int[][] map;
    private static int n, m;
    private static int[] rank;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];

        initUnionFind();

        StringTokenizer st;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int movement = Integer.parseInt(st.nextToken());
                map[i][j] = movement;
                map[j][i] = movement;
            }
        }

        st = new StringTokenizer(br.readLine());

        int[] route = new int[m];

        for (int i = 0; i < m; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        bfs(route[0]);

        for (int i = 1; i < route.length; i++) {
            // 인접해 있는 여행지끼리는 같은 집합에 있어야 방문할 수 있음을 이용
            if (find(route[i - 1]) != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    
     private static void initUnionFind() {
        rank = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            rank[i] = 1;
            parent[i] = i;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if(rank[rootX] <= rank[rootY]) {
            parent[rootX] = rootY;
            if(rank[rootX] == rank[rootY]) {
                rank[rootX]++;
            }
        } 
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int currentVertex = q.poll();
            for (int nextVertex = 1; nextVertex <= n; nextVertex++) {
                if (map[currentVertex][nextVertex] == 1 && !visited[nextVertex]) {
                    q.add(nextVertex);
                    visited[nextVertex] = true;
                    // 인접해 있는 여행지 경로끼리는 같은 집합에 넣어야 함
                    if (find(currentVertex) != find(nextVertex)) {
                        union(currentVertex, nextVertex);
                    }
                }
            }
        }
    }

}
