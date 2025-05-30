package 剑指offer.数组;

public class No16_两数之和 {
    public static void main(String[] args) {

    }

    //两数之和 ，比较经典的解法是双指针
    public int[] twoSum(int[] price, int target) {
        int left = 0, right = price.length - 1;
        while (left < right) {
            int sum = price[left] + price[right];
            if (sum == target) {
                return new int[]{price[left], price[right]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}
