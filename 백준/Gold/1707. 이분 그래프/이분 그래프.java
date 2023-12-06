import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int[] color;
    private static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            color = new int[v + 1];
            map = new ArrayList[v + 1];
            for (int j = 1; j <= v; j++) {
                map[j] = new ArrayList<>();
            }

            for (int l = 0; l < e; l++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x].add(y);
                map[y].add(x);
            }

            boolean isBipartite = true;
            for (int j = 1; j <= v; j++) {
                if (color[j] == 0) {
                    if (!dfs(j, 1)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            System.out.println(isBipartite ? "YES" : "NO");
        }
    }

    public static boolean dfs(int node, int c) {
        color[node] = c;
        for (int adjNode : map[node]) {
            if (color[adjNode] == c) {
                return false;
            }
            // 인접노드와 반대되는 색 칠하기 위해 3 - c 이용
            if (color[adjNode] == 0 && !dfs(adjNode, 3 - c)) {
                return false;
            }
        }
        return true;
    }
}
