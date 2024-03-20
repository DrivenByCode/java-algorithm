import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n;
    private static int[] arr;
    private static int count = 0;

    private static void nQueen(int row) {
        // 모든 원소를 다 채운 상태면 count 증가 및 return
        if (row == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            // 해당 행에 i를 놓았음을 의미, 1부터 n까지 안놓은 것을 놓을 수 있음.
            arr[row] = i;
            // 놓을 수 있는 위치일 경우 재귀호출
            if (isPossible(row)) {
                nQueen(row + 1);
            }
        }
    }

    private static boolean isPossible(int col) {
        for (int i = 0; i < col; i++) {
            // 해당 열의 행과 i열의 행이 일치할경우 (같은 행에 존재할 경우)
            if (arr[col] == arr[i]) {
                return false;
            }

            // 대각선
            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        nQueen(0);

        System.out.println(count);
    }
}
