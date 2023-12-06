import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.util.*;
=======
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
>>>>>>> java-algorithm/master

public class Main {
    private static int n, m;
    private static int[] indegree;
<<<<<<< HEAD
    private static ArrayList<Integer>[] map;
    private static StringBuilder sb = new StringBuilder();

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int j = 1; j <= n; j++) {
            if (indegree[j] == 0) {
                // 진입 차수가 0인것을 큐에 삽입
                q.add(j);
            }
        }

        // 반대로 출력하기 위해 Deque 이용
        Deque<Integer> stk = new ArrayDeque<>();

        while (!q.isEmpty()) {
            int currentVertex = q.poll();
            stk.push(currentVertex);
            for (int nextVertex : map[currentVertex]) {
                indegree[nextVertex]--;
                if (indegree[nextVertex] == 0) {
                    q.add(nextVertex);
                }
            }
        }

        while (!stk.isEmpty()) {
            sb.append(stk.pop() + " ");
        }

        System.out.println(sb);
=======
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
            // 앞에 온 숫자가 가중치를 받고 얼마나 앞인지 알아야 함.
            // 따라서 진입차수는 x가 늘어나고, 그걸 역으로 출력
            map[y].add(x);
            // 진입 차수 늘려주기
            indegree[x]++;
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
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            orders[a].add(b);
            indegree[b]++;
        }

        topologicalSort();

        System.out.println(sb.toString().trim());
>>>>>>> java-algorithm/master
    }
}
