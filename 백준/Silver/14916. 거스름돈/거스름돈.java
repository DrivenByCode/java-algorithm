import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static int getMinCount(int n) {
        int answer = 0;

        if (n >= 5) {
            if (n % 5 == 0) {
                return n / 5;
            }
            int tmp = n;
            int cnt = 0;
            while (tmp >= 2) {
                tmp -= 5;
                cnt++;
                // 11, 12, 13, 14, 16, 17,18, 19 등 여기 해당
                if (0 < tmp && tmp < 10 && tmp % 2 == 0) {
                    cnt += (tmp / 2);
                    return cnt;
                }
            }
            if (n % 2 == 0) {
                return n / 2;
            }
        }
        if (n % 2 == 0) {
            return n / 2;
        }
        
        return answer == 0 ? -1 : answer;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(getMinCount(n));
    }
}
