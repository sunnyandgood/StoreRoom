package leet_code_0009_回文数;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/26 12:47
 * 9. 回文数
 */
public class Solution {
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        String string = new StringBuffer(str).reverse().toString();
        return str.equals(string);
    }

    public boolean isPalindrome1(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int num = x;
        int result = 0;
        while (num > 0){
            result = result * 10 + num % 10;
            num = num / 10;
        }
        return result == x;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(12321));
        System.out.println(solution.isPalindrome1(121));
    }
}
/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 */