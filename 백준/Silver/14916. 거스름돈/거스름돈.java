import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static int getCoinsCount(int n) {
        int answer = 0;

        while (n > 0) {
            if (n >= 5) {
                while (n % 5 == 0) {
                    answer += n / 5;
                    return answer;
                }
                int tmp = n;
                int cnt = 0;
                while (tmp >= 2) {
                    // 13, 17, 19 등
                    tmp -= 5;
                    cnt++;
                    if (0 < tmp && tmp < 10 && tmp % 2 == 0) {
                        cnt += (tmp / 2);
                        return cnt;
                    }
                }
                if (n % 2 == 0) {
                    return n / 2;
                }
            } else if (n >= 2) {
                n -= 2;
                answer++;
            } else {
                return -1;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(getCoinsCount(n));
    }
}
