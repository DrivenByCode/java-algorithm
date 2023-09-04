class Solution {
    static boolean getSeqType(int[] common) {
        int ratio = 0;
        
        if(common[0] != 0) {
            ratio = common[1] / common[0];
        }

        if(common[1] * ratio == common[2]) {
            return true;
        } 
        return false;
    }
    public int solution(int[] common) {
        int answer = 0;
        if(common[0] != 0 && getSeqType(common)) {
            int ratio = common[1] / common[0];
            answer = common[common.length - 1] * ratio;
        } else {
            int diff = common[1] - common[0];
            answer = common[common.length - 1] + diff;
        }
        return answer;
    }
}