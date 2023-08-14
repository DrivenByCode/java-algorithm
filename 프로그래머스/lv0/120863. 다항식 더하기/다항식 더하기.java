class Solution {
    public String solution(String polynomial) {
        int num = 0;
        int coeff = 0;
        
        if(polynomial.contains("x")) {
            if(polynomial.contains("+")) {
                String[] strs = polynomial.split(" \\+ ");

                for(String s : strs) {
                    if(s.contains("x")) {
                        if(s.length() >= 2) {
                            s = s.replace("x", "");
                            coeff += Integer.parseInt(s);
                        } else if(s.length() == 1) {
                            coeff += 1;
                        }
                    } else {
                        num += Integer.parseInt(s);
                    }
                }
            } else {
                if(polynomial.length() >= 2) {
                    polynomial = polynomial.replace("x", "");
                    coeff = Integer.parseInt(polynomial);
                } else if(polynomial.length() == 1) {
                    coeff += 1;
                }
            }
        } else {
            if(polynomial.contains("+")) {
                String strs[] = polynomial.split(" \\+ ");
                for(String s : strs) {
                    num += Integer.parseInt(s);
                }
            } else {
                num += Integer.parseInt(polynomial);
            }
        }
        
        String notConstant = "";
        String constant = "";
        String answer = "";
        
        if(coeff != 0) {
            notConstant = coeff == 1? "x" : coeff + "x";
            constant = (num == 0? "": " + " + num);
        } else {
            constant = (num == 0? "0": String.valueOf(num));
        }
        
        answer = notConstant + constant;
        
        return answer;
    }
}