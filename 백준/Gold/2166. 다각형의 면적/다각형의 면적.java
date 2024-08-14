import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class Main {
    private static int[][] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        points = new int[n][2];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i][0] = x;
            points[i][1] = y;
        }

        // Shoelace 공식에 따라 넓이 계산
        BigInteger area = BigInteger.ZERO;

        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            int x2 = points[(i + 1) % n][0];
            int y2 = points[(i + 1) % n][1];

            BigInteger term1 = BigInteger.valueOf(x1).multiply(BigInteger.valueOf(y2));
            BigInteger term2 = BigInteger.valueOf(y1).multiply(BigInteger.valueOf(x2));

            area = area.add(term1).subtract(term2);
        }

        // 절대값을 취함
        area = area.abs();

        // BigDecimal로 변환하여 소수점 계산
        // 10은 정밀도, 그 이후는 반올림 처리를 나타냄
        BigDecimal finalArea = new BigDecimal(area).divide(BigDecimal.valueOf(2), 3, RoundingMode.HALF_UP);

        // 소수점 첫째 자리까지 출력
        System.out.printf("%.1f", finalArea);
    }
}
