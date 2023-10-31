import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            results.add(currentVertex);
            for (int nextVertex : singers[currentVertex]) {
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
        if (results.size() != n) {
            System.out.println(0);
            return;
        }
        for (int i : results) {
            System.out.println(i);
        }
    }
}
