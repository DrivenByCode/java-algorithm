import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        String number = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char current = number.charAt(i);

            // 스택이 비어있지 않고, 스택의 최상단 값이 현재 값보다 작고, 제거할 기회가 남아있을 때
            while (!stack.isEmpty() && stack.peek() < current && k > 0) {
                stack.pop(); // 스택의 작은 값 제거
                k--;  // 제거한 횟수 감소
            }
            stack.push(current); // 현재 숫자를 스택에 넣음
        }

        // 만약 제거할 기회가 남아 있다면, 뒤에서부터 더 제거해야 함
        while (k > 0) {
            stack.pop();
            k--;
        }

        // 스택을 이용해 남은 숫자들을 출력
        StringBuilder answer = new StringBuilder();
        for (char c : stack) {
            answer.append(c);
        }

        System.out.println(answer);
    }
}
