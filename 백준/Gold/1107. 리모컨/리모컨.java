import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 숫자 버튼으로 해당 채널로 이동 가능한지 확인하는 함수
    // 가능하면 자리수 반환, 불가능하면 -1 반환
    public static int possible(int channel, boolean[] broken) {
        // 목표 채널이 0번이면
        if (channel == 0) {
            return broken[0] ? 0 : 1;  // 0번 버튼이 고장났으면 이동 불가, 아니면 1자리
        }

        int len = 0;  // 채널 자리수 계산
        while (channel > 0) {
            int digit = channel % 10;
            if (broken[digit]) {
                return 0;  // 고장난 버튼이 포함되어 있으면 이동 불가
            }
            len++;
            channel /= 10;
        }

        return len;  // 가능한 자리수 반환
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 목표 채널 N
        int n = Integer.parseInt(br.readLine());

        // 고장난 버튼의 개수
        int m = Integer.parseInt(br.readLine());

        // 고장난 버튼이 있을 경우 처리
        boolean[] broken = new boolean[10];  // 숫자 버튼이 고장났는지 여부를 체크하는 배열
        if (m > 0) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int num : arr) {
                broken[num] = true;  // 고장난 버튼을 true로 설정
            }
        }

        // 1. 단순히 +/- 버튼으로만 이동하는 경우 (100번 채널에서 시작)
        int minPress = Math.abs(n - 100);

        // 2. 숫자 버튼을 눌러서 목표 채널에 가까운 채널로 이동한 후, +/-로 조정하는 경우
        // 0부터 999,999까지 가능한 모든 채널에 대해 계산
        for (int channel = 0; channel <= 999999; channel++) {
            int len = possible(channel, broken);  // 해당 채널을 숫자로 이동할 수 있는지 확인

            if (len > 0) {  // 가능한 채널이면
                // 숫자 버튼으로 이동한 후, +/- 버튼으로 이동한 횟수 계산
                int pressCount = len + Math.abs(channel - n);
                minPress = Math.min(minPress, pressCount);
            }
        }

        // 결과 출력
        System.out.println(minPress);
    }
}
