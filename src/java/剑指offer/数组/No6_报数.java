package 剑指offer.数组;

public class No6_报数 {
    public static void main(String[] args) {

    }

    static public int[] countNumbers(int cnt) {
        int limit = (int) (Math.pow(10, cnt) - 1);
        int[] res = new int[limit];
        for (int i = 1; i <= limit; i++) {
            res[i - 1] = i;
        }
        return res;
    }
}
