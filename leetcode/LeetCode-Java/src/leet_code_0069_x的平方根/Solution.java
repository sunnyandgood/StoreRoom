package leet_code_0069_x的平方根;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/2 23:36
 * 69. x 的平方根
 */
public class Solution {
    /**
     * 二分查找
     */
    public int mySqrt(int x) {
        int result = x;
        int left = 1;
        int right = x / 2;
        while (left<=right){
            int mid = (right + left) / 2;
            // 防止乘法溢出
            int value = x / mid;
            if (value>mid){
                result = mid;
                left = mid + 1;
            }else if (value<mid){
                right = mid -1;
            }else {
                return mid;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(8));
        System.out.println(solution.mySqrt(16));
        System.out.println(solution.mySqrt(4));
    }
}
/**
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */