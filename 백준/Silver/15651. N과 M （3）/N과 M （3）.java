import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 중복 순열 <- 2 1 / 1 2는 다르게 취급
    static int n, m;
    static int[] selectedNumbers;
    static StringBuilder sb;

    private static void getPermutationWithRepetition(int level) {
        if (level == m) {
            for (int selectedNumber : selectedNumbers) {
                sb.append(selectedNumber).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= n; i++) {
            selectedNumbers[level] = i;
            getPermutationWithRepetition(level + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        sb = new StringBuilder();

        n = Integer.parseInt(strs[0]);
        m = Integer.parseInt(strs[1]);

        selectedNumbers = new int[m];

        getPermutationWithRepetition(0);

        System.out.println(sb);
    }
}