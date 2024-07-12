import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static boolean found;

    private static void dfs(int node, int depth) {
        if (depth == 5) {
            found = true;
            return;
        }

        visited[node] = true;

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, depth + 1);
                if (found) return;
            }
        }

        visited[node] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        visited = new boolean[n];
        found = false;

        for (int i = 0; i < n; i++) {
            if (!found) {
                dfs(i, 1);
            }
        }

        if (found) {
            System.out.println(1);
            return;
        }
        System.out.println(0);

    }
}
