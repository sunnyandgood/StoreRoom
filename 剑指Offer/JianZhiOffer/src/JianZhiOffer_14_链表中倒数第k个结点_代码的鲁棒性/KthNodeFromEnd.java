package JianZhiOffer_14_链表中倒数第k个结点_代码的鲁棒性;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/19 14:17
 * 14、链表中倒数第k个结点
 * 题目描述:
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class KthNodeFromEnd {
    /**
     * 定义两个指针，第一个指针从链表的头指针开始遍历向前走k－1，第二个指针保持不动；
     * 从第k步开始，第二个指针也开始从链表的头指针开始遍历。
     * 由于两个指针的距离保持在k－1，当第一个（走在前面的）指针到达链表的尾结点时，第二个指针（走在后面的）指针正好是倒数第k个结点。
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k <= 0){
            return null;
        }
        ListNode frontNode = head;
        ListNode behindNode = head;
        for (int i = 0;i < k -1;i++){
            if (frontNode.next != null){
                frontNode = frontNode.next;
            }else {//当k的值大于链表长度时
                return null;
            }
        }
        while (frontNode.next != null){
            frontNode = frontNode.next;
            behindNode = behindNode.next;
        }
        return behindNode;
    }

    /**
     * 假设整个链表有n个结点，那么倒数第k个结点就是从头结点开始的第n－k＋1个结点。
     * 需要遍历链表两次，第一次统计出链表中结点的个数，第二次就能找到倒数第k个结点。
     */
    public ListNode FindKthToTail1(ListNode head,int k) {
        if (head == null || k <= 0){
            return null;
        }
        ListNode node = head;
        int length = 1;
        while (node.next != null){
            node = node.next;
            length++;
        }
        int index = length - k +1;
        node = head;
        if (index > 0){
            int count = 1;
            while (node.next != null){
                if (count == index){
                    break;
                }
                node = node.next;
                count++;
            }
        }else {//当k的值大于链表长度时
            return null;
        }
        return node;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        ListNode listNode = new ListNode().create(arr);
        KthNodeFromEnd kthNodeFromEnd = new KthNodeFromEnd();
//        System.out.println(kthNodeFromEnd.FindKthToTail(listNode,1).val);
        System.out.println(kthNodeFromEnd.FindKthToTail1(listNode,9).val);

//        while (listNode!=null){
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }
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
        if (arr == null || arr.length ==0){
            return null;
        }
        ListNode head = init(arr[0]);
        if (arr.length > 1){
            ListNode preNode = head;
            ListNode node;
            for (int i=1;i<arr.length;i++){
                node = new ListNode(arr[i]);
                preNode.next = node;
                preNode = node;
            }
        }
        return head;
    }
    ListNode init(int num){
        ListNode head = new ListNode(num);
        head.next = null;
        return head;
    }
}