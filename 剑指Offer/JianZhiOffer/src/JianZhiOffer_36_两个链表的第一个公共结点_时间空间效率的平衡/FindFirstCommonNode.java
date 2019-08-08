package JianZhiOffer_36_两个链表的第一个公共结点_时间空间效率的平衡;

import java.util.LinkedList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/8 23:19
 * 36、两个链表的第一个公共结点
 * 题目描述
 * 输入两个链表，找出它们的第一个公共结点。
 *
 */
public class FindFirstCommonNode {
    /**
     * 方法一：
     * 首先遍历两个链表得到它们的长度，就能知道哪个链表比较长，以及长的链表比短的链表多几个结点。
     * 在第二次遍历的时候，在较长的链表上先走若干步，接着再同时在两个链表上遍历，
     * 找到的第一个相同的结点就是它们的第一个公共结点。
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null){
            return null;
        }
        int length1 = this.getListNodeLength(pHead1);
        int length2 = this.getListNodeLength(pHead2);
        ListNode pLongList = pHead1;
        ListNode pShortList = pHead2;
        int subLength = length1 - length2;
        if (length1 < length2){
            pLongList = pHead2;
            pShortList = pHead1;
            subLength = length2 - length1;
        }
        //先在长链表上走几步，再同时在两链表上遍历
        for (int i = 0;i < subLength;i++){
            pLongList = pLongList.next;
        }
        while (pLongList != null && pShortList != null && pLongList != pShortList){
            pLongList = pLongList.next;
            pShortList = pShortList.next;
        }
        //得到第一个公共节点
        ListNode firstCommonNode = pLongList;
        return firstCommonNode;
    }

    private int getListNodeLength(ListNode pHead){
        if (pHead == null){
            return 0;
        }
        int length = 0;
        while (pHead != null){
            length += 1;
            pHead = pHead.next;
        }
        return length;
    }

    /**
     * 方法二：
     * 分别把两个链表的结点放入两个栈里，这样两个链表的尾结点就位于两个栈的栈顶，接下来比较两个栈顶的结点是否相同。
     * 如果相同，则把栈顶弹出接着比较下一个栈顶，直到找到最后一个相同的结点。
     */
    public ListNode FindFirstCommonNode_1(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null){
            return null;
        }
        LinkedList<ListNode> stack1 = new LinkedList<>();
        LinkedList<ListNode> stack2 = new LinkedList<>();
        while (pHead1 != null){
            stack1.add(pHead1);
            pHead1 = pHead2.next;
        }
        while (pHead2 != null){
            stack2.add(pHead2);
            pHead2 = pHead1.next;
        }
        ListNode firstCommonNode = null;
        while (stack1.size() != 0 && stack2.size() != 0){
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();
            if (node1 != node2){
                break;
            }
            firstCommonNode = node1;
        }
        return firstCommonNode;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        ListNode listNode1 = listNode.create(new int[]{1, 2, 3, 6, 7});
        ListNode listNode2 = listNode.create(new int[]{4, 5, 6, 7});
        FindFirstCommonNode find = new FindFirstCommonNode();
        ListNode node = find.FindFirstCommonNode_1(listNode1, listNode2);
        System.out.println(node);
    }
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
    ListNode() {

    }

    ListNode create(int[] arr){
        ListNode head = null;
        if (arr.length >= 2){
            head = new ListNode(arr[0]);
            head.next = null;
            ListNode preNode = head;
            ListNode curNode;
            for (int i=1;i<arr.length;i++){
                curNode = new ListNode(arr[i]);
                preNode.next = curNode;
                preNode = curNode;
            }
        }else if (arr.length == 1){
            head = new ListNode(arr[0]);
        }
        return head;
    }
}