import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String word = br.readLine();
            int k = Integer.parseInt(br.readLine());

            if (k == 1) {
                // K가 1이면 모든 문자가 답이 될 수 있음
                sb.append("1 1\n");
                continue;
            }

            // 문자와 그 인덱스 리스트를 저장하는 맵
            HashMap<Character, List<Integer>> map = new HashMap<>();

            // 각 문자에 대해 인덱스 저장
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                map.putIfAbsent(c, new ArrayList<>());
                map.get(c).add(j);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = Integer.MIN_VALUE;

            // 각 문자에 대해 슬라이딩 윈도우로 계산
            for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
                List<Integer> indices = entry.getValue(); // 해당 문자가 위치해 있는 인덱스 목록
                if (indices.size() < k) continue; // K개 미만이면 스킵

                // 슬라이딩 윈도우로 K개의 문자를 포함하는 구간 계산
                for (int j = 0; j <= indices.size() - k; j++) {
                    int start = indices.get(j);
                    int end = indices.get(j + k - 1);
                    int len = end - start + 1;


                    minLen = Math.min(minLen, len);
                    maxLen = Math.max(maxLen, len);
                }
            }

            if (minLen == Integer.MAX_VALUE || maxLen == Integer.MIN_VALUE) {
                sb.append("-1\n"); // 조건을 만족하는 구간이 없는 경우
            } else {
                sb.append(minLen).append(" ").append(maxLen).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
