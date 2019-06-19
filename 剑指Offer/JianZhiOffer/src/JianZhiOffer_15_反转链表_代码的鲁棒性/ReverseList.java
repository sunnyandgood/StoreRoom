package JianZhiOffer_15_反转链表_代码的鲁棒性;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/19 15:17
 * 15、反转链表
 * 题目描述
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList {
    /**
     * 迭代：借助三个指针实现
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode;
        while (curNode != null){
            nextNode = curNode.next;
            if (nextNode == null){
                head = curNode;
            }
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return head;
    }

    /**
     * 递归实现
     */
    public ListNode ReverseList1(ListNode head) {
        //递归到最后一个节点
        if (head == null || head.next == null){
            return head;
        }
        // 将最后一个节点作为新的头节点保留
        ListNode newHead = ReverseList(head.next);
        // 将后一个节点指向当前节点
        head.next.next = head;
        // 防止在原头节点处成环
        head.next = null;
        return newHead;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        ListNode listNode = new ListNode().create(arr);
        ReverseList list = new ReverseList();
        listNode = list.ReverseList(listNode);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
    ListNode(){

    }
    ListNode create(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        if (arr.length > 1){
            ListNode node = head;
            for (int i=1;i<arr.length;i++){
                ListNode newNode = new ListNode(arr[i]);
                node.next = newNode;
                node = newNode;
            }
        }
        return head;
    }
}