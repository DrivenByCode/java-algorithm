import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static long target;
    private static boolean[] visited = new boolean[(int) 1e9 + 1];

    private static int bfs(long number) {
        Queue<Long> queue = new LinkedList<>();
        queue.offer(number);
        visited[(int) number] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                long num = queue.poll();

                if (num == target) {
                    return count + 1;
                }

                if (num * 2 <= target && !visited[(int) (num * 2)]) {
                    visited[(int) (num * 2)] = true;
                    queue.offer(num * 2);
                }
                if (num * 10 + 1 <= target && !visited[(int) (num * 10 + 1)]) {
                    visited[(int) (num * 10 + 1)] = true;
                    queue.offer(num * 10 + 1);
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        target = Long.parseLong(st.nextToken());

        System.out.println(bfs(a));
    }
}
