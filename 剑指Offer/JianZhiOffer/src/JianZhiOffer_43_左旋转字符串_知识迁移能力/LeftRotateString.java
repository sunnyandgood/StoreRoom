package JianZhiOffer_43_左旋转字符串_知识迁移能力;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/16 22:14
 * 43、左旋转字符串
 * 题目描述
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class LeftRotateString {
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0 || n < 0){
            return str;
        }
        StringBuffer buffer = new StringBuffer();
        String preStr = str.substring(0, n);
        String laStr = str.substring(n, str.length());
        buffer.append(laStr).append(preStr);
        return buffer.toString();
    }

    public String LeftRotateString_1(String str,int n) {
        if (str == null || str.length() == 0 || n < 0){
            return str;
        }
        char[] charArr = str.toCharArray();
        int length = str.length();
        reverse(charArr,0,n - 1);
        reverse(charArr,n,length - 1);
        reverse(charArr,0,length - 1);
        return new String(charArr);
    }

    private void reverse(char[] charArr,int start,int end){
        while (start < end){
            char temp = charArr[start];
            charArr[start++] = charArr[end];
            charArr[end--] = temp;
        }
    }

    public static void main(String[] args) {
        LeftRotateString buffer = new LeftRotateString();
//        System.out.println(buffer.LeftRotateString("abcXYZdef",3));
        System.out.println(buffer.LeftRotateString_1("abcXYZdef",3));
    }
}
