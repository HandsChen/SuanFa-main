package 剑指offer.位运算;

public class No54_二进制中1的个数 {
    public static void main(String[] args) {

    }

    //1.暴力法
    public static int hammingWeight(int n) {
        String binaryString = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    //2.寻找一种更加优雅的方式
    public static int hammingWeight2(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

}
