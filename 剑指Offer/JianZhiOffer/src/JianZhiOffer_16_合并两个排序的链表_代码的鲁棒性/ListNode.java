package JianZhiOffer_16_合并两个排序的链表_代码的鲁棒性;

public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(){

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