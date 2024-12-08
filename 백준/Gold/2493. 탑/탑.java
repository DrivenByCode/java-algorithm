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
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && deque.peek().height < height) {
                deque.poll(); // 지금 타워보다 더 높이가 낮은 타워는 제거
            }

            if (deque.isEmpty()) {
                sb.append("0 ");
            } else {
                sb.append(deque.peek().idx).append(" ");
            }

            deque.offerFirst(new Tower(i, height));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}