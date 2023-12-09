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

        for (int i = l; i < 101; i++) {
            long lt = 0;
            long rt = n + 1;

            while (lt < rt) {
                long mid = (lt + rt) / 2;
                long sum = ((mid + mid + i - 1) * i) / 2;

                if (sum > n) {
                    rt = mid;
                } else {
                    lt = mid + 1;
                    if (sum == n) {
                        answer = IntStream.range((int) mid, (int) mid + i).toArray();
                        System.out.println(Arrays.toString(answer).replaceAll("[\\[\\],]", ""));
                        return;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(answer).replaceAll("[\\[\\],]", ""));
    }
}