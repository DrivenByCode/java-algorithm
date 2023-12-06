import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] ascendingArr = IntStream.rangeClosed(1, 8).toArray();

        int[] descendingArr = Arrays.stream(ascendingArr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        if (Arrays.equals(arr, ascendingArr)) {
            System.out.println("ascending");
            return;
        }
        if (Arrays.equals(arr, descendingArr)) {
            System.out.println("descending");
            return;
        }
        System.out.println("mixed");
    }
}
