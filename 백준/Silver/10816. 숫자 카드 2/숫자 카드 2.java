import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] numberCards;

    private static int upperBound(int key) {
        int lt = 0;
        int rt = numberCards.length;

        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (numberCards[mid] <= key) {
                lt = mid + 1;
            } else {
                rt = mid;
            }
        }
        return lt;
    }

    private static int lowerBound(int key) {
        int lt = 0;
        int rt = numberCards.length;

        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (numberCards[mid] < key) {
                lt = mid + 1;
            } else {
                rt = mid;
            }
        }
        return lt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numberCards = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numberCards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numberCards);

        int m = Integer.parseInt(br.readLine());
        int[] guessCards = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < m; j++) {
            guessCards[j] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < m; k++) {
            // 해당 key를 가지고 있는 인덱스의 상계 - 하계 하면 갯수를 알 수 있음.
            int count = upperBound(guessCards[k]) - lowerBound(guessCards[k]);
            sb.append(count).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
