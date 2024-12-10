import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static String[] inputs;
    private static String[] selected;
    private static int c, l;

    private static void combination(int level, int start) {
        if (level == l) {
            if (isValid()) { // 최소 한 개의 모음과 두개의 자음 조건을 충족하는지 여부 확인
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < l; i++) {
                    sb.append(selected[i]);
                }
                System.out.println(sb);
            }
            return;
        }

        for (int i = start; i < c; i++) {
            selected[level] = inputs[i];
            combination(level + 1, i + 1);
        }
    }

    private static boolean isValid() {
        int vowelCount = 0;
        int consonantCount = 0;

        for (String s : selected) {
            if (isVowel(s.charAt(0))) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        // 최소 1개의 모음과 최소 2개의 자음이 있어야 조건 만족
        return vowelCount >= 1 && consonantCount >= 2;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken()); // 뽑을 숫자 갯수
        c = Integer.parseInt(st.nextToken()); // 전체 갯수

        inputs = br.readLine().split(" ");
        selected = new String[l];

        Arrays.sort(inputs);

        combination(0, 0);
    }
}
