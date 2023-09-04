class Solution {
    static boolean getSeqType(int[] common) {
        int ratio = 0;

        // Runtime Error 방지: 0으로 나누는 예외 제외
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
        int ratio = 0;
        // Runtime Error 방지: 0으로 나누는 예외 제외
        if (getSeqType(common)) {
            if (common[0] != 0) {
                ratio = common[1] / common[0];
            }
            answer = common[common.length - 1] * ratio;
        } else {
            int diff = common[1] - common[0];
            answer = common[common.length - 1] + diff;
        }
        return answer;
    }
}