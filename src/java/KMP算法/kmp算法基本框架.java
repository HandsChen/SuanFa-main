package KMP算法;

/**
 * KMP 算法是由三个名字分别以K M P的大佬命名
 * 该算法能够高效的判断一个字符串是否包含另一个字符串
 * 该类型问题通常可以使用滑动窗口发进行求解
 * 但是如果需要 str = AAAAAB pattern = AAB 则总是需要比对到str最后一个字符才能结束，不够高效
 * KMP算法关键在于构建next数组
 */
public class kmp算法基本框架 {
    public static void main(String[] args) {
        System.out.println("args = " + isMatch("ABAABAF", "BAC"));

    }

    public static boolean isMatch(String target, String pattern) {
        int[] next = buildNext(pattern); //获得next数组
        int j = 0; //pattern开始匹配的位置
        for (int i = 0; i < target.length(); i++) {
            while (j > 0 && pattern.charAt(j) != target.charAt(i)) {
                j = next[j - 1];
            }
            //匹配成功j前进
            if (pattern.charAt(j) == target.charAt(i)) {
                j++;
            }
            if (j == pattern.length()) {
                return true;
            }
        }
        return false;
    }

    //构建next数组
    public static int[] buildNext(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = next[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
