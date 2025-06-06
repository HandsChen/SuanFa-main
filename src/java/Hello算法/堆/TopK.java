package Hello算法.堆;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * top 问题
 */
public class TopK {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};
        Queue<Integer> integers = topKHeap(input,2);
        System.out.println("integers = " + integers);

    }

    /* 基于堆查找数组中最大的 k 个元素 */
    static Queue<Integer> topKHeap(int[] nums, int k) {
        // 初始化小顶堆
        Queue<Integer> heap = new PriorityQueue<Integer>();
        // 将数组的前 k 个元素入堆
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        // 从第 k+1 个元素开始，保持堆的长度为 k
        for (int i = k; i < nums.length; i++) {
            // 若当前元素大于堆顶元素，则将堆顶元素出堆、当前元素入堆
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap;
    }
}


