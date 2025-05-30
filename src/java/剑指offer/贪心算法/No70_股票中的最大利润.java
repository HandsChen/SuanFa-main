package 剑指offer.贪心算法;

import java.util.PriorityQueue;

public class No70_股票中的最大利润 {
    public static void main(String[] args) {

    }

    public static int bestTiming(int[] prices) {
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        int maxBenefit = 0;
        for (int price : prices) {
            if (!smallHeap.isEmpty()) {
                maxBenefit = Math.max(maxBenefit, price - smallHeap.peek());
            }
            smallHeap.offer(price);
        }
        return maxBenefit;
    }

    public static int bestTiming2(int[] prices) {
        int maxBenefit = 0;
        int curMinValue = -1;
        for (int price : prices) {
            if (curMinValue == -1) {
                curMinValue = price; //遍历值
            } else {
                //计算当前最大收益
                maxBenefit = Math.max(maxBenefit, price - curMinValue);
                //更新最小值
                curMinValue = Math.min(curMinValue, price);
            }
        }
        return maxBenefit;
    }
}
