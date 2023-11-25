import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] indegree;
    private static ArrayList<Integer>[] map;

    private static int[] topologicalSort() {
        int[] results = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            results[i] = 1;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int j = 1; j <= n; j++) {
            if (indegree[j] == 0) {
                // 진입 차수가 0인것을 큐에 삽입
                q.add(j);
            }
        }

        while (!q.isEmpty()) {
            int currentVertex = q.poll();
            for (final int nextVertex : map[currentVertex]) {
                indegree[nextVertex]--;
                if (indegree[nextVertex] == 0) {
                    q.add(nextVertex);
                    results[nextVertex] = Math.max(results[nextVertex], results[nextVertex] + results[currentVertex]);
                }
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        indegree = new int[n + 1];

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x].add(y);
            // 진입 차수 늘려주기
            indegree[y]++;
        }

        final int[] results = topologicalSort();

        final StringBuilder sb = new StringBuilder();
        for (int k = 1; k < results.length; k++) {
            sb.append(results[k] + " ");
        }

        System.out.println(sb.toString().trim());
    }
}
