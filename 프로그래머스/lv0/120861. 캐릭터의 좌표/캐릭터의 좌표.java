import java.util.*;
class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        HashMap<String, int[]> move = new HashMap<>();
        
        move.put("left", new int[]{-1, 0});
        move.put("right", new int[]{1, 0});
        move.put("up", new int[]{0, 1});
        move.put("down", new int[]{0, -1});
        
        int[] start = {0, 0};
        int maxLengthX = board[0] / 2;
        int maxLengthY = board[1] / 2;
        
        for(String s : keyinput) {
            int[] tmp = move.get(s);
            int nx = start[0] + tmp[0];
            int ny = start[1] + tmp[1];
            if(maxLengthX * -1 <= nx && nx <= maxLengthX && maxLengthY * -1 <= ny && ny <= maxLengthY){
                start[0] += tmp[0];
                start[1] += tmp[1];
            }
        }
        return start;
    }
}