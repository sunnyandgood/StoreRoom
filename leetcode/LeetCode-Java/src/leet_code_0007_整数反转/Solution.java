package leet_code_0007_整数反转;

import java.math.BigDecimal;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/30 22:03
 * 7. 整数反转
 */
public class Solution {
    public int reverse(int x) {
        int num = x;
        BigDecimal reverse = new BigDecimal(0);
        BigDecimal ten = new BigDecimal(10);
        if (x < 0){
            num = -num;
        }
        while (num>0){
            reverse = reverse.multiply(ten).add(new BigDecimal(num % 10));
            num = num / 10;
        }
        if (reverse.compareTo(new BigDecimal(Math.pow(2,31)-1))>0) {//−2^31,2^31 − 1
            reverse = new BigDecimal(0);
        }
        if (x<0){
            if (reverse.negate().compareTo(new BigDecimal(Math.pow(-2,31)))<0){
                return 0;
            }else {
                return -reverse.intValue();
            }
        }

        return reverse.intValue();
    }

    public int reverse1(int x) {
        int num = x;
        long reverse = 0;
        if (x < 0){
            num = -num;
        }
        while (num>0){
            reverse = reverse * 10 + num % 10;
            num = num / 10;
            if (reverse>Integer.MAX_VALUE){
                reverse = 0;
                break;
            }
        }
        if (x<0){
            reverse = -reverse;
        }
        return (int) reverse;
    }

    public int reverse2(int x) {
        int num = x>0 ? x : -x;
        int reverse = 0;
        char[] chars = String.valueOf(num).toCharArray();
        for (int i=chars.length-1;i>=0;i--){
            int temp = reverse * 10;
            if (temp / 10 == reverse){
                reverse = temp + chars[i] - '0';
            }else {
                return 0;
            }
        }
        return x > 0 ? reverse : -reverse;
    }

    public int reverse3(int x) {
        int num = x > 0 ? x : -x;
        int reverse = 0;
        while (num > 0){
            int temp = reverse * 10;
            if (temp / 10 == reverse){
                reverse = temp + num % 10;
                num = num / 10;
            }else {
                return 0;
            }
        }
        return x > 0 ? reverse : -reverse;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverse2(1200));
        System.out.println(solution.reverse1(123));
        System.out.println(solution.reverse1(-123));
        System.out.println(solution.reverse1(16009));
        System.out.println(solution.reverse1(1534236469));
    }
}
/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */