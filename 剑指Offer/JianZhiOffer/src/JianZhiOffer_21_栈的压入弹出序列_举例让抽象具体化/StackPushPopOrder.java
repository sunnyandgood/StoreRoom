package JianZhiOffer_21_栈的压入弹出序列_举例让抽象具体化;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/14 21:51
 * 21、栈的压入弹出序列
 * 题目描述：
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 *
 * 解题思路：
 * 解决这个问题很直观的想法就是建立一个辅助栈，把输入的第一个序列中的数字依次压入该辅助栈，并按照第二个序列的顺序依次从该栈中弹出数字。
 *
 * 如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。如果下一个弹出的数字不在栈顶，我们把压栈序列中还没有入栈的数字压入辅助栈，
 * 直到把下一个需要弹出的数字压入栈顶为止。如果所有的数字都压入栈了仍然没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。
 */
public class StackPushPopOrder {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null || pushA.length == 0 || popA == null || popA.length == 0){
            return false;
        }
        boolean res = true;
        LinkedList<Integer> stack = new LinkedList<>();
        int pushInex = 0;
        int popInex = 0;
        stack.push(pushA[pushInex++]);
        while (popInex < popA.length){
            while (stack.peek() != popA[popInex]){
                if (pushInex < pushA.length){
                    stack.push(pushA[pushInex++]);
                }else {
                    res = false;
                    break;
                }
            }
            stack.pop();
            popInex++;
        }
        return res;
    }

    public boolean IsPopOrder1(int [] pushA,int [] popA) {
        if (pushA == null || pushA.length == 0 || popA == null || popA.length == 0){
            return false;
        }
        boolean res = true;
        ArrayList<Integer> list = new ArrayList<>();
        int pushInex = 0;
        int popInex = 0;
        list.add(pushA[pushInex++]);
        while (popInex < popA.length){
            while (list.get(list.size()-1) != popA[popInex]){
                if (pushInex < pushA.length){
                    list.add(pushA[pushInex++]);
                }else {
                    res = false;
                    break;
                }
            }
            list.remove(list.size()-1);
            popInex++;
        }
        return res;
    }

    public static void main(String[] args) {
        StackPushPopOrder order = new StackPushPopOrder();
        int[] push = new int[]{1,2,3,4,5};
        int[] pop1 = new int[]{4,5,3,2,1};
        int[] pop2 = new int[]{4,3,5,1,2};
        System.out.println(order.IsPopOrder1(push,pop1));
        System.out.println(order.IsPopOrder1(push,pop2));
    }
}
