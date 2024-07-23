import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 문제 수
        int l = Integer.parseInt(st.nextToken()); // 문제 난이도 합 최소치
        int r = Integer.parseInt(st.nextToken()); // 문제 난이도 합 최대치
        int x = Integer.parseInt(st.nextToken()); // 최대 난이도 - 최소난이도 차이

        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        for (int i = 0; i < (1 << n); i++) {
            int coveredProblem = 0;
            int cnt = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    coveredProblem += nums[j];
                    cnt++;
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                }
            }

            if ((cnt >= 2) && (max - min) >= x && (l <= coveredProblem) && (coveredProblem <= r)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
