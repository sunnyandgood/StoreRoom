package sort.sort6_quickSort;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/8 0:37
 * 平均时间复杂度O(nlog₂n)；最坏时间复杂度O(n^2)；最好时间复杂度O(nlog₂n) //n倍以2为底n的对数
 * 空间复杂度O(nlog₂n)
 * 稳定性：不稳定
 * 复杂度：较复杂
 */
public class QuickSort {
    public int[] quickSort(int[] arr,int start,int end){
        if (start>end){
            return arr;
        }
        int key = arr[start];
        int left = start;
        int right = end;
        while (left<right){
            while (left<right && arr[right]>=key){
                right--;
            }
            while (left<right && arr[left]<=key){
                left++;
            }
            if (left<right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        if (left == right){
            arr[start] = arr[right];
            arr[right] = key;
        }
        quickSort(arr,start,right-1);
        quickSort(arr,right+1,end);

        return arr;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] arr = new int[]{32,43,23,13,5};
        System.out.println(Arrays.toString(sort.quickSort(arr,0,arr.length-1)));
    }
}
/**
 * 要求时间最快时。
 * 选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
 * 递归的将p左边和右边的数都按照第一步进行，直到不能递归。
 */