class Solution {
    static int countNum(int num, int k) {
        int cnt = 0;
        String tmp = String.valueOf(num);
        for(int i = 0; i < tmp.length(); i++) {
            if(tmp.charAt(i) == ('0' + k)) {
                cnt++;
            }
        }
        return cnt;
    }
    public int solution(int i, int j, int k) {
        int answer = 0;
        
        for(int start = i; start <= j; start++) {
            answer += countNum(start, k);
        }
        
        return answer;
    }
}