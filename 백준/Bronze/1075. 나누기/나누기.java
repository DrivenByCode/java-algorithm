import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int f = Integer.parseInt(br.readLine());

        // N의 마지막 두 자리를 0으로 만들기
        n = (n / 100) * 100;

        // N을 F로 나누어 떨어지게 만드는 가장 작은 수 찾기
        while (n % f != 0) {
            n++;
        }

        // 결과 출력 (마지막 두 자리만 출력)
        System.out.printf("%02d", n % 100);
    }
}