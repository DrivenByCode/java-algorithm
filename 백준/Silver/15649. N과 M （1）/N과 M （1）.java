import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준_15649_N과 M(1)
public class Main {
    static int[] selectedNumbers;
    static boolean[] visited;
    static StringBuilder sb;
    static void permutation(int level,int n, int m) {
        if(level == m) {
            for(int i = 0; i < m; i++) {
                sb.append(selectedNumbers[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selectedNumbers[level] = i + 1;
                permutation(level + 1, n, m);
                visited[i] = false;
            }
        }
    }
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