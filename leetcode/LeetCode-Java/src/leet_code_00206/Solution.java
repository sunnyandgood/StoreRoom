package leet_code_00206;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/14 0:34
 * 206. 反转链表
 */
public class Solution {
    //迭代：借助三个指针实现
    public ListNode reverseList1(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next;
        if (head==null){
            return null;
        }else {
            while (cur!=null){
                next = cur.next;
                // 进行变换
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

    //递归
    public ListNode reverseList(ListNode head) {
        // 递归到最后一个节点
        if (head == null || head.next == null) {
            return head;
        }
        // 将最后一个节点作为新的头节点保留
        ListNode newHead = reverseList(head.next);
        // 将后一个节点指向当前节点
        head.next.next = head;
        // 防止在原头节点处成环
        head.next = null;
        return newHead;
    }

    //迭代：dummyHead + 头插法
    public ListNode reverseList2(ListNode head) {
        // 虚拟头结点一开始并不连接到链表头结点（或者理解为构造了一条新的链表更为合适）
        ListNode dummyHead = new ListNode(0);
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            cur.next = dummyHead.next;
            dummyHead.next = cur;
        }
        return dummyHead.next;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。
 */