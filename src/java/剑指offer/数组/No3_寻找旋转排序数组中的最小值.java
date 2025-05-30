package 剑指offer.数组;

public class No3_寻找旋转排序数组中的最小值 {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println("nums = " + findMin(nums));
    }

    public static int findMin(int[] nums) {
        int len = nums.length;
        if (len == 0) return -1;
        if (len == 1) return nums[0];
        //数组中至少有两个值寻找最小值才有意义
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) { //应该忽略右半边
                high = mid;
            } else if (nums[mid] > nums[high]) { //应该忽略左半边
                low = mid + 1;
            } else {
                return nums[mid];
            }
        }
        return -1;
    }
}
