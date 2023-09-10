import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static int getParenthesisType(String str) {
        int result = 0;
        int sum = 1;

        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            // 여는 괄호일 때 처리
            if (c == '(' || c == '[') {
                stk.push(c);
                if (c == '(') {
                    sum *= 2;
                } else {
                    sum *= 3;
                }
            } 
            // 스택이 비었을 때 처리 (올바르지 않은 괄호열)
            else if (stk.isEmpty()) {
                return 0;
            } 
            // 닫는 괄호일 때 처리
            else {
                // 닫는 괄호가 ')' 일 때
                if (c == ')') {
                    // 스택의 최상단이 '('가 아니면 올바르지 않은 괄호열
                    if (stk.peek() != '(') return 0;
                    // 바로 앞 문자가 '('이면 현재 sum 값을 result에 더함
                    if (str.charAt(i - 1) == '(') {
                        result += sum;
                    }
                    stk.pop();
                    sum /= 2;
                } 
                // 닫는 괄호가 ']' 일 때
                else if (c == ']') {
                    // 스택의 최상단이 '['가 아니면 올바르지 않은 괄호열
                    if (stk.peek() != '[') return 0;
                    // 바로 앞 문자가 '['이면 현재 sum 값을 result에 더함
                    if (str.charAt(i - 1) == '[') {
                        result += sum;
                    }
                    stk.pop();
                    sum /= 3;
                }
            }
        }

        // 스택이 비어있지 않으면 올바르지 않은 괄호열
        if (!stk.isEmpty()) {
            return 0;
        } else {
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(getParenthesisType(str));
    }
}
