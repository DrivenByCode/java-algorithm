class Solution {
    public int solution(int[] wallet, int[] bill) {
        int maxWallet = Math.max(wallet[0], wallet[1]);
        int minWallet = Math.min(wallet[0], wallet[1]);
        
        int currentMaxBill = Math.max(bill[0], bill[1]);
        int currentMinBill = Math.min(bill[0], bill[1]);
        
        int answer = 0;
        
        while (true) {
            if (currentMaxBill <= maxWallet && currentMinBill <= minWallet) {
                break;
            }
            
            int splitPart = currentMaxBill / 2;
            int otherPart = currentMinBill;
            
            currentMaxBill = Math.max(splitPart, otherPart);
            currentMinBill = Math.min(splitPart, otherPart);
            
            answer++;
        }
        
        return answer;
    }
}