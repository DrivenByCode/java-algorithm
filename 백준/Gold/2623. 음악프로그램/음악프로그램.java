import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] indegree;
    private static ArrayList<Integer>[] graph;
    private static final StringBuilder sb = new StringBuilder();

    private static List<Integer> topologicalSort() {
        List<Integer> results = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            results.add(current);

            for (int next : graph[current]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
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

        indegree = new int[n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] inputs = br.readLine().split(" ");
            int count = Integer.parseInt(inputs[0]);

            for (int j = 1; j < count; j++) {
                int from = Integer.parseInt(inputs[j]);
                int to = Integer.parseInt(inputs[j + 1]);

                graph[from].add(to);
                indegree[to]++;
            }
        }

        List<Integer> results = topologicalSort();

        if (results.size() != n) {
            System.out.println(0);  // 사이클이 존재
        } else {
            for (int singer : results) {
                sb.append(singer).append("\n");
            }
            System.out.print(sb);
        }
    }
}
