import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] indegree;
    private static final StringBuilder sb = new StringBuilder();
    private static ArrayList<Integer>[] graph;

    private static void topologicalSort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 진입차수가 0인 노드들을 우선순위 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int current = pq.poll();
            sb.append(current).append(" ");

            for (int next : graph[current]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            indegree[to]++;
        }

        topologicalSort();

        System.out.println(sb.toString().trim());
    }
}
