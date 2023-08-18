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
    static int[][] map;
    static String ans1 = "";
    static String ans2 = "";

    static void DFS(int num) {
        ans1 += num + " ";
        visited[num] = true;
        for (int next = 1; next <= n; next++) {
            if (map[num][next] == 1 && !visited[next]) {
                visited[next] = true;
                DFS(next);
            }
        }
    }

    static void BFS(int num) {
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        while (!q.isEmpty()) {
            int now = q.poll();
            visited[now] = true;
            ans2 += now + " ";
            for (int next = 1; next <= n; next++) {
                if (map[now][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(st.nextToken());

        v = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];

        map = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[b][a] = 1;
        }

        DFS(v);

        System.out.println(ans1.trim());

        Arrays.fill(visited, false);

        BFS(v);

        System.out.println(ans2.trim());

    }
}
