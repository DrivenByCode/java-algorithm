import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static int isMakeWord(String s, String t) {
        boolean isReversed = false;
        Deque<Character> deque = new ArrayDeque<>();
        char[] chars = t.toCharArray();

        for (char c : chars) {
            deque.offer(c);
        }

        while (!deque.isEmpty()) {
            if (deque.size() == s.length()) {
                StringBuilder word = new StringBuilder(deque.toString().replaceAll("[\\[\\], ]", ""));
                if (isReversed) {
                    word = word.reverse();
                }
                if (word.toString().equals(s)) {
                    return 1;
                }
                return 0;
            }
            if (isReversed) {
                char c = deque.peek();
                if (c == 'B') {
                    isReversed = !isReversed;
                }
                deque.poll();
            } else {
                char c = deque.peekLast();
                if (c == 'B') {
                    isReversed = !isReversed;
                }
                deque.pollLast();
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        System.out.println(isMakeWord(s, t));
    }
}
