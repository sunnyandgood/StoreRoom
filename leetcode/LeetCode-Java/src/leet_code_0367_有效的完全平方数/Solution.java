package leet_code_0367_有效的完全平方数;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/2 23:59
 * 367. 有效的完全平方数
 */
public class Solution {
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num/2;
        double result = 1;
        while (left<=right){
            int mid = (left + right) / 2;
            int value = num / mid;
            if (mid < value){
//                result = mid;
                left = mid + 1;
            }else if (mid > value){
                right = mid -1;
            }else {
                result = mid;
                break;
            }
        }
        if (num / result == result){
            return true;
        }else {
            return false;
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPerfectSquare(16));
        System.out.println(solution.isPerfectSquare(11));
        System.out.println(solution.isPerfectSquare(1));
    }
}
/**
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 *
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 * 示例 1：
 *
 * 输入：16
 * 输出：True
 *
 * 示例 2：
 *
 * 输入：14
 * 输出：False
 */