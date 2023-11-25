import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;
    private static int[] indegree;
    private static ArrayList<Integer>[] orders;
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
            int currentVertex = queue.poll();
            results.add(currentVertex);

            for (final int nextVertex : orders[currentVertex]) {
                indegree[nextVertex]--;
                if (indegree[nextVertex] == 0) {
                    queue.offer(nextVertex);
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
        orders = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            orders[i] = new ArrayList<>();
        }

        for (int j = 0; j < m; j++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int count = arr[0];
            int lastOne = -1;
            for (int k = 1; k <= count; k++) {
                if (lastOne != -1) {
                    orders[lastOne].add(arr[k]);
                    indegree[arr[k]]++;
                }
                lastOne = arr[k];
            }
        }

        final List<Integer> results = topologicalSort();

        if (results.size() != n) {
            System.out.println(0);
            return;
        }

        for (final int result : results) {
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}
