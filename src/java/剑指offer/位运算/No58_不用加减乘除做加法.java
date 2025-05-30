package 剑指offer.位运算;

public class No58_不用加减乘除做加法 {
    public static void main(String[] args) {

    }

    //自己想的，很不错了
    public static int encryptionCalculate(int dataA, int dataB) {
        int add = 0; //进位值（前一位）
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int a = (dataA) >> i & 1;
            int b = (dataB) >> i & 1;
            int c = a ^ b ^ add;
            if (c == 1) {
                res = res | (1 << i); //修改对应位值上的数为1
            }
            //判断是否产生新的进位，产生进位有 1 1 0 / 1 1 1/ 0 1 1/1 0 1 一共4种情况
            if (a != 0 || b != 0) {
                if (a == 1 && b == 1) { //全部为1
                    add = 1;
                } else { //有且仅有一个不为1
                    add = add == 1 ? 1 : 0;
                }
            } else {
                add = 0;
            }
        }
        return res;
    }

    //标准答案
    public static int encryptionCalculate2(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
}
