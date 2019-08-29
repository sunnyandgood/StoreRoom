package JianZhiOffer_49_把字符串转换成整数_综合;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/29 0:32
 * 49、把字符串转换成整数
 * 题目描述：
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0。
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 *
 * 示例1
 * 输入
 * +2147483647
 *     1a33
 *
 * 输出
 * 2147483647
 *     0
 */
public class StringToInt {
    public int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 判断正负性(1为正，-1为负)
        int flag = 1;
        char[] chArr = str.toCharArray();
        int i = 0;
        if (chArr[i] == '-') {
            flag = -1;
            i++;
        } else if (chArr[i] == '+') {
            i++;
        }
        // 转换
        long sum = 0;
        while (i < chArr.length) {
            if (chArr[i] < '0' || chArr[i] > '9') {
                return 0;
            }
            sum = sum * 10 + flag * (chArr[i++] - '0');
            // 判断是否小于负整数最小值或大于正整数最大值
            if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return (int) sum;
    }
}
