package 剑指offer.数组;

import java.util.*;

public class No18_滑动窗口最大值 {
    public static void main(String[] args) {
//        System.out.println("args = " + Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println("args = " + Arrays.toString(maxSlidingWindow3(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    //1.一个显然的方法是暴力法，不过可能会超时，这里并没有尝试
    //2. 尝试使用动态规划计算,这里做得还是有问题，时间复杂度虽然总体比暴力法小，但是还是最大时还是O(n)
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] dp = new int[nums.length - k + 1]; //创建dp数组
        //先找出滑动窗口还没移动时，窗口内最大的数字
        int idx = 0;
        int maxOfWindowInit = Integer.MIN_VALUE;
        while (idx < k) {
            maxOfWindowInit = Math.max(maxOfWindowInit, nums[idx]);
            idx++;
        }
        //此时就获得了dp的初始值
        int cursor = 0;
        dp[cursor++] = maxOfWindowInit;
        //然后窗口开始滑动
        for (int i = k; i < nums.length; i++) {
            if (nums[i] >= dp[i - k]) { //当滑动窗口即将新加入的数大于当前滑动窗口的最大值时，变更滑动窗口的最大值
                dp[cursor] = nums[i];
            } else { //小于时可能有点麻烦，原因在于原来滑动窗口内的最大值可能被移除，所以此时要分情况进行讨论
                if (nums[i - k] == dp[i - k]) { //原最大值被移除
                    int maxInWindow = Integer.MIN_VALUE;
                    for (int j = i - k + 1; j <= i; j++) {
                        maxInWindow = Math.max(maxInWindow, nums[j]);
                    }
                    dp[cursor] = maxInWindow;
                } else { //原最大值未被移除，
                    dp[cursor] = dp[cursor - 1]; //继承最大值
                }
            }
            cursor++;
        }
        return dp;
    }

    //3. 使用优先级队列,时间复杂度最大为O(n*log^n)
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]); //创建大根堆
        //首先将滑动初始化时，窗口内的所有数据入队列
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        //然后创建dp数组
        int[] dp = new int[nums.length - k + 1];
        dp[0] = queue.peek() != null ? queue.peek()[0] : 0;

        for (int i = k; i < nums.length; i++) {
            //首先判断当前堆顶元素是否位于窗口最左侧(或者小于，小于的话可能是之前留下的废值)，如果是，那么就不断将其抛掉
            while (queue.peek() != null && queue.peek()[1] < (i - k + 1)) { //这里容易死循环，因为优先级队列每次弹出后没有获取peek没有获取新的值
                queue.poll(); //抛掉
            }
            queue.offer(new int[]{nums[i], i}); //将新元素加入堆中
            dp[i - k + 1] = queue.peek() != null ? queue.peek()[0] : 0;
        }
        return dp;
    }

    //4. 使用单调队列
    public static int[] maxSlidingWindow3(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>(); //用来存储数字的索引
        //首先将窗口内的元素入队
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) { //
                deque.pollLast();
            }
            deque.offer(i); //新增最新的索引入队
        }
        int[] dp = new int[nums.length - k + 1]; //构建dp数组
        dp[0] = deque.peekFirst() != null ? nums[deque.peekFirst()] : 0;
        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) { //
                deque.pollLast();
            }
            deque.offer(i); //新增最新的索引入队
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) { //
                deque.pollFirst();
            }
            dp[i - k + 1] = nums[deque.getFirst()];
        }
        return dp;
    }

}

