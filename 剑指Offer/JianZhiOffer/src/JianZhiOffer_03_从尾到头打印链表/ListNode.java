package JianZhiOffer_03_从尾到头打印链表;

public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(){

    }

    ListNode create(int[] arr){
        ListNode node = new ListNode(arr[0]);//创建一个节点
        ListNode preNode = node;//将创建好的节点赋值给前驱节点
        ListNode head = preNode;//创建头节点
        for (int i=1;i<arr.length;i++){
            node = new ListNode(arr[i]);
            node.next = null;
            preNode.next = node;
            preNode = node;
        }
        return head;
    }
}