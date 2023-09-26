import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] indegree;
    private static ArrayList<Integer>[] map;
    private static StringBuilder sb = new StringBuilder();

    private static void topologicalSort() {
        // 난이도도 반영하기 위해 최소힙 이용
        // 숫자가 작을 수록 난이도가 쉬움
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int j = 1; j <= n; j++) {
            if (indegree[j] == 0) {
                // 진입 차수가 0인것을 큐에 삽입
                pq.offer(j);
            }
        }

        while (!pq.isEmpty()) {
            int currentVertex = pq.poll();
            sb.append(currentVertex + " ");

            for (int nextVertex : map[currentVertex]) {
                indegree[nextVertex]--;
                if (indegree[nextVertex] == 0) {
                    pq.add(nextVertex);
                }
            }
        }

        System.out.println(sb);
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

        topologicalSort();
    }
}
