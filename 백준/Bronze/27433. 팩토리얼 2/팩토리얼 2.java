import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long[] memorization = new long[21];

    private static long getFactorial(int n) {
        if (memorization[n] > 0) {
            return memorization[n];
        }

        return memorization[n] = getFactorial(n - 1) * n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        memorization[0] = 1;
        memorization[1] = 1;

        System.out.println(getFactorial(n));
    }
}
