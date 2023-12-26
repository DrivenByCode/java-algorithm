import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String words = br.readLine();
        String bombWord = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < words.length(); i++) {
            stack.push(words.charAt(i));

            if (stack.size() >= bombWord.length()) {
                boolean foundBomb = true;
                for (int j = 0; j < bombWord.length(); j++) {
                    if (stack.get(stack.size() - bombWord.length() + j) != bombWord.charAt(j)) {
                        foundBomb = false;
                        break;
                    }
                }

                if (foundBomb) {
                    for (int j = 0; j < bombWord.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }

        String result = sb.toString();
        if (result.isEmpty()) {
            System.out.println("FRULA");
        } else {
            System.out.println(result);
        }
    }
}
