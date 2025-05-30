package 剑指offer.数组;

import java.util.PriorityQueue;

public class No10_数据流的中位数 {
    //1. 先排序再找中位数（自己想的），会超时
    /*private List<Integer> res;

    public No10_数据流的中位数() {
        this.res = new ArrayList<>();
    }

    public void addNum(int num) {
        this.res.add(num);
    }

    public double findMedian() {

        int len = this.res.size();
        if (len < 2) {
            return (double) res.get(0);
        }
        Collections.sort(this.res); //每次找中位数之前先排序
        int tmp = len % 2;
        if (tmp == 0) { //如果是偶数
            return (this.res.get(len / 2 - 1) + this.res.get(len / 2)) / 2.0;
        } else { //如果是奇数
            return (double) this.res.get(len / 2);
        }
    }*/

    //2. 大小根堆，动态调整根堆数据差异
  /*  private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public No10_数据流的中位数() {
        minHeap = new PriorityQueue<>(); //新建小顶堆
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); //新建大顶堆
    }

    public void addNum(int num) {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        if (minHeapSize > maxHeapSize) {
            maxHeap.offer(num); //向大根堆中增加元素
        } else { //像小根堆中增加元素
            minHeap.offer(num);
        }
        //每次增加完元素后进行大小堆调整
        while ((!minHeap.isEmpty() && !maxHeap.isEmpty()) && minHeap.peek() < maxHeap.peek() && Math.abs(minHeap.size() - maxHeap.size()) <= 1) {
            //调整方式为
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            } else {
                minHeap.offer(maxHeap.poll());
            }
            //使大小根堆均衡
            if (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
                if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                } else {
                    minHeap.offer(maxHeap.poll());
                }
            }
        }
    }

    public double findMedian() {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        if (minHeapSize > maxHeapSize) {
            return minHeap.peek();
        } else if (maxHeapSize > minHeapSize) {
            return maxHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }*/


    //3.优化后的大小根堆
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public No10_数据流的中位数() {
        minHeap = new PriorityQueue<>(); //新建小顶堆(用来放大的数)
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); //新建大顶堆（用来放小的数）
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            //平衡堆的数量
            if ((maxHeap.size() - minHeap.size()) > 1) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(num);
            //平衡堆的数量
            /*if ((minHeap.size() - maxHeap.size()) > 1) {
                maxHeap.offer(minHeap.poll());
            }*/
            //可以进一步优化
            if ((minHeap.size() - maxHeap.size()) > 0) {
                maxHeap.offer(minHeap.poll());
            }
        }
    }

    public double findMedian() {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        int totalSize = minHeapSize + maxHeapSize;
        if (totalSize % 2 == 0) { //如果添加总的数据个数是偶数
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else { //如果是奇数
            return  maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        No10_数据流的中位数 myRun = new No10_数据流的中位数();
        myRun.addNum(1);
        myRun.addNum(2);
        System.out.println("myRun = " + myRun.findMedian());
        myRun.addNum(3);
        System.out.println("myRun = " + myRun.findMedian());
    }
}
