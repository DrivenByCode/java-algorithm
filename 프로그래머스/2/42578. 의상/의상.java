import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] c : clothes) {
            String key = c[1]; // category
            
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        // 2. 입지 않는 경우를 추가하여 모든 조합 계산하기
        Iterator<Integer> it = map.values().iterator();
        int answer = 1;
        
        while(it.hasNext()) {
            answer *= it.next().intValue() + 1;
        }

        // 3. 아무종류의 옷도 입지 않는 경우 제외하기
        return answer - 1;
    }
}