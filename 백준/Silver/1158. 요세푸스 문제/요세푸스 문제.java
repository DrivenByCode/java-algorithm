import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            list.add(i);
        }

        sb.append("<");
        int idx = 0;
        while(!list.isEmpty()) {
            // K번째 사람을 제거
            idx = (idx + K - 1) % list.size();
            // remove(int index) : 이 버전은 지정된 인덱스의 요소를 제거하고 그 요소를 반환합니다.
            // remove(Object o) : 이 버전은 지정된 객체와 일치하는 첫 번째 요소를 제거합니다. 성공 시 true, 실패 시 false 반환
            sb.append(list.remove(idx));
            if(!list.isEmpty()) sb.append(", ");
        }
        sb.append(">");

        System.out.println(sb.toString());
    }
}