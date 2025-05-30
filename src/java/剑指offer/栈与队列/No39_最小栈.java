package 剑指offer.栈与队列;

import java.util.ArrayList;
import java.util.List;

public class No39_最小栈 {
    private List<Integer> minValueDp;
    private List<Integer> stack;

    public static void main(String[] args) {

    }

    /**
     * initialize your data structure here.
     */
    public No39_最小栈() {
        this.stack = new ArrayList<>();
        this.minValueDp = new ArrayList<>();
        this.minValueDp.add(Integer.MAX_VALUE);
    }

    public void push(int x) {
        this.stack.add(x);
        this.minValueDp.add(Math.min(x, this.minValueDp.get(this.minValueDp.size() - 1)));
    }

    public void pop() {
        if (!stack.isEmpty()) {
            this.stack.remove(this.stack.size() - 1);
            this.minValueDp.remove(this.minValueDp.size() - 1);
        }
    }

    public int top() {
        if (this.stack.isEmpty()) {
            return 0;
        }
        return this.stack.get(this.stack.size() - 1);
    }

    public int getMin() {
        return this.minValueDp.get(this.minValueDp.size() - 1);
    }
}
