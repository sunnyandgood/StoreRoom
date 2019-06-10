package sort.sort3_simpleSelectionSort;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/7 23:57
 * 3.简单选择排序
 * 平均时间复杂度O(n^2)；最坏时间复杂度O(n^2)；最好时间复杂度O(n^2)
 * 空间复杂度O(1)
 * 稳定性：不稳定
 * 复杂度：简单
 */
public class SimpleSelectionSort {
    public int[] selectionSort(int[] arr){
        int length = arr.length;
        for (int i=0;i<length;i++){//循环次数
            int key = arr[i];
            int index = i;
            for (int j=i+1;j<length;j++){//选出最小的值和位置
                if (arr[j]<key){
                    key = arr[j];
                    index = j;
                }
            }
            arr[index] = arr[i];//交换位置
            arr[i] = key;
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        SimpleSelectionSort sort = new SimpleSelectionSort();
        System.out.println(Arrays.toString(sort.selectionSort(new int[]{32,43,23,13,5})));
    }
}
/**
 * (如果每次比较都交换，那么就是交换排序；如果每次比较完一个循环再交换，就是简单选择排序。)
 *  1、遍历整个序列，将最小的数放在最前面。
 *  2、遍历剩下的序列，将最小的数放在最前面。
 *  3、重复第二步，直到只剩下一个数。
 *
 *  如何写成代码：
 *     1）首先确定循环次数，并且记住当前数字和当前位置。
 *     2）将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
 *     3）比对完成后，将最小的值与第一个数的值交换。
 *     4）重复2、3步。
 */