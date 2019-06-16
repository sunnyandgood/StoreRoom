package JianZhiOffer_03_从尾到头打印链表;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/16 10:33
 * 3、从头到尾打印链表
 * 题目描述：
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class PrintListReversingly {
    /**
     * 用栈（先进后出，后进先出）来实现：
     * 现将链表数据压入栈，然后再弹出栈
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();
        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }

    /**
     * 先反转链表，在遍历链表。缺点：会改变链表的结构
     * 时间复杂度为O(n)
     */
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode pre = null;
        ListNode next = null;
        //反转链表
        while (listNode != null){
            next = listNode.next;
            listNode.next = pre;
            pre = listNode;
            listNode = next;
        }
        listNode = pre;
        //遍历链表
        while (listNode != null){
            list.add(listNode.val);
            listNode = listNode.next;
        }
        return list;
    }

    /**
     * 递归实现
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode preNode = listNode;
        if (preNode != null){
            if (preNode.next != null){
                list = printListFromTailToHead2(preNode.next);
            }
            list.add(preNode.val);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        ListNode listNode = new ListNode().create(arr);
        PrintListReversingly print = new PrintListReversingly();
        System.out.println(print.printListFromTailToHead1(new ListNode()));
    }
}
