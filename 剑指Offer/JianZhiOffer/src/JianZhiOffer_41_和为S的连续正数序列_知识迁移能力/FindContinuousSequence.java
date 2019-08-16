package JianZhiOffer_41_和为S的连续正数序列_知识迁移能力;

import java.util.ArrayList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/15 22:35
 * 41、和为S的连续正数序列
 * 题目描述：
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16 的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,
 * 你能不能也很快的找出所有和为S 的连续正数序列? Good Luck!
 * 输出描述:
 * 输出所有和为S 的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class FindContinuousSequence {
    /**
     * 考虑用两个数small和big分别表示序列的最小值和最大值。首先把small初始化为1，bug初始化为2。
     * 如果从small到big的序列的和大于s，我们可以从序列中去掉较小的值，也就是增大small的值。
     * 如果从small到big的序列的和小于s，我们可以增大big，让这个序列包含更多的数字。
     * 因为这个序列至少要有两个数字，我们一直增加small到（1＋s）/2为止。
     *
     * 以求和为9的所有连续序列为例，我们先把small初始化为1，big初
     * 始化为2。此时介于small和big之间的序列是{1,2}，序列的和为3，小于
     * 9，所以我们下一步要让序列包含更多的数字。我们把big增加1变成3，
     * 此时序列为{1,2,3}。由于序列的和是6，仍然小于9，我们接下来再增加
     * big变成4，介于small和big之间的序列也随之变成{1,2,3,4}。由于序列的
     * 和10大于9，我们要删去去序列中的一些数字，于是我们增加small变成
     * 2，此时得到的序列是{2,3,4}，序列的和正好是9。我们找到了第一个和
     * 为9的连续序列，把它打印出来。接下来我们再增加big，重复前面的过
     * 程，可以找到第二个和为9的连续序列{4,5}。
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        if (sum < 3){
            return null;
        }
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int small = 1;
        int big = 2;
        int mid = (1 + sum) / 2;
        int curSum = small + big;
        while (small < big){
            if (curSum == sum){
                dealArrayList(list,small,big);
            }
            while (curSum > sum && small < mid){
                curSum -= small;
                small++;
                if (curSum == sum){
                    dealArrayList(list,small,big);
                }
            }
            big++;
            curSum += big;
        }
        return list;
    }
    private void dealArrayList(ArrayList<ArrayList<Integer>> list,int small,int big){
        ArrayList<Integer> innerList = new ArrayList<>();
        for (int i = small;i <= big;i++){
            innerList.add(i);
        }
        list.add(innerList);
    }


    public ArrayList<ArrayList<Integer> > FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer> >  list = new ArrayList<ArrayList<Integer> > ();
        if(sum < 3)return list;
        int n = (int) (Math.ceil((Math.sqrt(8*sum+1)-1)/2));
        int tmp = 0;
        int num = 0;
        for (int i = n;i > 1;i--){
            tmp = (i + 1) * i / 2;
            if ((sum - tmp) % i == 0) {
                ArrayList<Integer> arrayList = new ArrayList<Integer>();
                num = (sum - tmp) / i;
                for (int a=1;a<=i;a++){
                    arrayList.add(num + a);
                }
                list.add(arrayList);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        FindContinuousSequence deal = new FindContinuousSequence();
        System.out.println(deal.FindContinuousSequence(15));
    }
}
