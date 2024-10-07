import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 팰린드롬 상태를 체크하는 함수
    public static int checkPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                if (isPseudoPalindrome(word, left + 1, right) || isPseudoPalindrome(word, left, right - 1)) {
                    return 1;
                } else {
                    return 2;
                }
            }
            left++;
            right--;
        }
        return 0;
    }

    // 유사 팰린드롬인지 체크하는 함수
    public static boolean isPseudoPalindrome(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            System.out.println(checkPalindrome(word));
        }
    }
}