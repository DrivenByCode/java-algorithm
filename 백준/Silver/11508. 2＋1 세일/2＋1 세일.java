import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 내림 차순 정렬
        int[] sortedArray = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        long minCost = sortedArray[0];
        for (int i = 1; i < n; i++) {
            if (i % 3 != 2) { // 3의 배수, 인덱스는 0부터 시작하니 3n + 2 형태 즉, 3으로 나눠서 2가 아닌경우만 더 해줌.
                minCost += sortedArray[i];
            }
        }

        System.out.println(minCost);
    }
}
