import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 한 변에 찍힌 점의 수 계산
        int pointsOnSide = (int) Math.pow(2, n) + 1;

        // 전체 점의 수 계산 (한 변의 점의 수의 제곱)
        int result = pointsOnSide * pointsOnSide;

        // 결과 출력
        System.out.println(result);
    }
}
