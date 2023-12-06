// 출력해보기 위해 import
import java.util.*;
class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        int[] counts = new int[4];
        
        for(int i = 0; i < babbling.length; i++) {
            counts = new int[4];
            
            // "" 대신 "1"을 넣는 이유는 발음 할 수 있는 것이 주어진 문자열 사이에 위치했을 때, 예외를 방지하기 위함임
            // 예를 들어 wyeoo는 ye먼저 사라지고 woo사라지면서 전체 모두 발음할 수 있는 것처럼 처리 될 수 있음.
            if(babbling[i].contains("aya") && counts[0] == 0) {
                babbling[i] = babbling[i].replace("aya", "1");
                counts[0]++;
            } 
            if(babbling[i].contains("ye") && counts[1] == 0) {
                babbling[i] = babbling[i].replace("ye", "1");
                counts[1]++;
            } 
            if(babbling[i].contains("woo") && counts[2] == 0) {
                babbling[i] = babbling[i].replace("woo", "1");
                counts[2]++;
            } 
            if(babbling[i].contains("ma") && counts[3] == 0) {
                babbling[i] = babbling[i].replace("ma", "1");
                counts[3]++; 
            }
            // 어떠한 소문자로도 남아 있다면, 2로 바꾸면 해당 문자열은 발음하지 못한 것이 됨.
            babbling[i] = babbling[i].replaceAll("[a-z]", "2");
            
            // 2를 포함하지 않았을 경우만 카운트 해주기
            if(!babbling[i].contains("2")) answer++;
        }
        
        System.out.println(Arrays.toString(babbling));
        return answer;
    }
}