package sort.sort4_heapSort;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/8 0:10
 * 4.堆排序
 * 平均时间复杂度O(nlog₂n)；最坏时间复杂度O(nlog₂n)；最好时间复杂度O(nlog₂n) //n倍以2为底n的对数
 * 空间复杂度O(1)
 * 稳定性：不稳定
 * 复杂度：较复杂
 */
public class HeapSort {
    public int[] heapSort(int[] arr){
        int arrayLength = arr.length;
        //循环建堆
        for(int i=0;i<arrayLength-1;i++){
            //建堆

            buildMaxHeap(arr,arrayLength-1-i);
            //交换堆顶和最后一个元素
            swap(arr,0,arrayLength-1-i);
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }
    private  void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
    //对data数组从0到lastIndex建大顶堆
    private void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while(k*2+1<=lastIndex){
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex<lastIndex){
                    //若果右子节点的值较大
                    if(data[biggerIndex]<data[biggerIndex+1]){
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if(data[k]<data[biggerIndex]){
                    //交换他们
                    swap(data,k,biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                }else{
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        System.out.println(Arrays.toString(sort.heapSort(new int[]{32,43,23,13,5})));
    }
}
/**
 *  对简单选择排序的优化。
 *  1、将序列构建成大顶堆。
 *  2、将根节点与最后一个节点交换，然后断开最后一个节点。
 *  3、重复第一、二步，直到所有节点断开。
 */