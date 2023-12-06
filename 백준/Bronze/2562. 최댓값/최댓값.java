import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static class NumberIndex implements Comparable<NumberIndex> {
        private final int number;
        private final int index;

        private NumberIndex(int number, int index) {
            this.number = number;
            this.index = index;
        }

        @Override
        public int compareTo(NumberIndex other) {
            return this.number - other.number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<NumberIndex> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int number = Integer.parseInt(br.readLine());
            list.add(new NumberIndex(number, i));
        }

        Collections.sort(list);

        System.out.println(list.get(8).number);
        System.out.println(list.get(8).index);
    }
}
