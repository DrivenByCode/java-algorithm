class Solution {
    public String solution(String bin1, String bin2) {
        // 2진수로 주어진 수들을 10진법으로 바꿈. 
        int decimal1 = Integer.parseInt(bin1, 2);
        int decimal2 = Integer.parseInt(bin2, 2);
        
        // 바꾼 것을 더한 후 다시 2진법으로 변환
        int sum = decimal1 + decimal2;

        String answer = Integer.toBinaryString(sum);
        
        return answer;
    }
}