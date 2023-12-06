import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] memoization = new int[31][31];

    private static int combination(int n, int m) {
        if (memoization[n][m] > 0) return memoization[n][m];
        if (n == 1 || m == 0 || m == n) return memoization[n][m] = 1;
        else if (m == 1) return memoization[n][m] = n;
        return memoization[n][m] = combination(n - 1, m) + combination(n - 1, m - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(combination(n, m));
        }
    }
}
