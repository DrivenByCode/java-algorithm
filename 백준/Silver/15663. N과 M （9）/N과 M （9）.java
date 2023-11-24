import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int[] selectedNumbers;
    private static boolean[] visited;
    private static final StringBuilder sb = new StringBuilder();
    private static final Set<String> generatedSequences = new HashSet<>();
    private static int n, m;

    private static void dfs(int level) {
        if (level == m) {
            String sequence = Arrays.toString(selectedNumbers);
            if (!generatedSequences.contains(sequence)) {
                generatedSequences.add(sequence);
                for (int num : selectedNumbers) {
                    sb.append(num).append(" ");
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectedNumbers[level] = arr[i];
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

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        selectedNumbers = new int[m];
        visited = new boolean[n];

        dfs(0);

        System.out.println(sb.toString().trim());
    }
}
