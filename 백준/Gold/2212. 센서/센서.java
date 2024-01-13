import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 센서의 개수
        int k = Integer.parseInt(br.readLine()); // 집중국의 개수

        if (k >= n) {
            System.out.println(0);
            return;
        }

        String[] input = br.readLine().split(" ");
        int[] sensors = new int[n];
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(sensors); // 센서 위치를 오름차순으로 정렬

        int[] distances = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            distances[i] = sensors[i + 1] - sensors[i];
        }

        distances = Arrays.stream(distances).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        int sum = 0;
        for (int i = k - 1; i < n - 1; i++) {
            sum += distances[i];
        }

        System.out.println(sum); // 최소 수신 가능 영역의 합 출력
    }
}
