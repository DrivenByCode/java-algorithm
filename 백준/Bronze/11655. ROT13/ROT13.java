import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        for (char c : input.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                if (Character.isLowerCase(c)) {
                    c += 13;

                    if (c > 'z') {
                        c = (char) ('a' + (c - 'z') - 1);
                    }
                } else {
                    c += 13;
                    
                    if (c > 'Z') {
                        c = (char) ('A' + (c - 'Z') - 1);
                    }
                }
            }
            sb.append(c);
        }

        System.out.println(sb);
    }
}