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
