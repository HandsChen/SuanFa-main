package 剑指offer.字符串;

public class No28_左旋转字符串 {
    public static void main(String[] args) {

    }
    //常规思路
    public static String dynamicPassword(String password, int target) {
        StringBuilder sb = new StringBuilder(password.length());
        char[] charArray = password.toCharArray();
        for (int i = target; i < charArray.length; i++) {
            sb.append(charArray[i]);
        }
        for (int i = 0; i < target; i++) {
            sb.append(charArray[i]);
        }
        return sb.toString();
    }
    //取余数，很骚但是性能没上面好
    public static String dynamicPassword2(String password, int target) {
        StringBuilder res = new StringBuilder();
        for (int i = target; i < target + password.length(); i++)
            res.append(password.charAt(i % password.length()));
        return res.toString();
    }
}
