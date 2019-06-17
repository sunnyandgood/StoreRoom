package JianZhiOffer_08_跳台阶;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/17 12:55
 * 8、跳台阶
 * 题目描述：
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 解题思路：
 * 本质上类似于斐波那契数列
 * 斐波那契数列：0 1 1 2 3 5  8 13 21 34 55 89
 * 青蛙跳台阶数：0 1 2 3 5 8 13 21 34 55 89
 */
public class JumpFloor {
    public int JumpFloor(int target) {
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
            res = pre + cur;
            pre = cur;
            cur = res;
        }
        return res;
    }

    public static void main(String[] args) {
        JumpFloor floor = new JumpFloor();
        for (int i=0;i<=10;i++){
            System.out.println(floor.JumpFloor(i));
        }
    }
}
