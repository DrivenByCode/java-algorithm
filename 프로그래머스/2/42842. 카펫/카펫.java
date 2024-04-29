class Solution {
    private static int[] answer = new int[2];
    private static int[] getAnswer(int sum, int yellow) {
        for(int i = 2; i <= sum; i++) {
            if(sum % i == 0) {
                int j = (sum / i);
                if((i >= j) && ((i-2) * (j-2) == yellow)) {
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }
        return answer;
    }
    public int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        
        return getAnswer(sum, yellow);
    }
}