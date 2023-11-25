import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int[] selectedNumbers;
    private static boolean[] visitedIndex;
    private static final LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
    private static StringBuilder sb;
    private static int n, m;

    private static void dfs(int level) {
        if (level == m) {
            sb = new StringBuilder();
            for (final int num : selectedNumbers) {
                sb.append(num).append(" ");
            }
            linkedHashSet.add(sb.toString().trim());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visitedIndex[i]) {
                visitedIndex[i] = true;
                selectedNumbers[level] = arr[i];
                dfs(level + 1);
                visitedIndex[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        selectedNumbers = new int[m];
        visitedIndex = new boolean[n];

        dfs(0);

        sb = new StringBuilder();
        for (String str : linkedHashSet) {
            sb.append(str).append("\n");
        }
        System.out.print(sb.toString().trim());
    }
}
