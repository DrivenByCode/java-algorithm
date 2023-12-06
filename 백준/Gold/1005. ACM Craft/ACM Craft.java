import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, k, w;
    private static int[] indegree;
    private static int[] times;
    private static ArrayList<Integer>[] orders;

    private static int topologicalSort() {
        int[] results = new int[n + 1]; // 각 건물을 짓는데 필요한 최소 시간을 저장 하는 배열
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            results[i] = times[i - 1];

            // 진입 차수가 0이 된 정점을 큐에 삽입
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            for (final int nextVertex : orders[currentVertex]) {
                results[nextVertex] = Math.max(results[nextVertex], results[currentVertex] + times[nextVertex - 1]);
                indegree[nextVertex]--;

                // 진입 차수가 0이 된 정점을 큐에 삽입
                if (indegree[nextVertex] == 0) {
                    queue.offer(nextVertex);
                }
            }
        }
        return results[w];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < t; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            orders = new ArrayList[n + 1];
            indegree = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                orders[i] = new ArrayList<>();
            }

            times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                orders[x].add(y);
                // 진입 차수 늘려주기
                indegree[y]++;
            }

            w = Integer.parseInt(br.readLine());

            int result = topologicalSort();

            System.out.println(result);
        }
    }
}
