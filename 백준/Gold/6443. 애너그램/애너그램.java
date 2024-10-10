import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static char[] selected;
    private static boolean[] visited;

    private static void getAnagram(char[] word, int level, StringBuilder sb) {
        int charLen = word.length;
        if (level == charLen) {
            sb.append(selected).append("\n");
            return;
        }

        char lastChar = '\0'; // 마지막에 사용한 문자 추적
        for (int i = 0; i < charLen; i++) {
            if (!visited[i] && word[i] != lastChar) {
                visited[i] = true;
                selected[level] = word[i];
                getAnagram(word, level + 1, sb);
                visited[i] = false;
                lastChar = word[i]; // 현재 문자 기록
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            char[] sortedWord = word.toCharArray();
            Arrays.sort(sortedWord);

            int len = sortedWord.length;
            visited = new boolean[len];
            selected = new char[len];
            getAnagram(sortedWord, 0, sb);
        }

        System.out.print(sb.toString().trim());
    }
}
