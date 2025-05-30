package 剑指offer.数组;

import java.util.Arrays;
import java.util.Comparator;

public class No11_破解闯关密码 {
    public static void main(String[] args) {
        int[] password = {15, 8, 7};
        crackPassword(password);
    }

    static String crackPassword(int[] password) {
        // 使用Integer类型的比较器来对数组排序
        Integer[] passwordWrapper = Arrays.stream(password).boxed().toArray(Integer[]::new);  // 将int[]转换为Integer[]，因为Comparator需要对象类型
        Arrays.sort(passwordWrapper, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                long a = Long.parseLong(o1 + "" + o2);
                long b = Long.parseLong(o2 + "" + o1);
                return Long.compare(a, b);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Integer i : passwordWrapper) {
            sb.append(i);
        }
        return sb.toString();
    }

}
