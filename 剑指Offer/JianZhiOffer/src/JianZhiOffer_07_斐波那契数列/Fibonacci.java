package JianZhiOffer_07_斐波那契数列;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/17 0:09
 * 7、斐波那契数列
 * 题目描述
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 * 0 1 2 3 4 5 6 7  8  9  10
 * 0 1 1 2 3 5 8 13 21 34 55
 */
public class Fibonacci {
    //从前往后循环实现
    public int fibonacci(int n) {
        int res;
        if (n < 2){
            return n;
        }
        int pre = 0;
        int cur = 1;
        for (int i=2;i<=n;i++){
            res = pre + cur;
            pre = cur;
            cur = res;
        }
        return cur;
    }
    //递归实现
    public int fibonacci1(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci1(n-1) + fibonacci1(n-2);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibonacci1(10));
    }
}
