import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] result = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 결과 배열 초기화
        Arrays.fill(result, -1);

        // 스택을 이용한 오큰수 계산
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 스택의 상단에 있는 인덱스의 값보다 현재 값이 크면 결과를 설정
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result[stack.pop()] = arr[i];
            }
            // 현재 인덱스를 스택에 추가
            stack.push(i);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();

    }
}