import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long low = 1;
        long high = k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            long count = 0;
            for (int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }

            if (k > count) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println(low);
    }
}
