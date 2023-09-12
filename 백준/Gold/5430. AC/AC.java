import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        Deque<Integer> stk = new ArrayDeque<>();
        while (t-- > 0) {
            // 함수 입력
            String func = br.readLine();
            boolean isReversed = false;
            boolean errorFlag = false;
            // 몇번 지우는지 카운트
            int deleteCount = 0;
            int n = Integer.parseInt(br.readLine());
            // 원소 전처리 1
            String tmp = br.readLine().replaceAll("[\\[\\]]", "");
            // 원소 쉼표 delimiter로  분리
            st = new StringTokenizer(tmp, ",");
            stk.clear();

            // 양방향 큐 (deque)에 원소 넣기
            for (int i = 0; i < n; i++) {
                stk.offer(Integer.parseInt(st.nextToken()));
            }

            StringBuilder sb = new StringBuilder();

            // 뒤집는 명령과 삭제 명령 처리
            for (char c : func.toCharArray()) {
                if (c == 'R') {
                    isReversed = !isReversed;
                } else {
                    // 역방향일 때.
                    if (isReversed) {
                        Integer pollResult = stk.pollLast();
                        if (pollResult == null) {
                            // 비어있는데 원소를 삭제(D)하면 에러 표시
                            sb.append("error");
                            errorFlag = true;
                            break;
                        }
                    } else {
                        Integer pollResult = stk.poll();
                        if (pollResult == null) {
                            // 비어있는데 원소를 삭제(D)하면 에러 표시
                            sb.append("error");
                            errorFlag = true;
                            break;
                        }
                    }

                }
            }

            // 역방향일 때.
            if (isReversed && !errorFlag) {
                while (!stk.isEmpty()) {
                    sb.append(stk.pollLast());
                    // 마지막 원소가 아닐 때만 쉼표 넣기
                    if (stk.size() >= 1) {
                        sb.append(",");
                    }
                }
                // 정방향일 때
            } else if (!isReversed && !errorFlag) {
                while (!stk.isEmpty()) {
                    sb.append(stk.poll());
                    // 마지막 원소가 아닐 때만 쉼표 넣기
                    if (stk.size() >= 1) {
                        sb.append(",");
                    }
                }
            }

            // error가 아닌 경우
            if (!errorFlag) {
                sb.insert(0, "[");
                sb.append(']');
                System.out.println(sb);
            } else {
                // error인 경우 error 출력
                System.out.println(sb);
            }
        }
    }
}