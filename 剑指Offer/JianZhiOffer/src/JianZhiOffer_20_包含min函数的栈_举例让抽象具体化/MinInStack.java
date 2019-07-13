package JianZhiOffer_20_包含min函数的栈_举例让抽象具体化;

import java.util.Stack;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/13 22:55
 * 20、包含min函数的栈
 * 题目描述：
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * 解题思路：
 * 在栈里添加一个成员变量存放最小的元素。每次压入一个新元素进栈的时候，如果该元素比当前最小的元素还要小，则更新最小元素。
 * 分析到这里我们发现仅仅添加一个成员变量存放最小元素是不够的，也就是说当最小元素被弹出栈的时候，
 * 我们希望能够得到次小元素。因此在压入这个最小元素之前，我们要把次小元素保存起来。
 *
 * 3,4,2,1
 * 首先往空的数据栈里压入数字3，显然现在3是最小值，我们也把这个最小值压入辅助栈。接下来往数据栈里压入数字4。
 * 由于4大于之前的最小值，因此我们仍然往辅助栈里压入数字3。第三步继续往数据栈里压入数字2。
 * 由于2小于之前的最小值3，因此我们把最小值更新为2，并把2压入辅助栈。
 * 同样当压入数字1时，也要更新最小值，并把新的最小值1压入辅助栈。
 *
 * 如果每次都把最小元素压入辅助栈，那么就能保证辅助栈的栈顶一直都是最小元素。
 * 当最小元素从数据栈内被弹出之后，同时弹出辅助栈的栈顶元素，此时辅助栈的新栈顶元素就是下一个最小值。
 */
public class MinInStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        stack.push(node);
        if (minStack.isEmpty() || minStack.peek() > node){
            minStack.push(node);
        }else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
