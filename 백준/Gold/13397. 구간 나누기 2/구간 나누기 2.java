import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 13397 - 구간 나누기 2
// https://www.acmicpc.net/problem/13397

public class Main {
    private static boolean canDivide(int[] arr, int maxScore, int m) {
        int count = 1;
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            // maxScore를 넘으면 그 구간을 새로이 다시 시작하는 의미로 count를 1 증가 시킴
            if (max - min > maxScore) {
                count++;
                min = arr[i];
                max = arr[i];
            }
        }

        return count <= m;
    }

    private static int getMaxValue(int[] arr, int m) {
        int left = 0;
        int right = Arrays.stream(arr).max().orElse(0);

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canDivide(arr, mid, m)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMaxValue(arr, m));
    }
}