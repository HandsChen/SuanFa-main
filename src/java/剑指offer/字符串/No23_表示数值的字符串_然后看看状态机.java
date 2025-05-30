package 剑指offer.字符串;

public class No23_表示数值的字符串_然后看看状态机 {
    public static void main(String[] args) {
        System.out.println("args = " + validNumber(".e1"));
    }

    //1. 暴力法（简直不想做第二次，太无脑了，又浪费时间）
    public static boolean validNumber(String s) {
        if (null == s) {
            return false;
        }
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }
        char[] charArray = s.toCharArray();
        boolean isExistE = false;
        boolean isExistDot = false;
        int eIndex = -1;//记录初次发现e的位置
        boolean res = true;
        for (int i = 0; i < charArray.length; i++) {
            int startOfChar = charArray[i];
            //‘+’‘-’出现正确情况：只能在开头和e后一位，否则直接错误
            if (startOfChar == '+' || startOfChar == '-') {
                if (i != 0 && (charArray[i - 1] != 'e' && charArray[i - 1] != 'E')) {
                    return false;
                }
            }
            //‘e’出现正确情况：只出现一次，且前面有数字
            else if (startOfChar == 'e' || startOfChar == 'E') {
                if (i == 0) {
                    return false;
                }
                //出现e首先判断前边是否有数字或者.
                if (!isNumberAtIndex(charArray, i - 1) && !isDotAtIndex(charArray, i - 1)) {
                    return false;
                }
                //然后判断e后面是否有数字
                if (i + 1 < charArray.length) {
                    //判断e后面+或-后面是否有数字
                    if (charArray[i + 1] == '+' || charArray[i + 1] == '-') {
                        if (i + 2 < charArray.length) {
                            if (!isNumberAtIndex(charArray, i + 2)) return false;
                        } else {
                            return false;
                        }
                    } else {
                        //判断e后面是否有数字
                        if (!isNumberAtIndex(charArray, i + 1)) return false;
                    }
                } else { //后面没字符了
                    return false;
                }
                if (isExistE) {
                    return false;
                } else {
                    isExistE = true;
                    eIndex = i;
                }
            }
            //‘.’出现正确情况：只出现一次，且在e的前面
            else if (startOfChar == '.') {
                //判断是否在e的前面
                if (-1 != eIndex && eIndex < i) {
                    return false;
                }
                //判断其是否还跟着一位数字或者e
                if (i + 1 < charArray.length) {
                    if (!isNumberAtIndex(charArray, i + 1) && !isEAtIndex(charArray, i + 1)) return false;
                } else { //后面没字符了
                    //判断.前面是否有字符
                    if (i - 1 < 0) {
                        return false;
                    }
                    //判断.前没有数字
                    if (!isNumberAtIndex(charArray, i - 1)) return false;
                }
                //判断是否出现过
                if (isExistDot) {
                    return false;
                } else {
                    isExistDot = true;
                }
            } else if ((startOfChar - '0') > 9 || (startOfChar - '0') < 0) {
                return false;
            }
        }
        return res;
    }

    private static boolean isNumberAtIndex(char[] charArray, int i) {
        int existFrontNumber = charArray[i] - '0';
        return existFrontNumber <= 9 && 0 <= existFrontNumber;
    }

    private static boolean isDotAtIndex(char[] charArray, int i) {
        return charArray[i] == '.' && i != 0;

    }

    private static boolean isEAtIndex(char[] charArray, int i) {
        return charArray[i] == 'e' || charArray[i] == 'E';


    }
}
