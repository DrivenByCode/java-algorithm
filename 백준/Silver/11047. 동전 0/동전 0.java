import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] sortedArray = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        int minCount = 0;
        for (int num : sortedArray) {
            if (num <= k) {
                if (k % num == 0) {
                    minCount += (k / num);
                    k -= (k / num) * num;
                } else {
                    if (k / num > 0) {
                        minCount += (k / num);
                        k -= (k / num) * num;
                    }
                }
            }
        }

        System.out.println(minCount);
    }
}
