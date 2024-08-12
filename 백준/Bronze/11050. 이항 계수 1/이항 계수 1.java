import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 재귀적으로 이항계수를 계산하는 메서드
    public static int binomialCoefficient(int n, int k) {
        // 기본 조건
        if (k == 0 || k == n) {
            return 1;
        }
        // 재귀적 호출로 이항계수를 계산
        return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(binomialCoefficient(n, k));
    }
}
