import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int[] rank;
    private static int[] count;
    private static final int MAX_VALUE = (int)10e6 + 1;
    private static void initUnionFind() {
        parent = new int[MAX_VALUE];
        rank = new int[MAX_VALUE];
        count = new int[MAX_VALUE];
        for(int i = 1; i <= MAX_VALUE - 1; i++) {
            parent[i] = i;
            rank[i] = 1;
            count[i] = 1;
        }
    }

    private static int find(int x) {
        if(x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) {
            return;
        }

        if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
            count[rootX] += count[rootY];
        } else {
            parent[rootX] = rootY;
            count[rootY] += count[rootX];
            if(rank[rootX] == rank[rootY]) {
                rank[rootY]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        initUnionFind();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            } else {
                int c = Integer.parseInt(st.nextToken());
                System.out.println(count[find(c)]);
            }
        }
    }
}