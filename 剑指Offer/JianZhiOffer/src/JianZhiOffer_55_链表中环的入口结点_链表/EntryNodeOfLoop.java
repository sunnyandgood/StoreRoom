package JianZhiOffer_55_链表中环的入口结点_链表;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/5 23:28
 * 55、链表中环的入口结点
 * 题目描述：
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class EntryNodeOfLoop {
    public ListNode EntryNodeOfLoop(ListNode pHead){
        if (pHead == null){
            return null;
        }
        boolean hasLoop = false;
        ListNode slow = pHead;
        ListNode fast = pHead;
        // 先判断是否有循环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }
        if (hasLoop) {
            while (pHead != slow) {
                pHead = pHead.next;
                slow = slow.next;
            }
            return pHead;
        }
        return null;
    }
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}