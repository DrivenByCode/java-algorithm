import java.util.*;
class Solution {
    public String solution(int[] numbers) {        
        List<String> numbersList = new ArrayList<>();
        
        for(int n : numbers) {
            numbersList.add(String.valueOf(n));
        }
        
        int sum = Arrays.stream(numbers).sum();
        
        // 람다 표현식을 사용하여 정렬
        // 303과 330을 비교할 수 있다!
        Collections.sort(numbersList, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        
        StringBuilder sb = new StringBuilder();
        
        for(String n : numbersList) {
            sb.append(n);
        }
        
        if(sum == 0) {
            // StringBuilder 초기화
            sb.setLength(0);
            sb.append("0");
        }
                
        return sb.toString();
    }
}