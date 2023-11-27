import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }

        int answer = 0;

        // num은 뽑을 원소
        for (int num : arr) {
            int halfIndex;

            if (deque.size() % 2 == 0) {
                halfIndex = deque.size() / 2 - 1;
            } else {
                halfIndex = deque.size() / 2;
            }
            if (deque.indexOf(num) > halfIndex) {
                // 오른쪽으로 한 칸씩 목표 원소까지 이동 (맨 앞에 새로운 원소가 추가)
                while (deque.peekFirst() != num) {
                    answer++;
                    deque.offerFirst(deque.pollLast());
                }
                deque.pollFirst();
                continue;
            }

            // 왼쪽으로 한 칸씩 이동 (맨 뒤에 새로운 원소가 추가)
            
            while (deque.peekFirst() != num) {
                answer++;
                deque.offerLast(deque.pollFirst());
            }
            deque.pollFirst();
        }

        System.out.println(answer);
    }
}