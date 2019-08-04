package JianZhiOffer_32_把数组排成最小的数_时间效率;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/2 21:58
 * 32、把数组排成最小的数
 * 题目描述：
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class PrintMinNumber {
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0){
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i < numbers.length;i++){
            list.add(numbers[i]);//将数组放入arrayList中
        }
        //实现了Comparator接口的compare方法，将集合元素按照compare方法的规则进行排序
        Collections.sort(list, (Integer str1, Integer str2) -> {
            String s1 = str1 + "" + str2;
            String s2 = str2 + "" + str1;

            return s1.compareTo(s2);
        });

        for(int val : list){
            buffer.append(val);
        }
        return buffer.toString();
    }
}
