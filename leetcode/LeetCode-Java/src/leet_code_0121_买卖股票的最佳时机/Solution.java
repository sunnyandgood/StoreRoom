package leet_code_0121_买卖股票的最佳时机;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/1 12:39
 * 121. 买卖股票的最佳时机
 */
public class Solution {
    /**
     * 暴力破解：时间复杂度为N^2
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i=0;i<prices.length;i++){
            for (int j=i+1;j<prices.length;j++){
                if (prices[j]>prices[i] && maxProfit<(prices[j]-prices[i])){
                    maxProfit = prices[j]-prices[i];
                }
            }
        }
        return maxProfit;
    }

    /**
     * 时间复杂度为N
     */
    public int maxProfit1(int[] prices) {
        int maxProfit = 0;
        if (prices==null || prices.length<2){
            return maxProfit;
        }
        int minPrices = prices[0];
        for (int i=0;i<prices.length;i++){
            int temp = prices[i] - minPrices;
            maxProfit = maxProfit > temp ? maxProfit : temp;
            minPrices = minPrices < prices[i] ? minPrices : prices[i];
        }
        return maxProfit;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices1 = {7,1,5,3,6,4};
        int[] prices2 = {7,6,4,3,1};
        System.out.println(solution.maxProfit1(prices1));
        System.out.println(solution.maxProfit1(prices2));
    }
}
/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */