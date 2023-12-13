import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] selectedNumber = new int[6];
    private static final StringBuilder sb = new StringBuilder();

    private static void lotto(int[] arr, int level, int start) {
        if (level == 6) {
            for (int number : selectedNumber) {
                sb.append(number + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < arr.length; i++) {
            selectedNumber[level] = arr[i];
            lotto(arr, level + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            String input = br.readLine();
            if (input.equals("0") || input.isEmpty()) {
                System.out.println(sb.toString().trim());
                return;
            }

            selectedNumber = new int[6];

            st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            lotto(arr, 0, 0);

            sb.append("\n");
        }

    }
}
