package sort.sort5_bubbleSort;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/8 0:24
 * 平均时间复杂度O(n^2)；最坏时间复杂度O(n^2)；最好时间复杂度O(n)
 * 空间复杂度O(1)
 * 稳定性：稳定
 * 复杂度：简单
 */
public class BubbleSort {
    public int[] bubbleSort(int[] arr){
        int length = arr.length;
        for (int i=0;i<length;i++){
            for (int j=0;j<length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        System.out.println(Arrays.toString(sort.bubbleSort(new int[]{32,43,23,13,5})));
    }
}
/**
 *  一般不用。
 *  1、将序列中所有元素两两比较，将最大的放在最后面。
 *  2、将剩余序列中所有元素两两比较，将最大的放在最后面。
 *  3、重复第二步，直到只剩下一个数。
 *
 *  如何写成代码：
 *   1）设置循环次数。
 *   2）设置开始比较的位数，和结束的位数。
 *   3）两两比较，将最小的放到前面去。
 *   4）重复2、3步，直到循环次数完毕。
 */