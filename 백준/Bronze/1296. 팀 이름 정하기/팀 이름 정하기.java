import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;

public class Main {
    private static class CountName implements Comparable<CountName> {
        private final int score;
        private final String name;

        private CountName(final int score, final String name) {
            this.score = score;
            this.name = name;
        }

        @Override
        public int compareTo(CountName other) {
            if (this.score == other.score) {
                return this.name.compareTo(other.name);
            }
            return Integer.compare(other.score, this.score);
        }
    }

    private static int[] countWord(String input) {
        int[] counts = new int[4];
        for (char c : input.toCharArray()) {
            if (c == 'L') {
                counts[0]++;
            } else if (c == 'O') {
                counts[1]++;
            } else if (c == 'V') {
                counts[2]++;
            } else if (c == 'E') {
                counts[3]++;
            }
        }
        return counts;
    }

    private static int getScores(int[] firstCounts, String input) {
        int[] inputCounts = countWord(input);

        int[] counts = new int[4];
        for (int i = 0; i < 4; i++) {
            counts[i] = firstCounts[i] + inputCounts[i];
        }

        int cal = ((counts[0] + counts[1]) * (counts[0] + counts[2]) * (counts[0] + counts[3]) * (counts[1] + counts[2]) * (counts[1] + counts[3]) * (counts[2] + counts[3]));
        return cal % 100;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        int[] firstCounts = countWord(name);

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<CountName> counts = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            int score = getScores(firstCounts, input);

            counts.add(new CountName(score, input));
        }

        System.out.println(Objects.requireNonNull(counts.poll()).name);
    }
}
