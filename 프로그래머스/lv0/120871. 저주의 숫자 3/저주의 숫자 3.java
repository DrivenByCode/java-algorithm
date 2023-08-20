import java.util.ArrayList;
class Solution {
    public int solution(int n) {
        int idx = 1;
        int[] noTree = new int[1000];
        
        for(int i = 1; i <= 1000; i++) {
            String tmp = String.valueOf(i);
            if(i % 3 != 0) {
                if(!tmp.contains("3")) {
                    noTree[idx++] = i;
                }
            }
        }
        return noTree[n];
    }
}