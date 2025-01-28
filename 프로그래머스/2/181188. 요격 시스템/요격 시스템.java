import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // 끝나는 지점을 기준으로 정렬
        Arrays.sort(targets, (t1, t2) -> Integer.compare(t1[1], t2[1]));

        int missileCount = 0; // 요격 미사일 개수
        int interceptPosition = -1; // 현재 요격 위치

        for (int[] missile : targets) {
            int rangeStart = missile[0];
            int rangeEnd = missile[1];

            // 현재 요격 위치가 미사일 범위에 포함되지 않으면 새로 요격
            if (interceptPosition < rangeStart) {
                missileCount++;
                interceptPosition = rangeEnd - 1; // 미사일 끝 범위 직전에서 요격
            }
        }

        return missileCount;
    }
}
