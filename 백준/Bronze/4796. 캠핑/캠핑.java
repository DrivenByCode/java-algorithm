import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = 1;
        while (true) {
            String input = br.readLine();

            if (input.equals("0 0 0")) {
                break;
            }

            st = new StringTokenizer(input);

            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int fullPeriods = v / p;
            int remainingDays = v % p;

            int answer = (fullPeriods * l) + Math.min(remainingDays, l);

            System.out.println("Case " + testCase + ": " + answer);

            testCase++;
        }
    }
}
