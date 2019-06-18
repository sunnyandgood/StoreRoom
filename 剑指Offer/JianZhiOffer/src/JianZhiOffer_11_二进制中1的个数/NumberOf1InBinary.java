package JianZhiOffer_11_二进制中1的个数;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/18 12:57
 * 11、二进制中1的个数
 * 题目描述:
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class NumberOf1InBinary {
    /**
     * 常规解法：
     * 为了避免死循环，我们可以不右移输入的数字n。首先把n和1做与运算，判断n的最低位是不是为1。
     * 接着把1左移一位得到2，再和n做与运算，就能判断n的次低位是不是1……这样反复左移，每次都能判断i的其中一位是不是1。
     * 这个解法中循环的次数等于整数二进制的位数，32位的整数需要循环32次。
     */
    public int NumberOf1(int n) {
        int res = 0;
        int flag = 1;
        while (flag != 0){
            if ((n & flag) != 0){
                res++;
            }
            flag = flag << 1;
        }
        return res;
    }

    /**
     * 经典解法：
     * 如果一个整数不等于0，那么该整数的二进制表示中至少有一位是1。
     * 把一个整数减去1，再和原整数做与运算，会把该整数最右边一个1变成0。
     * 那么一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作。
     */
    public int NumberOf1_1(int n) {
        int res = 0;
        while (n != 0){
            res++;
            n = n & (n - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        NumberOf1InBinary binary = new NumberOf1InBinary();
        System.out.println(binary.NumberOf1(2));
        System.out.println(binary.NumberOf1_1(-9));
    }
}
