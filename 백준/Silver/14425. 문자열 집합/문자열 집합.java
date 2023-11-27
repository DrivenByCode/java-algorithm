import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        HashSet<String> wordList = new HashSet<>();
        for (int i = 0; i < n; i++) {
            wordList.add(br.readLine());
        }

        int answer = 0;
        for (int j = 0; j < m; j++) {
            if (wordList.contains(br.readLine())) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}