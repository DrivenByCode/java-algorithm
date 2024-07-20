import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(input.charAt(i) - '0'));
            if (i == 0) {
                sb.append(binaryString); // 첫 번째 숫자는 앞의 0을 제거하고 추가
                continue;
            }

            while (binaryString.length() < 3) {
                binaryString.insert(0, "0"); // 나머지 숫자는 3자리로 맞추기
            }
            sb.append(binaryString);
        }

        System.out.println(sb);
    }
}