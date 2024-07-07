import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] gears;
    private static int[] pos; // 톱니바퀴의 12시 방향을 가리키는 인덱스

    private static void rotateGears(int gearIndex, int rotate) {
        int[] directions = new int[4];
        directions[gearIndex] = rotate;

        // 왼쪽 톱니바퀴들 회전 방향 결정
        for (int i = gearIndex; i > 0; i--) {
            if (gears[i][(pos[i] + 6) % 8] != gears[i - 1][(pos[i - 1] + 2) % 8]) {
                directions[i - 1] = -directions[i];
            } else {
                break;
            }
        }

        // 오른쪽 톱니바퀴들 회전 방향 결정
        for (int i = gearIndex; i < 3; i++) {
            if (gears[i][(pos[i] + 2) % 8] != gears[i + 1][(pos[i + 1] + 6) % 8]) {
                directions[i + 1] = -directions[i];
            } else {
                break;
            }
        }

        // 실제 회전 수행
        for (int i = 0; i < 4; i++) {
            if (directions[i] != 0) {
                pos[i] = (pos[i] - directions[i] + 8) % 8;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int MAX_GEAR_COUNT = 4;
        final int MAX_GEAR_INDEX = 8;
        gears = new int[MAX_GEAR_COUNT][MAX_GEAR_INDEX];
        pos = new int[MAX_GEAR_COUNT]; // 초기화

        for (int i = 0; i < MAX_GEAR_COUNT; i++) {
            String gear = br.readLine();
            for (int j = 0; j < MAX_GEAR_INDEX; j++) {
                gears[i][j] = gear.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int j = 0; j < k; j++) {
            st = new StringTokenizer(br.readLine());
            int gearIdx = Integer.parseInt(st.nextToken()) - 1;
            int rotate = Integer.parseInt(st.nextToken());
            rotateGears(gearIdx, rotate);
        }

        int scoreS = 1;
        int answer = 0;

        for (int i = 0; i < MAX_GEAR_COUNT; i++) {
            int check = gears[i][pos[i]];

            if (check == 1) {
                answer += scoreS;
            }
            scoreS *= 2;
        }

        System.out.println(answer);
    }
}