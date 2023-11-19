import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String quiz = br.readLine();
            int score = quiz.charAt(0) == 'O' ? 1 : 0;
            int totalScore = 0;
            totalScore += score;
            for (int j = 1; j < quiz.length(); j++) {
                if (quiz.charAt(j) == 'O') {
                    score++;
                } else {
                    score = 0;
                }
                totalScore += score;
            }

            System.out.println(totalScore);
        }
    }
}
