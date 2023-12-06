class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        
        // 모든 좌표 중 최소 값, 최대 값을 갱신
        for(int[] coord : lines) {
            minX = Math.min(coord[0], minX);
            maxX = Math.max(coord[1], maxX);
        }
        
        
        for(int i = minX; i <= maxX; i++) {
            int cnt = 0;
            if(lines[0][0] <= i && i < lines[0][1]) cnt++;
            if(lines[1][0] <= i && i < lines[1][1]) cnt++;
            if(lines[2][0] <= i && i < lines[2][1]) cnt++;
            // 두개 이상 겹치는 곳이 있으면 카운트
            if(cnt >= 2) answer++;
        }
        
        return answer;
    }
}