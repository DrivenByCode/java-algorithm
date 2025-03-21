import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] selectedNumbers;
    private static boolean[] visited;
    private static final StringBuilder sb = new StringBuilder();
    private static int n, m;

    private static void dfs(int level) {
        if (level == m) {
            for (int num : selectedNumbers) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectedNumbers[level] = i + 1;
                dfs(level + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        selectedNumbers = new int[m];
        visited = new boolean[n];

        dfs(0);

        System.out.println(sb.toString());
    }
}