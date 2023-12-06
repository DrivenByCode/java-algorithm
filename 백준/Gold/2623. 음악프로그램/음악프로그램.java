import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] singers;
    private static int[] indegree;
    private static int n, m;

    private static ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> results = new ArrayList<>();
=======
import java.util.*;

public class Main {
    private static int n, m;
    private static int[] indegree;
    private static ArrayList<Integer>[] orders;
    private static final StringBuilder sb = new StringBuilder();

    private static List<Integer> topologicalSort() {
        List<Integer> results = new ArrayList<>();
>>>>>>> java-algorithm/master

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            results.add(currentVertex);
<<<<<<< HEAD
            for (int nextVertex : singers[currentVertex]) {
=======

            for (final int nextVertex : orders[currentVertex]) {
>>>>>>> java-algorithm/master
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

<<<<<<< HEAD
        singers = new ArrayList[n + 1];
        indegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            singers[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 1; j < inputs.length - 1; j++) {
                int x = Integer.parseInt(inputs[j]);
                int y = Integer.parseInt(inputs[j + 1]);
                singers[x].add(y);
                indegree[y]++;
            }
        }

        ArrayList<Integer> results = topologicalSort();

        // 사이클이 있는 경우
=======
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

>>>>>>> java-algorithm/master
        if (results.size() != n) {
            System.out.println(0);
            return;
        }
<<<<<<< HEAD
        for (int i : results) {
            System.out.println(i);
        }
=======

        for (final int result : results) {
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString().trim());
>>>>>>> java-algorithm/master
    }
}
