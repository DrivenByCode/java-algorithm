import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private enum Grade {
        FIRST_GRADE(90, 100, "A"), SECOND_GRADE(80, 89, "B"), THIRD_GRADE(70, 79, "C"), FOURTH_GRADE(60, 69, "D"), FIFTH_GRADE(0, 59, "F");
        private final int minScore;
        private final int maxScore;
        private final String grade;

        Grade(int minScore, int maxScore, String grade) {
            this.minScore = minScore;
            this.maxScore = maxScore;
            this.grade = grade;
        }

        public int getMinScore() {
            return minScore;
        }

        public int getMaxScore() {
            return maxScore;
        }

        public String getGrade() {
            return grade;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int score = Integer.parseInt(br.readLine());

        for (Grade grade : Grade.values()) {
            if (grade.getMinScore() <= score && score <= grade.getMaxScore()) {
                System.out.println(grade.getGrade());
            }
        }
    }
}
