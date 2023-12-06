import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, m;
    static int[] selectedNumbers;

    static void getCombination(int level, int start) {
        if (level == m) {
            // 조합 목록 중 하나 출력
            for (int selectedNumber : selectedNumbers) {
                System.out.print(selectedNumber + " ");
            }
            System.out.println();
            return;
        }

        // 조합 구하기
        for (int i = start; i <= n; i++) {
            selectedNumbers[level] = i;
            getCombination(level + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        selectedNumbers = new int[m];

        getCombination(0, 1);
    }
}