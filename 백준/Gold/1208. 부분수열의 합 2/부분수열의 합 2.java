import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static int n, s;
    private static int[] arr;
    private static ArrayList<Integer> leftSum = new ArrayList<>();
    private static ArrayList<Integer> rightSum = new ArrayList<>();

    private static void solve(int start, int end, int sum, ArrayList<Integer> list) {
        if (start == end) {
            list.add(sum);
            return;
        }
        solve(start + 1, end, sum, list);
        solve(start + 1, end, sum + arr[start], list);
    }

    private static int lowerBound(ArrayList<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static int upperBound(ArrayList<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열을 반으로 나누어 각각에 대한 부분수열의 합을 구한다.
        solve(0, n / 2, 0, leftSum);
        solve(n / 2, n, 0, rightSum);

        // 이분 탐색을 위한 정렬
        Collections.sort(leftSum);

        long count = 0;
        for (int sum : rightSum) {
            // 왼쪽 리스트에서 s-sum 값을 만족하는 범위를 찾는다.
            count += upperBound(leftSum, s - sum) - lowerBound(leftSum, s - sum);
        }

        // S가 0인 경우, 빈 수열도 포함되므로 1을 빼준다.
        if (s == 0) {
            count--;
        }

        System.out.print(count);
    }
}

