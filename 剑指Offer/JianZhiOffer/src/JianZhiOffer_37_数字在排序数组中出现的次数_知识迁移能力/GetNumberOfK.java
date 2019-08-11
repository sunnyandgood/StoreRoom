package JianZhiOffer_37_数字在排序数组中出现的次数_知识迁移能力;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/9 23:45
 * 37、数字在排序数组中出现的次数
 * 题目描述：
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfK {
    public int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0 || k < array[0] || k > array[array.length - 1]){
            return 0;
        }
        int start = 0;
        int end = array.length - 1;
        int lastK = getLastK(array, k, start, end);
        int firstK = getFirstK(array, k, start, end);
        if (lastK > -1 && firstK > -1){
            return lastK - firstK + 1;
        }
        return 0;
    }

    private int getFirstK(int [] array , int k , int start , int end){
        if (start > end){
            return -1;
        }
        int mid = (end - start) / 2 + start;
        int midData = array[mid];
        if (midData == k){
            if ((mid > 0 && array[mid - 1] != k) || mid == 0) {
                return mid;
            }else {
                end = mid - 1;
            }
        }else if (array[mid] > k){
            end = mid - 1;
        }else {
            start = mid + 1;
        }
        return getFirstK(array,k,start,end);
    }

    private int getLastK(int [] array , int k , int start , int end){
        if (start > end){
            return -1;
        }
        int mid = (end - start) / 2 + start;
        int midData = array[mid];
        if (midData == k){
            if ((mid < (array.length - 1) && array[mid + 1] != k) || mid == (array.length - 1)){
                return mid;
            }else {
                start = mid + 1;
            }
        }else if (array[mid] < k){
            start = mid + 1;
        }else {
            end = mid - 1;
        }
        return getLastK(array,k,start,end);
    }

    public static void main(String[] args) {
        GetNumberOfK getNumberOfK = new GetNumberOfK();
//        System.out.println(getNumberOfK.GetNumberOfK(new int[]{1,2,3,3,3,3,4,5},3));
        System.out.println(getNumberOfK.GetNumberOfK(new int[]{3,3,3,3,4,5},3));
    }
}
