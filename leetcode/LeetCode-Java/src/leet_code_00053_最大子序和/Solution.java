package leet_code_00053_最大子序和;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/19 23:04
 * 53. 最大子序和
 * 53.Maximum Subarray
 */
public class Solution {
    //暴力破解O(N^3)  LeetCode测试超出时间限制
    public int maxSubArray1(int[] nums) {
        int max = nums[0];
        for (int i=0;i<nums.length;i++){
            for (int j=i;j<nums.length;j++){
                int sum = nums[i];
                for (int k=i;k<j;k++){
                    sum += nums[k+1];
                }
                max = (sum>max) ? sum : max;
            }
        }
        return max;
    }

    //暴力破解改进O(N^2)
    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        for (int i=0;i<nums.length;i++){
            int sum = 0;
            for (int j=i;j<nums.length;j++){
                sum += nums[j];

                max = (sum>max) ? sum : max;
            }
        }
        return max;
    }

    //最优解 O(N)
    public int maxSubArray3(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            if (sum > 0){
                sum += nums[i];
            } else{
                sum = nums[i];
            }
            max = (max<sum) ? sum : max;
        }
        return max;
        // int max = nums[0];
        // int sum = 0;
        // for (int i=0;i<nums.length;i++){
        //     sum += nums[i];
        //     if (sum > max)
        //         max = sum;
        //     else
        //         sum = 0;
        // }
        // return max;
    }

    public static void main(String[] args) {
        int nums[] = {-2,1,-3,4,-1,2,1,-5,4};
        int nums1[] = {-1};
        Solution solution = new Solution();
        System.out.println(solution.maxSubArray3(nums));
    }
}
/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */