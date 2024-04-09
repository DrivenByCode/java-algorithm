public class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int minMove = length - 1; // 최소 좌우 이동 횟수 초기화

        for(int i = 0; i < length; i++) {
            char c = name.charAt(i);
            // 상하 이동 횟수 계산
            answer += Math.min(c - 'A', 'Z' - c + 1);

            // 다음 문자가 'A'가 아닌 위치 찾기
            int nextIndex = i + 1;
            while (nextIndex < length && name.charAt(nextIndex) == 'A') {
                nextIndex++;
            }

            minMove = Math.min(minMove, i + length - nextIndex + Math.min(i, length - nextIndex));
        }

        answer += minMove;
        return answer;
    }
}
