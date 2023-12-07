import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int count;
    private static int n, s;

    private static void calculateSubsequenceSum(int index, int sum) {
        if (index == n) {
            if (sum == s) {
                count++;
            }
            return;
        }
        calculateSubsequenceSum(index + 1, sum + arr[index]);
        calculateSubsequenceSum(index + 1, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        calculateSubsequenceSum(0, 0);

        // 공집합의 합일 경우 count 하나 제거
        if (s == 0) {
            count--;
        }

        System.out.println(count);
    }
}