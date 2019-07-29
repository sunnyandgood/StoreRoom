package JianZhiOffer_28_数组中出现次数超过一半的数字_分解让复杂问题简单;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/29 23:03
 * 28、数组中出现次数超过一半的数字
 * 题目描述：
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class MoreThanHalfNumSolution {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int val : array){
            Integer time = map.getOrDefault(val, 0) + 1;
            if (time > array.length / 2){
                return val;
            }
            map.put(val,time);
        }
        return 0;
    }
}
