import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        int aLength = a.length();
        int bLength = b.length();

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= bLength - aLength; i++) {
            int count = 0;

            for (int j = 0; j < aLength; j++) {
                if (a.charAt(j) != b.charAt(j + i)) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        }

        System.out.println(answer);
    }
}
