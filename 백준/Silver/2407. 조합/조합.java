import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BigInteger[][] memoization;

    private static BigInteger getCombinationResult(int n, int m) {
        if (memoization[n][m] != null) return memoization[n][m];

        if (n == m || m == 0) {
            memoization[n][m] = BigInteger.ONE;
        } else if (m == 1) {
            memoization[n][m] = BigInteger.valueOf(n);
        } else {
            memoization[n][m] = getCombinationResult(n - 1, m).add(getCombinationResult(n - 1, m - 1));
        }
        return memoization[n][m];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        memoization = new BigInteger[n + 1][n + 1];

        System.out.println(getCombinationResult(n, m));
    }
}
