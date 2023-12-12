import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        boolean isMinus = false;
        String tmp = "";

        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                tmp += c;
            } else {
                if (c == '-') {
                    if (!isMinus) {
                        sum += Integer.parseInt(tmp);
                        isMinus = true;
                    } else {
                        sum -= Integer.parseInt(tmp);
                    }
                } else {
                    if (isMinus) {
                        sum -= Integer.parseInt(tmp);
                    } else {
                        sum += Integer.parseInt(tmp);
                    }
                }
                tmp = "";
            }
        }

        if (!tmp.isEmpty()) {
            if (isMinus) {
                sum -= Integer.parseInt(tmp);
            } else {
                sum += Integer.parseInt(tmp);
            }
            tmp = "";
        }

        System.out.println(sum);
    }
}
