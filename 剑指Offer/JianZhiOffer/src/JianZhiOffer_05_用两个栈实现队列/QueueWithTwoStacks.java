package JianZhiOffer_05_用两个栈实现队列;

import java.util.Stack;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/16 18:25
 *
 * 5、用两个栈实现队列
 * 题目描述：
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 解题思路：
 * 入队：将值压入stack1
 * 出队：先判断stack2是否为空，若不为空则直接弹出；若为空则先将stack1中的值压入stack2再弹出
 */
public class QueueWithTwoStacks {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        int value;
        if (!stack2.isEmpty()){
            value = stack2.pop();
        }else {
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            value = stack2.pop();
        }
        return value;
    }

    public static void main(String[] args) {
        QueueWithTwoStacks queue = new QueueWithTwoStacks();
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
        for (int val : arr){
            queue.push(val);
        }
        System.out.println(queue.pop());
    }
}
