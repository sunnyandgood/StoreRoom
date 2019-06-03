package leet_code_0020_有效的括号;

import java.util.LinkedList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/3 12:25
 * 20. 有效的括号
 */
public class Solution {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (Character ch : chars){
            //先将左括号放入栈中，用以后边匹配
            if (ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }else {
                //若开始不是一个左括号，则直接返回false
                if (stack.isEmpty()){
                    return false;
                }else {
                    //将左括号从栈中弹出，用以与右括号匹配
                    Character topChar = stack.pop();
                    if ((topChar=='(' && ch!=')') || (topChar=='{' && ch!='}') || (topChar=='[' && ch!=']')){
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{[]}"));
    }
}
/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 *
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */