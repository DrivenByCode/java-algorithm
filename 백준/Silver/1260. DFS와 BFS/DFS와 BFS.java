import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, v;
    static boolean[] visited;
    static int[][] adjMatrix;

    static String DFS(int num) {
        StringBuilder result = new StringBuilder();
        result.append(num).append(" ");
        visited[num] = true;
        for (int next = 1; next <= n; next++) {
            if (adjMatrix[num][next] == 1 && !visited[next]) {
                visited[next] = true;
                result.append(DFS(next));
            }
        }
        return result.toString();
    }

    static String BFS(int num) {
        StringBuilder result = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        while (!q.isEmpty()) {
            int now = q.poll();
            visited[now] = true;
            result.append(now).append(" ");
            for (int next = 1; next <= n; next++) {
                if (adjMatrix[now][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        adjMatrix = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        System.out.println(DFS(v).trim());
        Arrays.fill(visited, false);
        System.out.println(BFS(v).trim());
    }
}
