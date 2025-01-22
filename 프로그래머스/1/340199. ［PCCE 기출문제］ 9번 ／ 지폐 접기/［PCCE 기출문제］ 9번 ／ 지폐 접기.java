class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int maxBill = Math.max(bill[0], bill[1]);
        int minBill = Math.min(bill[0], bill[1]);

        bill[0] = Math.max(maxBill, minBill);
        bill[1] = Math.min(maxBill, minBill);
        
        int maxWallet = Math.max(wallet[0], wallet[1]);
        int minWallet = Math.min(wallet[0], wallet[1]);
        
        while(true) {           
            if(bill[0] <= maxWallet && bill[1] <= minWallet) {
                break;
            }
            
            maxBill = Math.max(bill[0], bill[1]) / 2;
            minBill = Math.min(bill[0], bill[1]);

            bill[0] = Math.max(maxBill, minBill);
            bill[1] = Math.min(maxBill, minBill);

            maxWallet = Math.max(wallet[0], wallet[1]);
            minWallet = Math.min(wallet[0], wallet[1]);
            
            answer++;
        }
        return answer;
    }
}