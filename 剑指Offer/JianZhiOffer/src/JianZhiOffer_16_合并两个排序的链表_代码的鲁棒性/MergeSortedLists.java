package JianZhiOffer_16_合并两个排序的链表_代码的鲁棒性;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/21 23:27
 * 16、合并两个排序的链表
 * 题目描述：
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 解题思路：
 * 链表1:1,3,5,7
 * 链表2:2,4,6,8
 * 首先分析合并两个链表的过程。我们的分析从合并两个链表的头结点开始。链表1的头结点的值小于链表2的头结点的值，因此链表1的头结点将是合并后链表的头结点。
 * 我们继续合并两个链表中剩余的结点；
 * 在两个链表中剩下的结点依然是排序的，因此合并这两个链表的步骤和前面的步骤是一样的。我们还是比较两个头结点的值。
 * 此时链表2的头结点的值小于链表1的头结点的值，因此链表2的头结点的值将是合并剩余结点得到的链表的头结点。
 * 我们把这个结点和前面合并链表时得到的链表的尾结点（值为1的结点）链接起来。
 * 当我们得到两个链表中值较小的头结点并把它链接到已经合并的链表之后，两个链表剩余的结点依然是排序的，因此合并的步骤和之前的步骤是一样的。
 * 这就是典型的递归的过程，我们可以定义递归函数完成这一合并过程。
 */
public class MergeSortedLists {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        ListNode head;
        if (list1.val < list2.val){
            head = list1;
            head.next = Merge(list1.next,list2);
        }else {
            head = list2;
            head.next = Merge(list1,list2.next);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        int[] arr1 = new int[]{1,3,5,7};
        int[] arr2 = new int[]{2,4,6,8};
        ListNode listNode1 = listNode.create(arr1);
        ListNode listNode2 = listNode.create(arr2);
        listNode = new MergeSortedLists().Merge(listNode1, listNode2);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
