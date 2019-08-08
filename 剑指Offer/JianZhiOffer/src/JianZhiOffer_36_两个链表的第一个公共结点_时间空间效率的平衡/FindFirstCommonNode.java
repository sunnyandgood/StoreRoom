package JianZhiOffer_36_两个链表的第一个公共结点_时间空间效率的平衡;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/8 23:19
 * 36、两个链表的第一个公共结点
 * 题目描述
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class FindFirstCommonNode {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
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
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}