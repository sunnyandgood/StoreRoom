package sort.sort1_directInsertionSort;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/6 23:24
 * 1.直接插入排序
 * 平均时间复杂度O(n^2)；最坏时间复杂度O(n^2)；最好时间复杂度O(n)
 * 空间复杂度O(1)
 * 稳定性：稳定
 * 复杂度：简单
 */
public class DirectInsertionSort {
    public int[] insertSort(int[] arr){
        int length = arr.length;//数组长度，将这个提取出来是为了提高速度。
        int insertNum;//要插入的数
        for (int i=1;i<length;i++){//插入的次数
            insertNum = arr[i];//要插入的数
            int j = i-1;//已经排序好的序列元素个数
            //序列从后到前循环，将大于insertNum的数向后移动一格
            while (j>=0 && arr[j]>insertNum){
                arr[j+1] = arr[j];//元素移动一格
                j--;
            }
            arr[j+1] = insertNum;//将需要插入的数放在要插入的位置。
//            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        DirectInsertionSort sort = new DirectInsertionSort();
        System.out.println(Arrays.toString(sort.insertSort(new int[]{32,43,23,13,5})));
        /**
         * [32, 43, 23, 13, 5]
         * [23, 32, 43, 13, 5]
         * [13, 23, 32, 43, 5]
         * [5, 13, 23, 32, 43]
         */
    }
}
/**
 * 1、将第一个数和第二个数排序，然后构成一个有序序列
 * 2、将第三个数插入进去，构成一个新的有序序列。
 * 3、对第四个数、第五个数……直到最后一个数，重复第二步。
 *
 * 如何写写成代码：
 *
 * 1）首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，1个数的那次不用插入。
 * 2）设定插入数 和 得到已经排好序列的最后一个数的位数。insertNum 和 j=i-1。
 * 3）从最后一个数开始向前循环，如果插入数小于当前数，就将当前数向后移动一位。
 * 4）将当前数放置到空着的位置，即j+1。
 */