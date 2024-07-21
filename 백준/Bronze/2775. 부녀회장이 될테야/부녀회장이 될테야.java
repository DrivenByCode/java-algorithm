import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // test case 수

        int[][] floor = new int[15][15];

        for (int i = 0; i <= 14; i++) {
            floor[0][i] = i;
        }

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine()); // k층
            int n = Integer.parseInt(br.readLine()); // n호

            if (floor[k][n] > 0) {
                System.out.println(floor[k][n]);
                continue;
            }

            for (int j = 1; j <= k; j++) {
                for (int l = 1; l <= n; l++) {
                    if (floor[j][l] > 0) {
                        continue;
                    }
                    floor[j][l] = floor[j - 1][l] + floor[j][l - 1];
                }
            }

            System.out.println(floor[k][n]);
        }

    }
}
