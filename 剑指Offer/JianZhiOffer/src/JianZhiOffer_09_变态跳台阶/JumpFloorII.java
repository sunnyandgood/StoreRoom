package JianZhiOffer_09_变态跳台阶;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/17 13:21
 * 9、变态跳台阶
 * 题目描述：
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 解题思路：
 * 还是类似于斐波那契数列
 * f(n) = 2^(n-1)
 * 0 1 2 4 8 16 32 64 128 256 512 1024
 */
public class JumpFloorII {
    public int JumpFloorII(int target) {
        if (target < 0){
            return -1;
        }
        if (target <= 2){
            return target;
        }
        int res = 0;
        int pre = 1;
        int cur = 2;
        for (int i=3;i<=target;i++){
            res = pre + pre + cur;
            pre = cur;
            cur = res;
        }
        return res;
    }

    public static void main(String[] args) {
        JumpFloorII floor = new JumpFloorII();
        for (int i=0;i<=10;i++){
            System.out.println(floor.JumpFloorII(i));
        }
    }
}
