package Hello算法.堆;

import java.util.PriorityQueue;
import java.util.Queue;

public class Heap {
    //初始化
    private static final Queue<Integer> minHeap = new PriorityQueue<>();
    private static final Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    public static void main(String[] args) {
        /*元素入堆*/
        maxHeap.offer(1);
        maxHeap.offer(3);
        maxHeap.offer(2);
        maxHeap.offer(5);
        maxHeap.offer(4);

        /*获取栈顶元素*/
        Integer peek = maxHeap.peek();
        System.out.println("大顶堆的栈顶元素为 = " + peek);
    }
}
