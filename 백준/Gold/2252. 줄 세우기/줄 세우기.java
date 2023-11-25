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
    private static ArrayList<Integer>[] orders;
    private static final StringBuilder sb = new StringBuilder();

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            sb.append(currentVertex).append(" ");

            for (final int nextVertex : orders[currentVertex]) {
                indegree[nextVertex]--;
                if (indegree[nextVertex] == 0) {
                    queue.offer(nextVertex);
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
        orders = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            orders[i] = new ArrayList<>();
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            orders[a].add(b);
            indegree[b]++;
        }

        topologicalSort();

        System.out.println(sb.toString().trim());
    }
}
