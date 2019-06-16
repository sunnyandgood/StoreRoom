package JianZhiOffer_06_旋转数组的最小数字;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/16 23:55
 * 6、旋转数组的最小数字
 * 题目描述：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class MinNumberInRotatedArray {
    public int minNumberInRotateArray(int [] array) {
        return 0;
    }

    public static void main(String[] args) {
        MinNumberInRotatedArray array = new MinNumberInRotatedArray();
        System.out.println(array.minNumberInRotateArray(new int[]{3,4,5,1,2}));
    }
}
