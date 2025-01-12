import java.io.*;
import java.util.*;

public class Main {
    private static final List<Point> pairs = new ArrayList<>(); // 괄호 쌍의 인덱스 저장
    private static final Set<String> resultSet = new TreeSet<>(); // 결과 문자열 저장 (중복 제거 및 사전 순 정렬)
    private static String input;

    private static class Point {
        private final int openingSignIdx;
        private final int closingSignIdx;

        public Point(final int openingSignIdx, final int closingSignIdx) {
            this.openingSignIdx = openingSignIdx;
            this.closingSignIdx = closingSignIdx;
        }
    }

    // 괄호 제거 조합 생성
    private static void generateCombinations(int depth, boolean[] removed) {
        if (depth == pairs.size()) {
            StringBuilder sb = new StringBuilder();
            Set<Integer> removeSet = new HashSet<>();
            // 괄호 쌍 조합 순회하여 제거
            for (int i = 0; i < pairs.size(); i++) {
                if (removed[i]) {
                    removeSet.add(pairs.get(i).openingSignIdx); // 여는 괄호 인덱스
                    removeSet.add(pairs.get(i).closingSignIdx); // 닫는 괄호 인덱스
                }
            }

            for (int i = 0; i < input.length(); i++) {
                if (!removeSet.contains(i)) { // 제거되지 않은 문자만 추가
                    sb.append(input.charAt(i));
                }
            }

            // 결과 저장 (중복 제거)
            if (!sb.toString().equals(input)) { // 원래 문자열은 제외
                resultSet.add(sb.toString());
            }
            return;
        }

        // 현재 괄호 쌍 포함하지 않음
        generateCombinations(depth + 1, removed);

        // 현재 괄호 쌍 포함
        removed[depth] = true;
        generateCombinations(depth + 1, removed);
        removed[depth] = false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        input = br.readLine();

        Stack<Integer> stack = new Stack<>();

        // 괄호 위치 저장
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                stack.push(i); // 여는 괄호의 인덱스 저장
            } else if (c == ')') {
                pairs.add(new Point(stack.pop(), i)); // 여는 괄호와 닫는 괄호 쌍 저장
            }
        }

        // 부분집합 생성
        generateCombinations(0, new boolean[pairs.size()]);

        // 결과 출력
        for (String result : resultSet) {
            sb.append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
