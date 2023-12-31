import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.offer(Long.parseLong(br.readLine()));
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        long answer = 0;

        while (pq.size() > 1) {
            long num1 = pq.poll();
            long num2 = pq.poll();

            long sum = num1 + num2;
            answer += sum;
            pq.offer(sum);
        }

        System.out.println(answer);
    }
}