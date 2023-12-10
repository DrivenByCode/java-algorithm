import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        int blueBlocks = 0;
        int redBlocks = 0;
        char lastColor = ' ';

        for (int i = 0; i < n; i++) {
            char currentColor = input.charAt(i);
            // 이전 컬러와 현재 컬러가 다른 곳의 갯수를 셈
            if (currentColor != lastColor) {
                if (currentColor == 'B') {
                    blueBlocks++;
                } else {
                    redBlocks++;
                }
            }
            lastColor = currentColor;
        }

        int answer = Math.min(blueBlocks, redBlocks) + 1;
        System.out.println(answer);
    }
}
