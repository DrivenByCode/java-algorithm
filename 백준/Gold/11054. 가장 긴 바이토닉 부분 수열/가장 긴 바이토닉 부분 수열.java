import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] increasingDp = new int[n];
        int[] decreasingDp = new int[n];

        for (int i = 0; i < n; i++) {
            increasingDp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && increasingDp[i] < increasingDp[j] + 1) {
                    increasingDp[i] = increasingDp[j] + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            decreasingDp[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[i] && decreasingDp[i] < decreasingDp[j] + 1) {
                    decreasingDp[i] = decreasingDp[j] + 1;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(increasingDp[i] + decreasingDp[i], max);
        }

        // 겹치는 것 하나 제거. 기준 index 기준 하나의 값이 겹침.
        System.out.println(max - 1);
    }
}