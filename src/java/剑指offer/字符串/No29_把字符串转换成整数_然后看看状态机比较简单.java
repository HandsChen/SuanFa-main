package 剑指offer.字符串;

public class No29_把字符串转换成整数_然后看看状态机比较简单 {
    public static void main(String[] args) {
        System.out.println("myAtoi() = " + myAtoi("-2147483648"));
    }

    public static int myAtoi(String s) {
        //1.首先去除空格
        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }
        //3.检测字符串首位是否为符号位
        int flag = 0;
        int start = 0;//开始遍历的指针
        if (s.charAt(0) == '+') {
            flag = 1;//为+
        }
        if (s.charAt(0) == '-') {
            flag = 2;//为-
        }
        if (flag != 0) { //如果有符号那么就跳过符号
            start++;
        }
        StringBuilder sb = new StringBuilder();
        //开始遍历
        for (int i = start; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (isNumber(curChar)) { //如果当前字符是数字，那么就将其加入进来
                sb.append(curChar);
            } else { //如果不是数字，那么就跳出
                break;
            }
        }
        //如果sb为空，那么就可以直接返回0
        if (sb.length() == 0) {
            return 0;
        }
        //这个时候sb可能存在前置为0的情况，此时将其删除，如果是0000，那么只保留一个0
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        //最后对数据进行舍入
        boolean overScope = isOverScope(sb, flag);
        if (overScope) { //溢出
            return flag == 2 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else { //没有溢出
            return flag == 2 ? sb.toString().equals("2147483648") ? -2147483648 : -Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
        }
    }

    //判断字符是否为数字
    public static boolean isNumber(char ch) {
        return ch - '0' >= 0 && ch - '0' <= 9;
    }

    //判断数字字符串是否溢出
    public static boolean isOverScope(StringBuilder sb, int flag) {
        String maxInt = "2147483647";
        String minInt = "2147483648";
        //首先判断字符串的长度，如果长度小于10，那么肯定不会溢出
        int len = sb.length();
        if (len == 10) { //当长度为10时，是否溢出需要进一步判断
            for (int i = 0; i < 10; i++) {
                int comparePart = flag == 2 ? minInt.charAt(i) - '0' : maxInt.charAt(i) - '0';
                if (sb.charAt(i) - '0' < comparePart) {
                    return false;
                } else if (sb.charAt(i) - '0' > comparePart) { //如果小于
                    return true;
                }
            }
            return false;
        } else return len >= 10;
    }
}

