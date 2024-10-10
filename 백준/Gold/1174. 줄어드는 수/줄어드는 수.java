import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Long> decreasingNumbers = new ArrayList<>();

        // 1 << 10은 1024로, 10비트(0~9)를 사용한 모든 조합을 나타냄
        for (int i = 1; i < (1 << 10); i++) {
            long number = 0;
            for (int j = 9; j >= 0; j--) {
                // 비트마스크의 i번째 비트가 켜져있는지 확인
                if ((i & (1 << j)) != 0) {
                    number = number * 10 + j;
                }
            }
            decreasingNumbers.add(number);
        }

        // 오름차순으로 정렬
        Collections.sort(decreasingNumbers);

        if (n > decreasingNumbers.size()) {
            System.out.println(-1);
        } else {
            System.out.println(decreasingNumbers.get(n - 1));
        }
    }
}
