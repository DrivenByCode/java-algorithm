import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// 백준 1269 대칭 차집합 - 실버4
// set 성질 이용

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int aN = Integer.parseInt(st.nextToken());
        int bN = Integer.parseInt(st.nextToken());

        // 배열 A 입력 후 Set으로 변환
        Set<Integer> setA = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toSet());

        // 배열 B 입력 후 Set으로 변환
        Set<Integer> setB = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toSet());

        // A - B와 B - A의 대칭 차집합 크기 계산
        Set<Integer> diffA = new HashSet<>(setA);
        Set<Integer> diffB = new HashSet<>(setB);

        diffA.removeAll(setB);
        diffB.removeAll(setA);

        // 대칭 차집합의 원소 개수 출력
        System.out.println(diffA.size() + diffB.size());
    }
}
