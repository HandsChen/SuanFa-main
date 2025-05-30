package 剑指offer.数组;

public class No14_在排序数组中查找数字 {
    static int count = 0;

    public static void main(String[] args) {
        System.out.println("args = " + countTarget2(new int[]{2, 2, 3, 4, 4, 4, 5, 6, 6, 8}, 4));
//        System.out.println("args = " + countTarget2(new int[]{1, 2, 3, 5, 7, 9}, 6));
    }

    /*public static int countTarget(int[] scores, int target) {
        binarySearch(scores, target, 0, scores.length - 1);
        return count;
    }

    //1. 这种本质上是退化成了O(n)的时间复杂度
    public static void binarySearch(int[] scores, int target, int low, int high) {
        //终止条件 目标值不在搜索区间
        if (low > high || scores[low] > target || scores[high] < target) {
            return;
        }
        int mid = low + (high - low) / 2;
        if (scores[mid] == target) {
            count++;
        }
        binarySearch(scores, target, low, mid - 1);
        binarySearch(scores, target, mid + 1, high);
    }*/

    //2.这种本质上还是退化成了O(n)的时间复杂度，尤其在当数组均为target时
   /* public static int countTarget1(int[] scores, int target) {
        int i = binarySearchLeftBorder(scores, target, 0, scores.length - 1);
        int j = binarySearchRightBorder(scores, target, 0, scores.length - 1);
        if (i == -1 || j == -1) return 0;
        return j - i + 1;
    }

    //因此可以使用两个二分查找，分别查处目标数字在数组中的左右边界,返回数字的数组索引
    public static int binarySearchLeftBorder(int[] scores, int target, int low, int high) {
        //终止条件 目标值不在搜索区间
        if (low > high || scores[low] > target || scores[high] < target) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (scores[mid] < target) { //左边界在右侧
            return binarySearchLeftBorder(scores, target, mid + 1, high);
        } else if (scores[mid] > target) { //左边界在左侧
            return binarySearchLeftBorder(scores, target, low, mid - 1);
        } else { //两者很有可能找到了边界，所有要寻找到第一个比target小的数
            while ((mid - 1) >= low && scores[mid - 1] >= target) {
                mid--;
            }
            return mid;
        }
    }

    public static int binarySearchRightBorder(int[] scores, int target, int low, int high) {
        //终止条件 目标值不在搜索区间
        if (low > high || scores[low] > target || scores[high] < target) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (scores[mid] < target) { //右边界在右侧
            return binarySearchRightBorder(scores, target, mid + 1, high);
        } else if (scores[mid] > target) { //右边界在左侧
            return binarySearchRightBorder(scores, target, low, mid - 1);
        } else { //两者很有可能找到了边界，所有要寻找到第一个比target大的数
            while ((mid + 1) <= high && scores[mid + 1] <= target) {
                mid++;
            }
            return mid;
        }
    }*/

    public static int countTarget2(int[] scores, int target) {
        int i = binarySearchLeftBorder(scores, target, 0, scores.length - 1);
        int j = binarySearchRightBorder(scores, target, 0, scores.length - 1);

        if (i == -1 || j == -1) return 0;
        return j - i + 1;
    }

    public static int binarySearchLeftBorder(int[] scores, int target, int low, int high) {
        //终止条件 目标值不在搜索区间
        if (low > high || scores[low] > target || scores[high] < target) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (scores[mid] < target) { //左边界在右侧
            return binarySearchLeftBorder(scores, target, mid + 1, high);
        } else { //左边界在左侧
            int i = binarySearchLeftBorder(scores, target, low, mid - 1);
            if (i == -1) {
                return scores[mid] == target ? mid : -1;
            } else {
                return i;
            }
        }
    }

    public static int binarySearchRightBorder(int[] scores, int target, int low, int high) {
        //终止条件 目标值不在搜索区间
        if (low > high || scores[low] > target || scores[high] < target) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (scores[mid] > target) { //右边界在左侧
            return binarySearchRightBorder(scores, target, low, mid - 1);
        } else { //右边界在右侧
            int i = binarySearchRightBorder(scores, target, mid + 1, high);
            if (i == -1) {
                return scores[mid] == target ? mid : -1;
            } else {
                return i;
            }
        }
    }
}
