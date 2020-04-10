package com.learn.algorithm.queue;

/**
 * 循环链表，增加一个变量增加数据大小
 *
 * @Author hanchen
 * @Date: 2020/4/10
 */
public class CircularQueueV2 {
    private int[] items;
    private int size;
    private int capacity;
    private int head = 0;
    private int tail = 0;

    public CircularQueueV2(int capacity) {
        items = new int[capacity];
        this.capacity = capacity;
    }

    public boolean enqueue(int item) {
        if (size == capacity) {
            return false;
        }
        tail = (tail + 1) % capacity;
        items[tail] = item;
        size++;
        return true;
    }

    public int dequeue() {
        if (capacity == 0) {
            return -1;
        }
        int item = items[head];
        head = (head + 1) % capacity;
        size--;
        return item;
    }
}
