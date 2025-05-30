package 剑指offer.栈与队列;

import java.util.*;

public class No41_设计自助结算系统 {
    public static void main(String[] args) {

    }

    /* 自己写的，偏暴力，主要耗时在add部分
    private final List<Integer> stack;
    private final Queue<Integer> maxValue;

    public No41_设计自助结算系统() {
        this.stack = new ArrayList<>();
        this.maxValue = new PriorityQueue<>(Collections.reverseOrder()); //大根堆
    }

    public int get_max() {
        if (this.maxValue.isEmpty()) {
            return -1;
        }
        return this.maxValue.peek();
    }

    public void add(int value) {
        this.stack.add(value);
        this.maxValue.offer(value);
    }

    public int remove() {
        if (!stack.isEmpty()) {
            Integer rmTarget = this.stack.get(0);
            this.maxValue.remove(rmTarget);
            this.stack.remove(0);
            return rmTarget;
        } else {
            return -1;
        }
    }*/
    //2.单调队列
    /*private final List<Integer> stack;
    private final List<Integer> maxValue;

    public No41_设计自助结算系统() {
        this.stack = new ArrayList<>();
        this.maxValue = new ArrayList<>();
    }

    public int get_max() {
        if (this.maxValue.isEmpty()) {
            return -1;
        }
        return this.maxValue.get(0);
    }

    public void add(int value) {
        //增加新元素的时候，将当前maxValue中所有比value的元素取出
        while (!this.maxValue.isEmpty() && this.maxValue.get(this.maxValue.size() - 1) < value) {
            this.maxValue.remove(this.maxValue.size() - 1);
        }
        this.maxValue.add(value);
        //常规队列中记录
        this.stack.add(value);
    }

    public int remove() {
        if (!stack.isEmpty()) {
            Integer rmTarget = this.stack.get(0);
            this.stack.remove(0); //移除头部元素，如果继续使用arrayList会导致数据复制，无法做到O(1)
            if (rmTarget.equals(this.maxValue.get(0))) {
                this.maxValue.remove(0);
            }
            return rmTarget;
        } else {
            return -1;
        }
    }*/

    // 单调队列进一步修改
    private final LinkedList<Integer> buffer;
    private final LinkedList<Integer> maxValue;

    public No41_设计自助结算系统() {
        this.buffer = new LinkedList<>();
        this.maxValue = new LinkedList<>();
    }

    public int get_max() {
        if (this.maxValue.isEmpty()) {
            return -1;
        }
        return this.maxValue.getFirst();
    }

    public void add(int value) {
        //增加新元素的时候，将当前maxValue中所有比value的元素取出
        while (!this.maxValue.isEmpty() && this.maxValue.getLast() < value) {
            this.maxValue.removeLast();
        }
        this.maxValue.offer(value);
        //常规队列中记录
        this.buffer.offer(value);
    }

    public int remove() {
        if (!buffer.isEmpty()) {
            Integer rmTarget = this.buffer.getFirst();
            this.buffer.removeFirst(); //移除头部元素，如果继续使用arrayList会导致数据复制，无法做到O(1)
            if (rmTarget.equals(this.maxValue.getFirst())) {
                this.maxValue.removeFirst();
            }
            return rmTarget;
        } else {
            return -1;
        }
    }
}
