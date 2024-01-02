import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] students;
    private static boolean[] visited;
    private static boolean[] finished;
    private static int count;

    private static void dfs(int v) {
        if (visited[v]) return;
        visited[v] = true;
        int next = students[v];

        if (!visited[next]) {
            dfs(next);
        } else {
            if (!finished[next]) {
                count++;
                // cycle 계산
                for (int i = next; i != v; i = students[i]) {
                    count++;
                }
            }
        }
        finished[v] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스의 수

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            students = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                dfs(i);
            }

            System.out.println(n - count); // 사이클에 속하지 않는 학생의 수
        }

        br.close();
    }


}

