package 剑指offer.找规律;

public class No72_数字序列中某一位的数字 {
    public static void main(String[] args) {
        System.out.println("args = " + findKthNumber3(30));
    }

    //1.暴力法 (虽然很容易想到，但是容易超时)
    public static int findKthNumber(int k) {
        int start = 1;
        while (true) {
            int len = lenOfNum(start);
            if (k - len <= 0) { //说明就在当前start结果中
                return String.valueOf(start).charAt(k - 1) - '0';
            } else {
                k = k - len;
            }
            start++;
        }
    }

    //获取数字一共有几位
    public static int lenOfNum(int num) {
        int res = 0;
        while (num > 0) {
            res++;
            num = num / 10;
        }
        return res;
    }

    /*
     * 0 - 9 th = n 10
     * 10 - 19 th = 10 + 10
     * 20 - 29 th = 10 + 10
     *
     * */

    /*
    1-9 9
    10-99 2*10*9
    100-999 3*100*9
    1000-9999 4*1000*9
    10000-99999 5*10000*9
    100000-999999 6*100000*9

    7
    100-999

    测试101
     * */

    //2.找规律寻找一种更加迅速的方法
    public static int findKthNumber2(int k) {
        if (k >= 1 && k <= 9) {
            return k;
        }
        int base = 10;
        int numBitCount = 2;
        int sum = 9;
        while (k > sum + numBitCount * Math.pow(base, numBitCount - 1) * 9) {
            sum += (int) (numBitCount * Math.pow(base, numBitCount - 1) * 9);
            numBitCount++;
        }
        k = k - sum;
        int start = (int) Math.pow(base, numBitCount - 1);
        //这里定位可以使用一种更加简单的方法
        int number = start + (k - 1) / numBitCount; //k-1的目的是为了从0开始，对齐索引
        // 确定数字中的具体位
        int digitIndex = (k - 1) % numBitCount;
        return String.valueOf(number).charAt(digitIndex) - '0';



        /*int th = (k - 1) / numBitCount;
        while (th > 0) {
            start++;
            th--;
        }
        return String.valueOf(start).charAt((k - 1) % numBitCount) - '0';*/
    }

    //3.查看参考答案
    public static int findKthNumber3(int k) {
        int start = 1;
        int digit = 1;
        int count = 9;
        while (k > count) {
            k -= count;
            start *= 10;
            digit++;
            count = digit * start * 9;
        }
        int number = start + (k - 1) / digit; //k-1的目的是为了从0开始，对齐索引
        // 确定数字中的具体位
        int digitIndex = (k - 1) % digit;
        return String.valueOf(number).charAt(digitIndex) - '0';
    }
}
// 10 11 12 13 14 15 16 17 18 19 20
