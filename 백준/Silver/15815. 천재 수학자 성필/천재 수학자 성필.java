import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static Deque<Integer> deque = new ArrayDeque<>();

    private static void calculate(char op) {
        int b = deque.pollFirst(); // 첫 번째 피연산자
        int a = deque.pollFirst(); // 두 번째 피연산자
        int answer = 0;

        switch (op) {
            case '+':
                answer = a + b;
                break;
            case '-':
                answer = a - b;
                break;
            case '*':
                answer = a * b;
                break;
            case '/':
                answer = a / b;
                break;
        }

        deque.offerFirst(answer);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                deque.offerFirst(c - '0');
            } else {
                calculate(c);
            }
        }

        System.out.println(deque.pollFirst());
    }
}
