package com.learn.algorithm.queue;


import com.learn.algorithm.linkedlist.ListNode;

/**
 * 链表实现队列
 *
 * @Author hanchen
 * @Date: 2020/4/9
 */
public class LinkedQueue {

    private ListNode head = new ListNode();
    private ListNode tail = null;

    public boolean enqueue(int data) {
        if (tail == null) {
            tail = new ListNode(data);
            head.next = tail;
        } else {
            tail.next = new ListNode(data);
            tail = tail.next;
        }
        return true;
    }

    public int dequeue() {
        if (tail == null) {
            return -1;
        }

        ListNode result = head.next;
        head = head.next;
        return result.val;
    }
}
