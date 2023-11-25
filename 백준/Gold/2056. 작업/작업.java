import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[] indegree;
    private static ArrayList<Integer>[] orders;
    private static int[] times;

    private static int topologicalSort() {
        int[] results = new int[n + 1];
        final Queue<Integer> queue = new LinkedList<>();


        for (int i = 1; i <= n; i++) {
            results[i] = times[i];

            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            for (final int nextVertex : orders[currentVertex]) {
                indegree[nextVertex]--;

                results[nextVertex] = Math.max(results[nextVertex], results[currentVertex] + times[nextVertex]);
                if (indegree[nextVertex] == 0) {
                    queue.offer(nextVertex);
                }
            }
        }

        return Arrays.stream(results).max().orElse(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        indegree = new int[n + 1];
        orders = new ArrayList[n + 1];
        times = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            orders[i] = new ArrayList<>();
        }

        for (int j = 1; j <= n; j++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            times[j] = t;
            int c = Integer.parseInt(st.nextToken());
            for (int k = 0; k < c; k++) {
                final int tmp = Integer.parseInt(st.nextToken());
                orders[tmp].add(j);
                indegree[j]++;
            }
        }

        final int answer = topologicalSort();

        System.out.println(answer);
    }
}
