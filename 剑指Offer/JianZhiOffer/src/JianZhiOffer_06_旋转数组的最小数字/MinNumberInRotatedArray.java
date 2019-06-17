package JianZhiOffer_06_旋转数组的最小数字;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/16 23:55
 * 6、旋转数组的最小数字
 * 题目描述：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * 解题思路：
 * 和二分查找法一样，我们用两个指针分别指向数组的第一个元素和最后一个元素。
 * 按照题目中旋转的规则，第一个元素应该是大于或者等于最后一个元素的（这其实不完全对，还有特例，后面再加以讨论）。
 * 接着我们可以找到数组中间的元素。如果该中间元素位于前面的递增子数组，那么它应该大于或者等于第一个指针指向的元素。
 * 此时数组中最小的元素应该位于该中间元素的后面。我们可以把第一个指针指向该中间元素，这样可以缩小寻找的范围。
 * 移动之后的第一个指针仍然位于前面的递增子数组之中。
 * 同样，如果中间元素位于后面的递增子数组，那么它应该小于或者等于第二个指针指向的元素。
 * 此时该数组中最小的元素应该位于该中间元素的前面。我们可以把第二个指针指向该中间元素，这样也可以缩小寻找的范围。
 * 移动之后的第二个指针仍然位于后面的递增子数组之中。
 * 不管是移动第一个指针还是第二个指针，查找范围都会缩小到原来的一半。接下来我们再用更新之后的两个指针，重复做新一轮的查找。
 * 按照上述的思路，第一个指针总是指向前面递增数组的元素，而第二个指针总是指向后面递增数组的元素。
 * 最终第一个指针将指向前面子数组的最后一个元素，而第二个指针会指向后面子数组的第一个元素。
 * 也就是它们最终会指向两个相邻的元素，而第二个指针指向的刚好是最小的元素。这就是循环结束的条件。
 * 此时两个指针的距离是1，表明第一个指针已经指向了第一个递增子数组的末尾，而第二个指针指向第二个递增子数组的开头。
 * 第二个子数组的第一个数字就是最小的数字，因此第二个指针指向的数字就是我们查找的结果。
 *
 * 前面我们提到在旋转数组中，由于是把递增排序数组前面的若干个数字搬到数组的后面，因此第一个数字总是大于或者等于最后一个数字。
 * 但按照定义还有一个特例：如果把排序数组的前面的0个元素搬到最后面，即排序数组本身，这仍然是数组的一个旋转，我们的代码需要
 * 支持这种情况。此时，数组中的第一个数字就是最小的数字，可以直接返回。
 * 一旦发现数组中第一个数字小于最后一个数字，表明该数组是排序的，就可以直接返回第一个数字了。
 *
 * 当两个指针指向的数字及它们中间的数字三者相同的时候，我们无法判断中间的数字是位于前面的子数组中还是后面的子数组中，
 * 也就无法移动两个指针来缩小查找的范围。此时，我们不得不采用顺序查找的方法。
 */
public class MinNumberInRotatedArray {
    public int minNumberInRotateArray(int [] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        int res = array[0];
        int left = 0;
        int right = array.length - 1;
        while (array[left] >= array[right]){
            if ((right - left) == 1){
                res = array[right];
                break;
            }
            int mid = left + (right - left) / 2;
            if (array[mid] > array[left]){
                left = mid;
            }else if (array[mid] < array[right]){
                right = mid;
            }else {//如果left、right和mid指向的三个数字相等，则只能顺序查找
                for (int i=0;i<array.length;i++){
                    if (res > array[i]){
                        res = array[i];
                    }
                }
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinNumberInRotatedArray array = new MinNumberInRotatedArray();
        System.out.println(array.minNumberInRotateArray(new int[]{3,4,5,1,2}));
        System.out.println(array.minNumberInRotateArray(new int[]{1,2,3,4,5}));
        System.out.println(array.minNumberInRotateArray(new int[]{1,0,1,1,1}));
        System.out.println(array.minNumberInRotateArray(new int[]{1,1,1,0,1}));
    }
}
