class Solution {
    static int getNumList(int start, int count) {
        int total = 0;
        for(int i = start; i < start + count; i++) {
            total += i;
        }
        return total;
    }
    public int[] solution(int num, int total) {
        int start = 0;
        int[] answer = new int[num];
        
        while(getNumList(start, num) != total) {
            if(getNumList(start, num) < total) {
                start++;
            } else if(getNumList(start, num) > total) {
                start--;
            }
        }
        
        int idx = 0;
        for(int i = start; i < start+ num; i++) {
            answer[idx++] = i;
        }
        
        return answer;
    }
}