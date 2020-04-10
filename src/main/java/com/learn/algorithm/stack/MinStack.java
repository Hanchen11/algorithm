package com.learn.algorithm.stack;

import java.util.Stack;

/**
 * leecode 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * @Author hanchen
 * @Date: 2020/4/9
 */
public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.empty()) {
            minStack.push(x);
        } else {
            if (x <= minStack.peek()) {
                minStack.push(x);
            }
        }
    }

    public int pop() {
        int top = stack.pop();
        if (top == minStack.peek()) {
            minStack.pop();
        }
        return top;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
