import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int coefficient = Integer.parseInt(st.nextToken());
        int intercept = Integer.parseInt(st.nextToken());

        int c = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());

        boolean condition1 = (coefficient <= c); // 기울기 비교
        boolean condition2 = (coefficient * n + intercept <= n * c); // n에서의 함수 값 비교

        if (condition1 && condition2) {
            System.out.println(1);
            return;
        }

        System.out.println(0);
    }
}