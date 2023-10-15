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
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int dist = y - x;
            int maxSpeed = (int) Math.sqrt(dist); // 최대 속도

            if (maxSpeed * maxSpeed == dist) {
                // 거리가 4, 9, 16 .. 일 때
                System.out.println(2 * maxSpeed - 1);
            } else if (dist <= maxSpeed * maxSpeed + maxSpeed) {
                // 거리가 5,6, 10, 11, 12 ...  일 때
                System.out.println(2 * maxSpeed);
            } else {
                // 거리가 최대 속도의 제곱과 최대 속도의 합 보다 크거나 최대 속도의 제곱보다 거리가 작은 경우
                // 거리가 13 일 때
                // 거리가 8일 때
                System.out.println(2 * maxSpeed + 1);
            }
        }
    }
}
