import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] computers;
    private static int totalComputers;
    private static int n;
    private static boolean[] visited;

    private static int dfs(int node) {
        visited[node] = true;
        int count = 1;
        for (int i = 1; i <= totalComputers; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                count += dfs(i);
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        totalComputers = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        computers = new int[totalComputers + 1][totalComputers + 1];
        visited = new boolean[totalComputers + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            computers[start][end] = 1;
            computers[end][start] = 1;
        }

        int answer = dfs(1) - 1; // 본인인 1번 컴퓨터 제외

        System.out.println(answer);
    }
}
