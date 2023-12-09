import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] answer = {-1};

        for (int len = l; len < 101; len++) {
            long lt = 0;
            long rt = n / len + 1;

            while (lt < rt) {
                long mid = (lt + rt) / 2;
                long sum = ((mid + mid + len - 1) * len) / 2;

                if (sum > n) {
                    rt = mid;
                } else {
                    if (sum == n) {
                        answer = IntStream.range((int) mid, (int) mid + len).toArray();
                        System.out.println(Arrays.toString(answer).replaceAll("[\\[\\],]", ""));
                        return;
                    }
                    lt = mid + 1;
                }
            }
        }

        System.out.println(Arrays.toString(answer).replaceAll("[\\[\\],]", ""));
    }
}

