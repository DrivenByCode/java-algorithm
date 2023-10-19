import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    private static int cnt;
    private static int n;

    private static void hanoi(int count, int from, int temp, int to) {
        if (count == 0) return;
        // 첫 번째 장대의 상위 count-1개의 원판을 두 번째 장대로 옮김
        hanoi(count - 1, from, to, temp);

        // 첫 번째 장대에 남아 있는 원판을 세 번째 장대로 옮김
        sb.append(from).append(" ").append(to).append("\n");
        cnt++;

        // 두 번째 장대의 level-1개의 원판을 세 번째 장대로 옮김
        hanoi(count - 1, temp, from, to);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 첫 번째 장대에 쌓인 원판의 수
        hanoi(n, 1, 2, 3);
        System.out.println(cnt);
        System.out.println(sb);
    }
}
