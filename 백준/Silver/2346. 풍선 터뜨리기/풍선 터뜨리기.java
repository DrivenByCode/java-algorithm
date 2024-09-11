import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> balloons = new ArrayList<>();
        List<Integer> moves = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            balloons.add(i + 1);  // 풍선 번호 저장
            moves.add(Integer.parseInt(st.nextToken()));  // 각 풍선의 이동 값 저장
        }

        StringBuilder result = new StringBuilder();
        int index = 0;

        while (!balloons.isEmpty()) {
            int currentMove = moves.remove(index);
            result.append(balloons.remove(index)).append(" ");

            if (balloons.isEmpty()) break;  // 모든 풍선이 터지면 종료

            if (currentMove > 0) {
                index = (index + currentMove - 1) % balloons.size();  // 시계방향 이동
            } else {
                index = (index + currentMove) % balloons.size();  // 반시계방향 이동
                if (index < 0) {
                    index += balloons.size();  // 음수 인덱스 처리
                }
            }
        }

        System.out.println(result.toString().trim());
    }
}
