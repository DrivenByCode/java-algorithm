import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD

// 백준_15649_N과 M(1)
public class Main {
    static int[] selectedNumbers;
    static boolean[] visited;
    static StringBuilder sb;
    static void permutation(int level,int n, int m) {
        if(level == m) {
            for(int i = 0; i < m; i++) {
                sb.append(selectedNumbers[i] + " ");
=======
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static int[] arr;
    private static int[] selectedNumbers;
    private static boolean[] visited;
    private static final StringBuilder sb = new StringBuilder();
    private static int n, m;

    private static void dfs(int level) {
        if (level == m) {
            for (final int num : selectedNumbers) {
                sb.append(num).append(" ");
>>>>>>> java-algorithm/master
            }
            sb.append("\n");
            return;
        }
<<<<<<< HEAD
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selectedNumbers[level] = i + 1;
                permutation(level + 1, n, m);
=======

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectedNumbers[level] = arr[i];
                dfs(level + 1);
>>>>>>> java-algorithm/master
                visited[i] = false;
            }
        }
    }
<<<<<<< HEAD
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        sb = new StringBuilder();

        selectedNumbers = new int[m];
        visited = new boolean[n];
        permutation(0, n, m);

        System.out.println(sb);
    }
}
=======

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = IntStream.range(1, n + 1).toArray();

        selectedNumbers = new int[m];
        visited = new boolean[n];

        dfs(0);

        System.out.println(sb.toString().trim());
    }
}
>>>>>>> java-algorithm/master
