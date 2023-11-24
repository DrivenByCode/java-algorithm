import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int[] selectedNumbers;
    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int level, int n, int m) {
        if (level == m) {
            for (int selectedNumber : selectedNumbers) {
                sb.append(selectedNumber).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            selectedNumbers[level] = arr[i];
            dfs(level + 1, n, m);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        selectedNumbers = new int[m];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        dfs(0, n, m);

        System.out.println(sb.toString().trim());
    }
}
