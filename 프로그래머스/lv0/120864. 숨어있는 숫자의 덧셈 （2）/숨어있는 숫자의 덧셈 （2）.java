class Solution {
    public int solution(String my_string) {
        int answer = 0;
        boolean isDigits = false;
        
        String tmp = "";
        
        for(int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);
            if(Character.isDigit(c)) {
                isDigits = true;
                tmp += c;               
            } else {
                isDigits = false;
                if(tmp.length() > 0) {
                    answer += Integer.parseInt(tmp);
                    tmp = "";
                } 
            }
        }
        
        if(tmp.length() > 0) answer += Integer.parseInt(tmp);
        return answer;
    }
}