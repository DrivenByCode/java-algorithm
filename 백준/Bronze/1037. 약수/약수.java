import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int h = Integer.parseInt(br.readLine());


        long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();

        long min = arr[0];
        long max = arr[arr.length - 1];

        System.out.println(min * max);
    }
}
