package JianZhiOffer_67_剪绳子_动态规划与贪婪;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/8 1:14
 * 67、剪绳子
 * 题目描述
 * 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。
 * 请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 输入描述:
 *  输入一个数n，意义见题面。（2 <= n <= 60）
 *
 * 输出描述:
 *  输出答案。
 *
 * 示例1
 * 输入
 * 8
 *
 * 输出
 * 18
 */
public class CutRope {
    //动态规划
    public int cutRope(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = max3(max, j * (i - j), j * dp[i - j]);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    private int max3(int num1, int num2, int num3) {
        return Integer.max(Integer.max(num1, num2), num3);
    }


    //贪心算法
    public int integerBreak(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }
        // 接下来就是 n >= 5 的时候的逻辑了
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        res *= n;
        return res;
    }

    public static void main(String[] args) {
        CutRope solution = new CutRope();
        int integerBreak = solution.cutRope(8);
        System.out.println(integerBreak);
    }
}
