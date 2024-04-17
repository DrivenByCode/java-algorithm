import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int width = Integer.MIN_VALUE;
        int height = Integer.MIN_VALUE;
        
        for(int[] size : sizes) {
            Arrays.sort(size);
            width = Math.max(width, size[0]);
            height = Math.max(height, size[1]);
        }
        
        return width * height;
    }
}