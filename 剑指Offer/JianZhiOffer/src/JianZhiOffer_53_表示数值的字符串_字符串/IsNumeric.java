package JianZhiOffer_53_表示数值的字符串_字符串;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/2 23:23
 * 53、表示数值的字符串
 * 题目描述：
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class IsNumeric {
    public boolean isNumeric(char[] str) {
        String s=String.valueOf(str);
        return s.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
    }

//    private boolean scanUnsignNum(char[] str){
//        char pre = str[0];
//        int index = 0;
//        while (index != (str.length - 1) && str[index] >= '0' && str[index] <= '9'){
//            index++;
//        }
//        //当str中存在若干个0~9的数字时，返回true
//        return str[index] > pre;
//    }
//
//    private boolean scanNum(char[] str){
//        int index = 0;
//        if (str[index] == '+' || str[index] == '-'){
//            index++;
//        }
//        char[] newStr = new char[str.length - index];
//        for (int i = index;i <str.length;i++){
//            newStr[i - index] = str[index];
//        }
//        return this.scanUnsignNum(newStr);
//    }
}
