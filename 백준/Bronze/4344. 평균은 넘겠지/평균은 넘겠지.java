import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int c = Integer.parseInt(br.readLine());
        List<Integer> scores = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            scores.clear();
            int sum = 0;
            for (int j = 0; j < n; j++) {
                int score = Integer.parseInt(st.nextToken());
                scores.add(score);
                sum += score;
            }

            int average = sum / n;

            int students = (int) scores.stream().filter(score -> score > average).count();

            System.out.printf("%.3f%%\n", (students / (double) n) * 100);
        }
    }
}
