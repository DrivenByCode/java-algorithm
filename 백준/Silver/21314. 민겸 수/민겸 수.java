import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder maxAnswer = new StringBuilder();
        StringBuilder minAnswer = new StringBuilder();

        int countM = 0;
        for (char ch : input.toCharArray()) {
            if (ch == 'M') {
                countM++;
            } else if (ch == 'K') {
                // 최대값 계산: M이 연속된 후 K가 나오면, 5 다음에 0을 countM만큼 추가
                maxAnswer.append("5");
                maxAnswer.append("0".repeat(countM));
                // 최소값 계산: K가 나오면, M개수에 따라 1 + 0(M의 갯수)를 추가
                if (countM >= 1) {
                    minAnswer.append("1");
                    minAnswer.append("0".repeat(countM - 1));
                }
                // 최소값 계산: K가 나오면, 5를 추가
                minAnswer.append("5");
                countM = 0;
            }
        }

        // 처리되지 않은 마지막 M 시퀀스 처리
        if (countM > 0) {
            // 최대값 계산: 마지막에 남은 M은 모두 1로 변환
            maxAnswer.append("1".repeat(countM));
            // 최소값 계산: 마지막에 남은 M은 1 다음에 0을 countM-1만큼 추가
            minAnswer.append("1");
            minAnswer.append("0".repeat(countM - 1));
        }

        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }
}
