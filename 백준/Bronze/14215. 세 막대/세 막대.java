import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int a, b, c;

    private static boolean isValidTriangle() {
        return (a + b > c) && (b + c > a) && (a + c > b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        if (isValidTriangle()) {
            System.out.println(a + b + c);
            return;
        }

        while (!isValidTriangle()) {
            if (a + b <= c) {
                c--;
            } else if (b + c <= a) {
                a--;
            } else if (a + c <= b) {
                b--;
            }
        }

        System.out.println(a + b + c);
    }
}