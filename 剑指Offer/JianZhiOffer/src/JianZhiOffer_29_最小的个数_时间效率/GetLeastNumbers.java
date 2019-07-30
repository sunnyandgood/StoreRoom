package JianZhiOffer_29_最小的个数_时间效率;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/30 21:56
 * 29、最小的个数
 * 题目描述：
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class GetLeastNumbers {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k == 0 || k > input.length) {
            return new ArrayList<>();
        }
        // 先使用最大堆保存 k 个数，此后，每次和堆顶比较，若比堆顶小，移除堆顶，新数入堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < input.length; i++) {
            if (maxHeap.size() != k) {
                maxHeap.add(input[i]);
            } else if (input[i] <= maxHeap.peek()) {
                maxHeap.remove();
                maxHeap.add(input[i]);
            }
        }
        return new ArrayList<>(maxHeap);
    }
//    public ArrayList<Integer> GetLeastNumbers_Solution_1(int [] input, int k) {
//        ArrayList<Integer> list = new ArrayList<>();
//        if (input == null || input.length == 0){
//            return list;
//        }
//        int start = 0;
//        int end = input.length - 1;
//        int index = this.partition(input,start,end);
//        while (index != k - 1){
//            if (index > k - 1){
//                end = index - 1;
//                index = this.partition(input,start,end);
//            }else {
//                start = index + 1;
//                index = this.partition(input,start,end);
//            }
//        }
//        for (int i = 0;i < k;i++){
//            list.add(input[i]);
//        }
//        return list;
//    }
//
//    private int partition(int[] data,int start,int end){
//        if (data == null || data.length == 0 || start < 0 || start >= end){
//            return 0;
//        }
//        int index = new Random().nextInt(end - start) + start;
//        this.swap(data,index,end);
//        int small = start - 1;
//        for (index = start;index < end;index++){
//            if (data[index] < data[end]){
//                this.swap(data,index,end);
//            }
//        }
//        ++small;
//        this.swap(data,small,end);
//        return small;
//    }
//    private void swap(int[] data,int i,int j){
//        int temp = data[i];
//        data[i] = data[j];
//        data[j] = temp;
//    }

    public static void main(String[] args) {
        GetLeastNumbers numbers = new GetLeastNumbers();
        System.out.println(numbers.GetLeastNumbers_Solution(new int[]{4,5,1,6,2,7,3,8},4));
        System.out.println(numbers.GetLeastNumbers_Solution(new int[]{4,5,1,6,2,7,3,8},8));
    }
}
