import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int totalPicks = picks[0] + picks[1] + picks[2];
        // 사용할 그룹의 수는 곡괭이 총 개수와 광물 그룹 수 중 작은 값
        int groupCount = Math.min((minerals.length + 4) / 5, totalPicks);
        
        List<Group> groups = new ArrayList<>();
        
        // minerals를 5개씩 그룹으로 나누기
        for (int i = 0; i < groupCount; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, minerals.length);
            int diamondCount = 0, ironCount = 0, stoneCount = 0;
            
            for (int j = start; j < end; j++) {
                String mineral = minerals[j];
                if (mineral.equals("diamond")) {
                    diamondCount++;
                } else if (mineral.equals("iron")) {
                    ironCount++;
                } else if (mineral.equals("stone")) {
                    stoneCount++;
                }
            }
            
            // 각 곡괭이로 사용했을 때의 피로도 계산
            int diamondCost = (end - start); // 다이아몬드 곡괭이는 모든 광물을 1의 피로도로 캔다.
            int ironCost = diamondCount * 5 + ironCount * 1 + stoneCount * 1;
            int stoneCost = diamondCount * 25 + ironCount * 5 + stoneCount * 1;
            
            groups.add(new Group(diamondCost, ironCost, stoneCost));
        }
        
        // 무게(=돌 곡괭이 사용시 피로도)를 기준으로 내림차순 정렬
        groups.sort((g1, g2) -> Integer.compare(g2.stoneCost, g1.stoneCost));
        
        int fatigue = 0;
        
        // 우선순위가 높은 곡괭이부터 할당
        for (Group group : groups) {
            if (picks[0] > 0) { // 다이아몬드 곡괭이
                fatigue += group.diamondCost;
                picks[0]--;
            } else if (picks[1] > 0) { // 철 곡괭이
                fatigue += group.ironCost;
                picks[1]--;
            } else if (picks[2] > 0) { // 돌 곡괭이
                fatigue += group.stoneCost;
                picks[2]--;
            }
        }
        
        return fatigue;
    }
    
    // 그룹 정보를 저장하는 클래스
    private static class Group {
        private final int diamondCost;
        private final int ironCost;
        private final int stoneCost;
        
        private Group(final int diamondCost, final int ironCost, final int stoneCost) {
            this.diamondCost = diamondCost;
            this.ironCost = ironCost;
            this.stoneCost = stoneCost;
        }
    }
}
