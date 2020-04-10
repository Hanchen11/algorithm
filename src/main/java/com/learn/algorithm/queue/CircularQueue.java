package com.learn.algorithm.queue;

/**
 * 循环队列，tail指向下一个空位置，循环链表浪费一个空间
 *
 * @Author hanchen
 * @Date: 2020/4/9
 */
public class CircularQueue {
    private int[] items;
    private int head = 0;
    private int tail = 0;
    private int size;

    public CircularQueue(int size) {
        this.size = size;
        items = new int[size];
    }

    public boolean enqueue(int item) {
        if ((tail + 1) % size == head) {
            return false;
        }

        items[tail] = item;
        tail = (tail + 1) % size;
        return true;
    }

    public int dequeue() {
        if (head == tail) {
            return -1;
        }

        int item = items[head];
        head = (head + 1) % size;
        return item;
    }
}

