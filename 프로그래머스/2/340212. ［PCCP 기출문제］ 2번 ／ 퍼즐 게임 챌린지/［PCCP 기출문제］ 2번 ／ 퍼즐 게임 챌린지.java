import java.util.*;

class Solution {
    private static long getResult(int startLevel, int[] diffs, int[] times, long limit) {
        int level = startLevel;
        long time = 0;
        int prevTime = 0;

        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i] - level;

            if (diff > 0) { // 틀린 경우 계산
                time += (long) diff * (times[i] + prevTime);
            }
            time += times[i]; // 퍼즐을 푸는 시간 추가

            if (time > limit) {
                return -1;
            }

            prevTime = times[i]; // 이전 퍼즐의 시간 갱신
        }

        return time;
    }

    private static int getOptimizedLevel(int[] diffs, int[] times, long limit) {
        int lt = 1;
        int rt = Arrays.stream(diffs).max().getAsInt();
        int result = rt;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (getResult(mid, diffs, times, limit) > -1) { // 제한 시간 내 해결 가능
                result = mid; // 가능한 최솟값 갱신
                rt = mid - 1; // 더 낮은 레벨 탐색
            } else {
                lt = mid + 1; // 더 높은 레벨 탐색
            }
        }

        return result;
    }

    public int solution(int[] diffs, int[] times, long limit) {
        return getOptimizedLevel(diffs, times, limit);
    }
}
