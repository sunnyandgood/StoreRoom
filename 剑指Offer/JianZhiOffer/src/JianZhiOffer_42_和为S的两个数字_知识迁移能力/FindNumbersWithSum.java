package JianZhiOffer_42_和为S的两个数字_知识迁移能力;

import java.util.ArrayList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/16 21:53
 * 42、和为S 的两个数字
 * 题目描述：
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class FindNumbersWithSum {

    /**
     * 以数组{1、2、4、7、11、15}及期待的和15为例详细分析一下这个过程。
     * 首先定义两个指针，第一个指针指向数组的第一个（也是最小的）数字1，第二个指针指向数组的最后一个（也是最大的）数字15。
     * 这两个数字的和16大于15，因此我们把第二个指针向前移动一个数字，让它指向11。这个时候两个数字1与11的和是12，小于15。
     * 接下来我们把第一个指针向后移动一个数字指向2。此时两个数字2与11的和13，还是小于15。
     * 我们再一次向后移动第一个指针，让它指向数字4。数字4、11的和是15，正是我们期待的结果。
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0){
            return list;
        }
        int min = 0;
        int max = array.length - 1;
        while (min < max){
            int curSum = array[min] +array[max];
            if (curSum == sum){
                list.add(array[min]);
                list.add(array[max]);
                break;
            }else if (curSum < sum){
                min++;
            }else {
                max--;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        FindNumbersWithSum find = new FindNumbersWithSum();
        System.out.println(find.FindNumbersWithSum(new int[]{1,2,4,7,11,15},15));
    }
}
