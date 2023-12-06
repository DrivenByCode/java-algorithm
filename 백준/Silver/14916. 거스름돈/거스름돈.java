import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int getMinCount(int n) {
        if (n >= 5) {
            if (n % 5 == 0) {
                return n / 5;
            }
            int tmp = n;
            int cnt = 0;
            while (tmp >= 2) {
                tmp -= 5;
                cnt++;
                if (0 < tmp && tmp < 10 && tmp % 2 == 0) {
                    cnt += (tmp / 2);
                    return cnt;
                }
            }
        }
        if (n % 2 == 0) {
            return n / 2;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(getMinCount(n));
    }
}
