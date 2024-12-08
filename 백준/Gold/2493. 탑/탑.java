import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static class Tower {
        private final int idx;
        private final int height;

        private Tower(final int idx, final int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<Tower> deque = new ArrayDeque<>();


        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());
            while (!deque.isEmpty()) {
                Tower lastTower = deque.peek();
                // 지난 타워의 높이가 더 높거나 크다면 (현 위치 보다 왼쪽에 있는 타워)
                if (lastTower.height >= height) {
                    bw.write(lastTower.idx + " ");
                    break;
                }
                deque.pop();
            }
            if (deque.isEmpty()) {
                bw.write("0 ");
            }
            deque.push(new Tower(i, height));
        }

        bw.flush();
        bw.close();
    }
}