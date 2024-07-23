import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 문제 수
        int m = Integer.parseInt(st.nextToken()); // 팀원 수

        // 문제 비트마스크 배열
        int[] problems = new int[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());

            for (int j = 0; j < o; j++) {
                int problem = Integer.parseInt(st.nextToken()) - 1;
                // 문제 번호에 해당하는 비트를 켜서 해당 팀원이 해결 가능한 문제를 비트마스크로 표시
                problems[i] |= (1 << problem);
            }
        }

        int answer = Integer.MAX_VALUE;

        // 모든 팀원 조합을 비트마스크로 생성
        for (int i = 1; i < (1 << m); i++) {
            int coveredProblems = 0;
            int teamSize = 0;

            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    coveredProblems |= problems[j];
                    teamSize++;
                }
            }

            // 모든 문제를 커버하는지 확인
            if (coveredProblems == (1 << n) - 1) {
                answer = Math.min(answer, teamSize);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
