import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] selectedNumbers;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[] arr;
    static int n, m;

    // 순열 구하기
    private static void getPermutation(int level) {
        if (level == m) {
            for (int selectedNumber : selectedNumbers) {
                sb.append(selectedNumber + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectedNumbers[level] = arr[i];
                getPermutation(level + 1);
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

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        getPermutation(0);

        System.out.println(sb);
    }
}
