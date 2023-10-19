import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    private static int cnt;
    private static int n;

    private static void hanoi(int level, int from, int temp, int to) {
        if (level == n) return;
        // 첫 번째 장대에서 두번쨰 장대로 옮김
        hanoi(level + 1, from, to, temp);
        sb.append(from).append(" ").append(to).append("\n");
        cnt++;
        // 두 번째 장대에서 세번쨰 장대로 옮김
        hanoi(level + 1, temp, from, to);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 첫 번째 장대에 쌓인 원판의 수
        hanoi(0, 1, 2, 3);
        System.out.println(cnt);
        System.out.println(sb);
    }
}
