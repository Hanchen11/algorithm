package com.learn.algorithm.queue;

/**
 * 数组实现队列
 *
 * @Author hanchen
 * @Date: 2020/4/9
 */
public class ArrayQueue {
    private String[] items;
    private int head = 0;
    private int tail = 0;
    private int count;

    public ArrayQueue(int size) {
        items = new String[size];
        count = size;
    }

    public boolean enqueue(String item) {
        if (tail == count) {
            // 说明整个数组都满了，直接返回失败
            if (head == 0) {
                return false;
            }

            // 数据搬移到从0开始记
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }

        items[tail++] = item;
        return true;
    }

    public String dequeue() {
        if (head == tail) {
            return null;
        }
        return items[head++];
    }
}
