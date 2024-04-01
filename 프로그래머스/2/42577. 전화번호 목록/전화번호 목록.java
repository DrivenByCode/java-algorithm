import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        TreeSet<String> phones = new TreeSet<>((o1, o2) -> (o1.compareTo(o2)));
        
        for(String str : phone_book) {
            phones.add(str);
        }
        
        for(String str : phone_book) {
            String tmp = phones.higher(str);
            if(tmp != null && tmp.startsWith(str)) {
                return false;
            }
        }
        
        return true;
    }
}