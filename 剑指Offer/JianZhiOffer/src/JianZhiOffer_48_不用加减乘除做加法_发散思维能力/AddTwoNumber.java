package JianZhiOffer_48_不用加减乘除做加法_发散思维能力;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/29 0:25
 * 48、不用加减乘除做加法
 * 题目描述
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class AddTwoNumber {
    public int Add(int num1,int num2) {
        int sum = 0;
        do {
            sum = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = sum;
        }while (num2 != 0);
        return num1;
    }
}
