import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int floor = n % h;
            int roomNumber = 0;
            if (floor > 0) {
                roomNumber = n / h + 1;
            } else {
                floor += h;
                roomNumber = n / h;
            }

            System.out.println(String.valueOf(floor) + String.format("%02d", roomNumber));
        }
    }
}
