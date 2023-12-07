import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infixExpression = br.readLine().trim();
        String postfixExpression = convertInfixToPostfix(infixExpression);
        System.out.println(postfixExpression);
    }

    private static String convertInfixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (char currentChar : infix.toCharArray()) {
            if (Character.isLetter(currentChar)) {
                postfix.append(currentChar);
            } else if (isOperator(currentChar)) {
                while (!stack.isEmpty() && stack.peekFirst() != '(' && hasHigherPrecedence(stack.peekFirst(), currentChar)) {
                    postfix.append(stack.pollFirst());
                }
                stack.offerFirst(currentChar);
            } else if (currentChar == '(') {
                stack.offerFirst(currentChar);
            } else if (currentChar == ')') {
                while (!stack.isEmpty() && stack.peekFirst() != '(') {
                    postfix.append(stack.pollFirst());
                }
                stack.pollFirst(); // 여는 괄호 '(' 제거
            }

        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pollFirst());
        }

        return postfix.toString();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        if (precedence(op1) >= precedence(op2)) {
            return true;
        }
        return false;
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1; // 덧셈과 뺄셈의 우선순위는 1
            case '*':
            case '/':
                return 2; // 곱셈과 나눗셈의 우선순위는 2
            default:
                return 0; // 알 수 없는 연산자인 경우 기본값
        }
    }

}
