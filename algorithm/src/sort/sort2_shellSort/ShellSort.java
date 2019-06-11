package sort.sort2_shellSort;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/7 12:41
 * 2.希尔排序
 * 平均时间复杂度O(n^1.3)
 * 空间复杂度O(1)
 * 稳定性：不稳定
 * 复杂度：较复杂
 */
public class ShellSort {
    public int[] shellSort(int[] arr){
        int j;
        for (int gap=arr.length/2;gap>0;gap/=2){
            for (int i=gap;i<arr.length;i++){
                int temp = arr[i];
                for (j=i;j>=gap && temp<arr[j-gap];j-=gap){
                    arr[j] = arr[j-gap];
                }
                arr[j] = temp;

                System.out.println(Arrays.toString(arr));
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        ShellSort sort = new ShellSort();
        System.out.println(Arrays.toString(sort.shellSort(new int[]{32,43,23,13,5})));
    }
}
/**
 * 对于直接插入排序问题，数据量巨大时。
 * 1、将数的个数设为n，取奇数k=n/2，将下标差值为k的数分为一组，构成有序序列。
 * 2、再取k=k/2 ，将下标差值为k的数分为一组，构成有序序列。
 * 3、重复第二步，直到k=1执行简单插入排序。
 *
 * 如何写成代码：
 *   1）首先确定分的组数。
 *   2）然后对组中元素进行插入排序。
 *   3）然后将length/2，重复1,2步，直到length=0为止。
 */