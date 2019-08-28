package JianZhiOffer_47_求1加到n_发散思维能力;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/27 23:44
 * 47、求1+2+3+...+n
 * 题目描述
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Sum_Solution {
    /**
     * 利用短路运算的性质判断递归结束条件
     */
    public int Sum_Solution_1(int n) {
        int sum = n;
        boolean flag = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    /**
     * 利用库函数
     */
    public int Sum_Solution_2(int n) {
        return (int) (Math.pow(n, 2) + n) >> 1;
    }

    /**
     * 利用流
     */
    public int Sum_Solution(int n) {
        AtomicInteger sum = new AtomicInteger();
        IntStream.range(1, n + 1)
                .reduce((a, b) -> a + b)
                .ifPresent(sum::set);
        return sum.get();
    }
}

//class Solution {
//    private static int num = 0;
//    private static int sum = 0;
//    public Solution(){
//        ++num;
//        sum += num;
//    }
//
//    private static void reset(){
//        num = 0;
//        sum = 0;
//    }
//
//    private static int getSum(){
//        return sum;
//    }
//
//    public int Sum_Solution(int n) {
//        Solution.reset();
//        Solution[] solution = new Solution[n];
//        return Solution.getSum();
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.Sum_Solution(2));
//    }
//}
