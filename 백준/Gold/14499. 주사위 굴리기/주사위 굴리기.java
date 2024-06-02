import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] moves = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private static int[] dice = new int[6]; // 윗면, 북면, 동면, 서면, 남면, 바닥면
    private static int n, m, x, y;
    private static int[][] map;

    private static void rollDice(int direction) {
        int[] temp = dice.clone();

        switch (direction) {
            case 0: // 동쪽으로 굴리기
                dice[0] = temp[3];
                dice[2] = temp[0];
                dice[3] = temp[5];
                dice[5] = temp[2];
                break;
            case 1: // 서쪽으로 굴리기
                dice[0] = temp[2];
                dice[2] = temp[5];
                dice[3] = temp[0];
                dice[5] = temp[3];
                break;
            case 2: // 북쪽으로 굴리기
                dice[0] = temp[4];
                dice[1] = temp[0];
                dice[4] = temp[5];
                dice[5] = temp[1];
                break;
            case 3: // 남쪽으로 굴리기
                dice[0] = temp[1];
                dice[1] = temp[5];
                dice[4] = temp[0];
                dice[5] = temp[4];
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
        m = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
        x = Integer.parseInt(st.nextToken()); // 주사위 놓은 곳의 좌표 x
        y = Integer.parseInt(st.nextToken()); // 주사위 놓은 곳의 좌표 y
        int k = Integer.parseInt(st.nextToken()); // 명령의 갯수 K

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int command = Integer.parseInt(st.nextToken()) - 1; // 명령을 0부터 시작하는 인덱스로 맞추기 위해 -1

            int nx = x + moves[command][0];
            int ny = y + moves[command][1];

            // 지도를 벗어나는지 확인
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            // 주사위 굴리기
            rollDice(command);

            // 주사위 위치 갱신
            x = nx;
            y = ny;

            // 이동한 칸의 처리
            if (map[x][y] == 0) {
                map[x][y] = dice[5]; // 주사위의 바닥면 숫자를 지도에 복사
            } else {
                dice[5] = map[x][y]; // 지도의 숫자를 주사위의 바닥면에 복사
                map[x][y] = 0; // 지도 칸의 숫자를 0으로 만듦
            }

            // 주사위의 윗면 출력
            System.out.println(dice[0]);
        }
    }
}
