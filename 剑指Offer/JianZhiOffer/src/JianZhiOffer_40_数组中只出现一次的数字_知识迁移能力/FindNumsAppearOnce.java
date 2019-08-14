package JianZhiOffer_40_数组中只出现一次的数字_知识迁移能力;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/14 22:34
 * 40、数组中只出现一次的数字
 * 题目描述：
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class FindNumsAppearOnce {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        Integer[] res = new Integer[2];
        set.toArray(res);
        num1[0] = res[0];
        num2[0] = res[1];

    }
}
