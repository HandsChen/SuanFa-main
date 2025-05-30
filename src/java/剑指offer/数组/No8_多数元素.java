package 剑指offer.数组;

import java.util.Arrays;

public class No8_多数元素 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 3};
        System.out.println("majorityElement1(Arrays.asList(1,1,1,2,3).toArray()) = " + majorityElement1(nums));
    }

    //众数(排序法)
    static public int majorityElement(int[] nums) {
        //首先进行排序
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //众数(Boyer-Moore 投票算法)
    static public int majorityElement1(int[] nums) {
        int count = 0;
        int res = 0;
        for (int num : nums) {
            //1.如果count==0,那么就把当前的树作为候选
            if (count == 0) {
                res = num;
            }
            //2.那么如果遍历元素为候选元素，那么count++,否则count--
            count = num == res ? count + 1 : count - 1;
        }
        return res;
    }
}
