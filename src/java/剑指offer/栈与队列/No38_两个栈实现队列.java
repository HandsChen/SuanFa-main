package 剑指offer.栈与队列;

import java.util.Stack;

public class No38_两个栈实现队列 {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public static void main(String[] args) {

    }

    //初始化两个栈
    public No38_两个栈实现队列() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        this.stack1.push(value);
    }

    public int deleteHead() {
        if (this.stack2.isEmpty() && this.stack1.isEmpty()) {
            return -1;
        }
        if (this.stack2.isEmpty()) {
            while (!this.stack1.isEmpty()) {
                this.stack2.push(this.stack1.pop());
            }
        }
        return this.stack2.pop();
    }
}
