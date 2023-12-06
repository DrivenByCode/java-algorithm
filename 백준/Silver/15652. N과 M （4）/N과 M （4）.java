import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, m;
    static int[] selectedNumbers;
    static boolean[] visited;


    // 중복 조합
    private static void getPermutation(int level, int start) {
        if (level == m) {
            for (int selectedNumber : selectedNumbers) {
                System.out.print(selectedNumber + " ");
            }
            System.out.println();
            return;
        }
        for (int i = start; i <= n; i++) {
            selectedNumbers[level] = i;
            getPermutation(level + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        selectedNumbers = new int[m];

        visited = new boolean[n + 1];

        getPermutation(0, 1);
    }
}
