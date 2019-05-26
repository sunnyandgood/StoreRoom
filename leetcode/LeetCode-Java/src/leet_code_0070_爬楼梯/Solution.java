package leet_code_0070_爬楼梯;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/26 22:44
 * 70. 爬楼梯
 */
public class Solution {
    /**
     * 递归求解（LeetCode超出时间限制）
     */
    public int climbStairs(int n) {
        if (n==1){
            return 1;
        }else if (n==2){
            return 2;
        } else{
            return climbStairs(n-1) + climbStairs(n-2);
        }
    }

    /**
     * 要求解爬 n 阶台阶有多少中方法，就是求解爬 n - 1 阶台阶和爬 n - 2 阶台阶的方法的和
     * 由此可见，是斐波拉切数列的应用，可使用记忆化搜索和动态规划解决，这里使用动态规划来解决
     */
    public int climbStairs1(int n) {
        int[] fibonacci = new int[n+1];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i=2;i<=n;i++){
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        return fibonacci[n];
    }

    public int climbStairs2(int n) {
        int pre = 1;
        int cur = 1;
        for (int i=2;i<=n;i++){
            cur += pre;
            pre = cur - pre;
        }
        return cur;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs2(8));
        //1、1、2、3、5、8、13、21、34、55、89
    }

}
/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 10个台阶，全部都走两步的话，无非就是5个两步。
 * 全是一步，10个一步。1种。
 * 1个两步，8个一步。相当于从9个坑里放1个两步。9C1 = 9种。
 * 2个两步，6个一步。相当于从8个坑里放2个两步。8C2 = 28种。
 * 3个两步，4个一步。相当于从7个坑里放3个两步。7C3 = 35种。
 * 4个两步，2个一步。相当于从6个坑里放4个两步。6C4 = 15种。
 * 5个两步。1种。
 * 所以一共有：1 + 9 + 28 + 35 + 15 + 1 = 89种。
 */