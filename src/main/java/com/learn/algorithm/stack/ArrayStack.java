package com.learn.algorithm.stack;

/**
 * 数组实现栈
 * @Author hanchen
 * @Date: 2020/4/9
 */
public class ArrayStack<T> {
    private Object[] items;
    private int count; //栈元素大小
    private int n; //栈的大小

    public ArrayStack(int n) {
        this.items = new Object[n];
        this.count = 0;
        this.n = n;
    }

    public boolean push(T data) {
        if (count == n) {
            return false;
        }
        items[count] = data;
        ++count ;
        return true;
    }

    public T pop() {
        if (count == 0) {
            return null;
        }
        Object data = items[count-1];
        --count;
        return (T)data;
    }

    public T top() {
        if (count == 0) {
            return null;
        }
        return (T)(items[count-1]);
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
