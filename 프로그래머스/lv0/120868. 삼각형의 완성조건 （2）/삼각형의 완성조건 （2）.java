import java.util.Arrays;
import java.util.HashSet;
class Solution {
    public int solution(int[] sides) {
        Arrays.sort(sides);
        HashSet<Integer> set = new HashSet<>();
        
        // 가장 긴 변이 sides[1] 인 경우
        for(int i = 1; i <= sides[1]; i++) {
            if(sides[1] < i + sides[0]) set.add(i);
        }
        
        // 가장 긴 변이 x 인 경우
        for(int i = sides[1] + 1; i <= 10000; i++) {
            if(i< sides[0] + sides[1]) set.add(i);
        }
        return set.size();
    }
}