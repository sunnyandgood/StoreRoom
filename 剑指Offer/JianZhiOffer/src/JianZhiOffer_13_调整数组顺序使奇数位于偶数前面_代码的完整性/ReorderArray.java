package JianZhiOffer_13_调整数组顺序使奇数位于偶数前面_代码的完整性;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/19 0:52
 * 13、调整数组顺序使奇数位于偶数前面
 * 题目描述：
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class ReorderArray {
    /**
     * 从头到尾遍历数组，若发现为奇数的值，则之前的值向后移动，将该整数放到数组中 该奇数之前 上一个奇数之后。
     * 若连续两个数都是奇数，则不移动
     */
    public void reOrderArray(int [] array) {
        int length = array.length;
        if (array == null || length == 0){
            return;
        }
        for (int i = 0;i < length;i++){
            if ((array[i] & 1) == 1){
                int temp = array[i];
                int j;
                for (j = i;((j - 1) >= 0) && ((array[j - 1] & 1) == 0);j--){
                    array[j] = array[j-1];
                }
                array[j] = temp;
            }
        }
    }

    /**
     * 从头到尾遍历数组，若发现为偶数的值，则之后的值向前移动，将该整数放到数组中 该偶数之后 下一个偶数之前。
     * 若连续两个数都是偶数，则不移动
     */
    public void reOrderArray1(int [] array) {
        int length = array.length;
        if (array == null || length == 0){
            return;
        }
        int flag = 0;
        while (flag < length){
            for (int i = 0; i < length; i++){
                //如果该数是偶数
                if ((array[i] & 1) == 0){
                    int temp = array[i];
                    int j;
                    for (j = i;j < (array.length - 1) && ((array[j + 1] & 1) != 0);j++){
                        array[j] = array[j+1];
                    }
                    array[j] = temp;
                }
            }
            flag++;
        }
    }
    public static void main(String[] args) {
//        ReorderArray reorderArray = new ReorderArray();
        int[] arr = new int[]{1,2,8,3,4,5,6,7,10,12,9};
//        reorderArray.reOrderArray1(arr);
//        System.out.println(Arrays.toString(arr));

        ReorderArrayUnordered unordered = new ReorderArrayUnordered();
        unordered.reOrderArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}

/**
 * 不并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
class ReorderArrayUnordered{
    public void reOrderArray(int [] array) {
        int length = array.length;
        if (array == null || length == 0){
            return;
        }
        int left = 0;
        int right = length - 1;
        while (left < right){
            while (left < right && !isEven(array[left])){
                left++;
            }
            while (left < right && isEven(array[right])){
                right--;
            }
            if (left < right){
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
    }

    public boolean isEven(int num){
        return ((num & 1) == 0);
    }
}