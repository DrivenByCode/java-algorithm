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
                boolean findBombWord = true;
                for (int j = 0; j < bombWord.length(); j++) {
                    if (stack.get(stack.size() - bombWord.length() + j) != bombWord.charAt(j)) {
                        findBombWord = false;
                        break;
                    }
                }

                if (findBombWord) {
                    for (int j = 0; j < bombWord.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if (stack.isEmpty()) {
            System.out.println("FRULA");
            return;
        }

        for (char c : stack) {
            sb.append(c);
        }

        System.out.println(sb);

    }
}