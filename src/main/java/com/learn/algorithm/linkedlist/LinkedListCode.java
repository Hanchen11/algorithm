package com.learn.algorithm.linkedlist;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author hanchen
 * @Date: 2020/4/8
 */
@Slf4j
public class LinkedListCode {

    /**
     * leecode 234 回文链表
     * 请判断一个链表是否为回文链表
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 翻转slow之后的节点
        ListNode pre = null;
        ListNode curNode = slow.next;
        ListNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = pre;
            pre = curNode;
            curNode = nextNode;
        }

        while (pre != null) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }

        return true;
    }

    /**
     * leecode 206
     * 反转一个单链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode before = null;
        ListNode after;
        ListNode current = head;
        while (current != null) {
            after = current.next;
            current.next = before;
            before = current;
            current = after;
        }

        return before;
    }

    /**
     * leoocode 141. 环形链表
     * 给定一个链表，判断链表中是否有环。
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * leecode 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int temp = x % 10 + res * 10;
            if ((temp - x % 10) / 10 != res) { //最关键的一步
                return 0;
            }
            res = temp;
            x /= 10;
        }
        return res;
    }

    /**
     * leecode 21. 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                node.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            node = node.next;
        }

        if (l1 == null) {
            while (l2 != null) {
                node.next = new ListNode(l2.val);
                l2 = l2.next;
                node = node.next;
            }
        }
        if (l2 == null) {
            while (l1 != null) {
                node.next = new ListNode(l1.val);
                l1 = l1.next;
                node = node.next;
            }
        }
        return head.next;
    }

    /**
     * leecode 19. 删除链表的倒数第N个节点
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        // 哨兵节点，处理边界情况
        ListNode preNode = new ListNode(0);
        preNode.next = head;

        ListNode fast = preNode;
        ListNode slow = preNode;
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return preNode.next;
    }

    /**
     * leecode 876. 链表的中间结点
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next != null) {
            return slow.next;
        } else {
            return slow;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(6);

        node.next = node1;
        node1.next = node2;
//        node2.next = node3;

        log.info("result = {}", JSON.toJSON(middleNode(node)));
    }
}