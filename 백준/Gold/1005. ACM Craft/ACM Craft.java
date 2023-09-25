import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;

    private static int topologicalSort(int[] indegree, int[] times, ArrayList<Integer>[] map, int target) {
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[n + 1]; // 각 건물을 짓는 데 필요한 최소 시간을 저장하는 배열

        for (int i = 1; i <= n; i++) {
            result[i] = times[i];
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int currentVertex = q.poll();

            for (int nextVertex : map[currentVertex]) {
                result[nextVertex] = Math.max(result[nextVertex], result[currentVertex] + times[nextVertex]);
                indegree[nextVertex]--;

                // 진입 차수가 0이 된 정점을 큐에 삽입
                if (indegree[nextVertex] == 0) {
                    q.add(nextVertex);
                }
            }
        }

        return result[target];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] times = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                times[j] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Integer>[] map = new ArrayList[n + 1];
            for (int l = 1; l <= n; l++) {
                map[l] = new ArrayList<>();
            }

            int[] indegree = new int[n + 1];
            for (int v = 0; v < k; v++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x].add(y);
                indegree[y]++;
            }

            int target = Integer.parseInt(br.readLine());

            System.out.println(topologicalSort(indegree, times, map, target));
        }
    }
}
