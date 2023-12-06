import java.util.ArrayList;
class Solution {
    public int solution(int n) {
        int idx = 1;
        int[] noTree = new int[10000];

        // 최대 3x 부족의 수는 101이기 때문에 임의의 큰 수 10000으로 지정해놓고 101이 되면 종료
        for(int i = 1; i <= 10000; i++) {
            String tmp = String.valueOf(i);
            if(i % 3 != 0) {
                if(!tmp.contains("3")) {
                    noTree[idx++] = i;
                }
            }
            if(idx == 101) break;
        }
        return noTree[n];
    }
}