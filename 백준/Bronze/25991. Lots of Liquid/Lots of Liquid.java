import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        double sums = 0d;

        for (int i = 0; i < n; i++) {
            sums += Math.pow(Double.parseDouble(st.nextToken()), 3);
        }

        System.out.println(Math.cbrt(sums));
    }
}