import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int n = Integer.parseInt(br.readLine());
        int[] honey = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        // prefixSum 배열 생성 (누적 합)
        int[] prefixSum = new int[n];
        prefixSum[0] = honey[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + honey[i];
        }

        // 세 가지 경우의 수에 따른 최대 꿀 수집량 계산
        int MAX = Integer.MIN_VALUE;

        // 첫 번째 경우: 벌이 둘 다 오른쪽 끝에 있는 경우
        for (int i = 1; i < n - 1; i++) {
            int honeyAmount = (prefixSum[n - 1] - honey[0] - honey[i]) + (prefixSum[n - 1] - prefixSum[i]);
            MAX = Math.max(MAX, honeyAmount);
        }

        // 두 번째 경우: 벌이 둘 다 왼쪽 끝에 있는 경우
        for (int i = 1; i < n - 1; i++) {
            int honeyAmount = (prefixSum[n - 2] - honey[i]) + prefixSum[i - 1];
            MAX = Math.max(MAX, honeyAmount);
        }

        // 세 번째 경우: 벌이 꿀통을 가운데 두고 나누어진 경우
        for (int i = 1; i < n - 1; i++) {
            int honeyAmount = (prefixSum[i] - honey[0]) + (prefixSum[n - 1] - prefixSum[i - 1] - honey[n - 1]);
            MAX = Math.max(MAX, honeyAmount);
        }

        // 최대 꿀 수집량 출력
        System.out.println(MAX);
    }
}
