package Hello算法.堆;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 堆基础
 */
public class HeapBase {
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

        /*堆顶元素出堆*/
        //出堆元素会形成一个从大到小的序列
        peek =maxHeap.poll(); //5
        peek =maxHeap.poll(); //4
        peek =maxHeap.poll(); //3
        peek =maxHeap.poll(); //2
        peek =maxHeap.poll(); //1
        /*获取堆大小*/
        int size= maxHeap.size();
        /*判断堆是否为空*/
        boolean isEmpty = maxHeap.isEmpty();
        System.out.println("isEmpty = " + isEmpty);
        /* 输入列表并建堆 */
//        minHeap = new PriorityQueue<>(Arrays.asList(1, 3, 2, 5, 4));
    }
}
