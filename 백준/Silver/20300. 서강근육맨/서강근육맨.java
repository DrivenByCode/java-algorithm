import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] t = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();

        Long max = Long.MIN_VALUE;

        Deque<Long> muscleLosses = new ArrayDeque<>();

        for (long one : t) {
            muscleLosses.offer(one);
        }

        if (n % 2 == 1) {
            max = muscleLosses.pollLast();
        }

        while (!muscleLosses.isEmpty()) {
            max = Math.max(max, muscleLosses.pollFirst() + muscleLosses.pollLast());
        }

        System.out.println(max);
    }
}