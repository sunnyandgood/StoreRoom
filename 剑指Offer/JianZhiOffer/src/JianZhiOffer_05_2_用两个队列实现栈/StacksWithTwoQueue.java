package JianZhiOffer_05_2_用两个队列实现栈;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/16 22:54
 * 用两个队列实现栈
 * 题目描述：
 * 用两个队列来实现一个栈，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 解题思路：
 * 入栈：将值放到queue1中
 * 出栈：将queue1末尾的值放到queue2中，并将queue1末尾的值移除，然后返回queue2的第一个值。
 */
public class StacksWithTwoQueue {
    Queue<Integer> queue1 = new ArrayDeque<>();
    Queue<Integer> queue2 = new ArrayDeque<>();
    public void push(int node) {
        queue1.add(node);
    }

    public int pop() {
        int value;
        int size = queue1.size();
        int i = 0;
        Iterator<Integer> iterator = queue1.iterator();
        while (iterator.hasNext()){
            Integer val = iterator.next();
            if (i == size-1){
                queue2.add(val);
                iterator.remove();
                break;
            }
            i++;
        }
        value = queue2.peek();
        queue2.remove();
        return value;
    }

    public static void main(String[] args) {
        StacksWithTwoQueue stack = new StacksWithTwoQueue();
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        for (int val : arr){
            stack.push(val);
        }
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
