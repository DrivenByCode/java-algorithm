import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Jewel implements Comparable<Jewel> {
        private final int weight;
        private final int value;

        private Jewel(final int weight, final int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel other) {
            return this.weight - other.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewel> jewels = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels.offer(new Jewel(weight, value));
        }

        List<Integer> bags = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);

        PriorityQueue<Integer> availableJewels = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;

        for (int bag : bags) {
            while (!jewels.isEmpty() && jewels.peek().weight <= bag) {
                availableJewels.offer(jewels.poll().value);
            }

            if (!availableJewels.isEmpty()) {
                answer += availableJewels.poll();
            }
        }

        System.out.println(answer);
    }
}

// 반례 1 : 정답 1100
//4 4
//1 100
//2 200
//13 300
//10 500
//10
//10
//10
//14

// 반례 2 : 정답 183
//9 5
//4 5
//4 9
//4 10
//8 55
//14 20
//9 15
//8 55
//8 5
//11 54
//10
//5
//4
//15
//20