package 剑指offer.数组;

import java.util.Arrays;
import java.util.PriorityQueue;

public class No9_最小的k个数 {
    public static void main(String[] args) {
        int[] stock = {2, 5, 7, 4};
        System.out.println("stock = " + Arrays.toString(inventoryManagement2(stock, 2)));
    }

    //top k问题
    //使用优先级队列1
    static public int[] inventoryManagement(int[] stock, int cnt) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : stock) {
            pq.add(i);
        }
        int[] res = new int[cnt];
        int index = 0;
        while (!pq.isEmpty() && index < cnt) {
            res[index++] = pq.poll();
        }

        return res;
    }

    static public int[] inventoryManagement2(int[] stock, int cnt) {
        if (cnt == 0) {
            return new int[0];
        }
        int len = stock.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(cnt, ((o1, o2) -> o2 - o1)); //创建大根堆
        //首先让前cnt个入大根堆
        int i = 0;
        while (i < cnt) {
            pq.add(stock[i]);
            i++;
        }
        //从第cnt个开始与堆顶比较
        while (i < len) {
            if (stock[i] < pq.peek()) {
                pq.poll(); //首先退出堆顶元素
                pq.add(stock[i]);
            }
            i++;
        }
        int[] res = new int[cnt];
        while (!pq.isEmpty()) {
            res[--cnt] = pq.poll();
        }
        return res;
    }
}
