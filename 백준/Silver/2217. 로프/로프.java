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

        // 내림 차순 정렬 후 임의의 로프를 선택하여 들을 수 있는 최대의 무게를 구함
        int[] sotredArr = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        int maxWeight = 0;
        int cnt = 1;
        for (int i = 0; i < n; i++) {
            // 몇개를 선택 하든 가장 처음에 선택한 로프가 감당할 수 있는 무게 * 선택한 로프의 개수가 최대 무게
            int weight = sotredArr[i] * (i + 1);
            maxWeight = Math.max(maxWeight, weight);
        }

        System.out.println(maxWeight);
    }
}
