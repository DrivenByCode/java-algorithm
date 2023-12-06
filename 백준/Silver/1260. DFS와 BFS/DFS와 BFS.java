import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, v;
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;

    static String DFS(int num) {
        StringBuilder result = new StringBuilder();
        result.append(num).append(" ");
        visited[num] = true;
        for (int next : adjList[num]) {
            if (!visited[next]) {
                visited[next] = true;
                result.append(DFS(next));
            }
        }
        return result.toString();
    }

    static String BFS(int num) {
        Arrays.fill(visited, false);
        StringBuilder result = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        while (!q.isEmpty()) {
            int now = q.poll();
            visited[now] = true;
            result.append(now).append(" ");
            for (int next : adjList[now]) {
                if (!visited[next]) {
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
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 인접 리스트 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(adjList[i]);
        }

        System.out.println(DFS(v).trim());
        System.out.println(BFS(v).trim());
    }
}
