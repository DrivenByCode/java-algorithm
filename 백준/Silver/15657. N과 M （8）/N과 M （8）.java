import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int[] selectedNumbers;
    private static final StringBuilder sb = new StringBuilder();
    private static int n, m;

    private static void dfs(int level, int start) {
        if (level == m) {
            for (int selectedNumber : selectedNumbers) {
                sb.append(selectedNumber).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            selectedNumbers[level] = arr[i];
            dfs(level + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        selectedNumbers = new int[m];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        dfs(0, 0);

        System.out.println(sb.toString().trim());
    }
}
