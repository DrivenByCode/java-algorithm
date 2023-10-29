import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = 1;
            a %= 10; // 마지막 숫자만 사용, 예를 들어 4의 거듭 제곱은 1의 자리가 4, 6, 4, 6 반복됨.

            for (int j = 0; j < b; j++) {
                result = (result * a) % 10;
            }

            if (result == 0) {
                System.out.println(10);
            } else {
                System.out.println(result);
            }
        }
    }
}
