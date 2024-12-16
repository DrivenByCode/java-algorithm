import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            String input = br.readLine();
            String[] command = input.split(" ");
            if(command[0].equals("push_back")) {
                deque.offerLast(Integer.parseInt(command[1]));
            } else if(command[0].equals("push_front")) {
                deque.offerFirst(Integer.parseInt(command[1]));
            } else  if(command[0].equals("pop_front")) {
                Integer one = deque.pollFirst();
                if(Objects.isNull(one)) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(one);
            } else if(command[0].equals("pop_back")) {
                Integer one = deque.pollLast();
                if(Objects.isNull(one)) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(one);
            } else if(command[0].equals("size")) {
                System.out.println(deque.size());
            } else if(command[0].equals("empty")) {
                System.out.println(deque.isEmpty() ? 1 : 0);
            } else if(command[0].equals("front")) {
                if(deque.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(deque.peekFirst());
            } else if(command[0].equals("back")) {
                if(deque.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(deque.peekLast());
            }
        }
    }
}
