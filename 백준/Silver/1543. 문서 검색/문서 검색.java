import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1543 문서 검색 - 실버5

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String target = br.readLine();

        int answer = 0;
        int index = 0;

        while ((index = input.indexOf(target, index)) != -1) {
            // target이 발견된 위치로부터 target의 길이만큼 건너뜀
            index += target.length();
            answer++;
        }

        System.out.println(answer);
    }
}
