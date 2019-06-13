package array.找出一个有序数组在给定区间缺失的数;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/13 23:36
 */
public class FindMissingRange {
    public List<String> findMissingRange(int[] arr,int lower,int upper){
        List<String> res = new ArrayList<>();
        if (arr == null || arr.length == 0){
            res.add(lower + "->" + upper);
        }

        //首先处理lower后的范围
        addToList(res,lower,arr[0] - 1);
        //其次处理lower到upper之间的范围
        int pre = arr[0];
        int i = 1;
        while (i < arr.length){
            int cur = arr[i];
            if (cur != pre + 1){
                addToList(res,pre + 1,cur - 1);
            }
            pre = cur;
            i++;
        }
        //最后处理upper之前的范围
        addToList(res,arr[arr.length-1] + 1,upper);
        return res;
    }

    public void addToList(List<String> list,int start,int end){
        if (start == end){
            list.add(start + "");
        }else if (start < end){
            list.add(start + "->" + end);
        }
    }

    public static void main(String[] args) {
        FindMissingRange range = new FindMissingRange();
        int[] arr = {0,1,3,50,75};
        int lower = 0;
        int upper = 99;
        System.out.println(range.findMissingRange(arr,lower,upper));
    }
}
/**
 * 给定一个排好序的数组，找出在一个给定区间内缺失的数据
 *
 * eg：
 * given：[0,1,3,50,75],lower = 0 and upper = 99
 * return:["2","4->49","51->74","76->99"]
 */