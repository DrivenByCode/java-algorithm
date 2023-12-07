import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = 0;
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                // 5개의 원소가 있다고 하면
                // [0, 0, 0, 0, 0]
                // 여기서 0은 해당 숫자를 선택하지 않았음을, 1은 선택했음을 의미.
                // 따라서 (1 << j)와 (1 << j) 미만 숫자가 i와 비트 AND 연산시 겹치는 게 있다면(0이 아니라면)
                // 하나 이상을 선택했다는 의미
                // 이 방식으로 부분집합의 총 갯수를 구할 수 있다.
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }
            if (sum == s) {
                answer++;
            }
        }

        System.out.println(s == 0 ? answer - 1 : answer);
    }
}
