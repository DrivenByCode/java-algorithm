import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(Z(n, r, c));
        br.close();
    }

    private static int Z(int n, int r, int c) {
        if (n == 0) {
            return 0;
        }
        int half = 1 << (n - 1);

        // 2사분면
        if (r < half && c < half) {
            return Z(n - 1, r, c);
        }

        // 1사분면
        if (r < half && c >= half) {
            return half * half + Z(n - 1, r, c - half);
        }
        // 3사분면
        if (r >= half && c < half) {
            return 2 * half * half + Z(n - 1, r - half, c);
        }

        // 4사분면
        return 3 * half * half + Z(n - 1, r - half, c - half);
    }
}