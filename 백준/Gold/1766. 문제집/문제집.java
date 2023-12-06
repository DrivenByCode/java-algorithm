import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] indegree;
<<<<<<< HEAD
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
=======
    private static ArrayList<Integer>[] orders;
    private static final StringBuilder sb = new StringBuilder();

    private static void topologicalSort() {
        // 가능하면 쉬운 문제 부터 풀기 위해 PQ 사용
        final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                priorityQueue.offer(i);
            }
        }

        while (!priorityQueue.isEmpty()) {
            int currentVertex = priorityQueue.poll();
            sb.append(currentVertex).append(" ");
            for (final int nextVertex : orders[currentVertex]) {
                indegree[nextVertex]--;
                if (indegree[nextVertex] == 0) {
                    priorityQueue.offer(nextVertex);
                }
            }
        }
>>>>>>> java-algorithm/master
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

<<<<<<< HEAD
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
=======
        indegree = new int[n + 1];
        orders = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            orders[i] = new ArrayList<>();
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            orders[a].add(b);
            indegree[b]++;
        }

        topologicalSort();

        System.out.println(sb.toString().trim());
>>>>>>> java-algorithm/master
    }
}
