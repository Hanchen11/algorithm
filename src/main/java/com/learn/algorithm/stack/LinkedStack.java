package com.learn.algorithm.stack;

import com.learn.algorithm.linkedlist.ListNode;

/**
 * 链表实现栈
 * @Author hanchen
 * @Date: 2020/4/9
 */
public class LinkedStack {
    private ListNode top = null;

    public void push(int val) {
        ListNode newNode = new ListNode(val);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    public int pop() {
        if (top == null) {
            return -1;
        }
        int val = top.val;
        top = top.next;
        return val;
    }
}
