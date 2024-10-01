import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 심사대의 수
        int m = Integer.parseInt(st.nextToken());  // 사람 수

        long[] times = new long[n];
        long maxTime = 0;  // 가장 오래 걸리는 심사대의 시간

        for (int i = 0; i < n; i++) {
            times[i] = Long.parseLong(br.readLine());
            maxTime = Math.max(maxTime, times[i]);  // 가장 긴 심사 시간을 찾음
        }

        // 이분탐색 범위 설정 (최소 1분부터 최대 maxTime * m분)
        long left = 1;
        long right = maxTime * m - 1;

        while (left <= right) {
            long mid = (left + right) / 2;  // 현재 중간 시간(mid)

            long totalPeople = 0;  // mid 시간 동안 처리할 수 있는 사람 수

            for (int i = 0; i < n; i++) {
                totalPeople += mid / times[i];  // 각 심사대가 mid 시간에 처리할 수 있는 사람 수
                if (totalPeople >= m) break;  // 이미 m명을 넘으면 더 이상 계산할 필요 없음
            }

            // mid 시간에 m명을 처리할 수 있는 경우, 시간을 줄여서 더 최소 시간을 찾음
            if (totalPeople >= m) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);  // 최소 시간을 출력
    }
}
