import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_NUM = 1001;
    private static boolean[] isPrimeNumber = new boolean[MAX_NUM];

    private static void setIsPrimeNumber() {
        Arrays.fill(isPrimeNumber, true);
        isPrimeNumber[0] = isPrimeNumber[1] = false;

        for (int i = 2; i * i < MAX_NUM; i++) {
            if (isPrimeNumber[i]) {
                for (int j = i * i; j < MAX_NUM; j += i) {
                    isPrimeNumber[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        setIsPrimeNumber();

        int answer = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (isPrimeNumber[num]) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
