import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] buildingProcess;
    private static int[] times;
    private static int[] indegree;
    private static int n;

    private static int[] topologySort() {
        int[] results = new int[n + 1];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            results[i] = times[i];
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentOrder = queue.poll();
            for (final int nextOrder : buildingProcess[currentOrder]) {
                indegree[nextOrder]--;
                if (indegree[nextOrder] == 0) {
                    queue.offer(nextOrder);
                }

                // results[currentOrder] 는 사전에 지어야 할 건물 건설 시간 중 가장 큰 값을 저장함
                results[nextOrder] = Math.max(results[nextOrder], results[currentOrder] + times[nextOrder]);
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        times = new int[n + 1];
        indegree = new int[n + 1];
        buildingProcess = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            buildingProcess[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    break;
                }
                buildingProcess[num].add(i);
                indegree[i]++;
            }
            times[i] = time;
        }

        int[] results = topologySort();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(results[i]).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}
