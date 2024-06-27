import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long minDiff = Long.MAX_VALUE;
        int[] answer = new int[3];

        // 투 포인터로 두 값을 고정한다.
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                long twoSum = arr[i] + arr[j];

                // 이분탐색으로 세번째 값 찾기
                int left = j + 1;
                int right = n - 1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    long currentSum = twoSum + arr[mid];
                    long currentDiff = Math.abs(currentSum);

                    if (currentDiff < minDiff) {
                        minDiff = currentDiff;
                        answer[0] = arr[i];
                        answer[1] = arr[j];
                        answer[2] = arr[mid];
                    }

                    if (currentSum < 0) {
                        left = mid + 1;
                    } else if (currentSum > 0) {
                        right = mid - 1;
                    } else {
                        // 정답인 경우 중 하나이므로 answer 갱신
                        answer[0] = arr[i];
                        answer[1] = arr[j];
                        answer[2] = arr[mid];
                        break;
                    }
                }
            }
        }

        // 정답 추출
        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}

