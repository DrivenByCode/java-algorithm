import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long sum = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sum();

        System.out.println(sum);
    }
}