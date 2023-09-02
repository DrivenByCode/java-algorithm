import java.util.Arrays;

public class Solution {
    public int[] solution(int[][] scores) {
        int n = scores.length;
        int[] answer = new int[n];
        double[] avg = new double[n];
        
        // 평균 점수 계산
        for (int i = 0; i < n; i++) {
            avg[i] = (scores[i][0] + scores[i][1]) / 2.0;
        }
        
        // 순위 할당
        for (int i = 0; i < n; i++) {
            int rank = 1;
            for (int j = 0; j < n; j++) {
                if (avg[j] > avg[i]) {
                    rank++;
                }
            }
            answer[i] = rank;
        }
        
        return answer;
    }
}
