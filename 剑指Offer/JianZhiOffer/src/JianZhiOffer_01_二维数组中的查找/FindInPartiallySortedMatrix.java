package JianZhiOffer_01_二维数组中的查找;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/14 22:27
 *
 * 1、二维数组中的查找
 * 题目描述：
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 解题思路：
 * 首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束；
 * 如果该数字大于要查找的数字，剔除这个数字所在的列；
 * 如果该数字小于要查找的数字，剔除这个数字所在的行。
 * 也就是说如果要查找的数字不在数组的右上角，则每一次都在数组的查找范围中剔除一行或者一列，
 * 这样每一步都可以缩小查找的范围，直到找到要查找的数字，或者查找范围为空。
 */
public class FindInPartiallySortedMatrix {

    /**
     * 从右上角开始找
     * 如果该数字大于要查找的数字，剔除这个数字所在的列（列值减一）；
     * 如果该数字小于要查找的数字，剔除这个数字所在的行（行值加一）。
     */
    public boolean Find(int target, int [][] array) {
        boolean res = false;
        if (array == null || array.length == 0){
            return false;
        }

        int col = array[0].length - 1;
        int row = 0;
        while (col>=0 && row<array.length){
            if (array[row][col] == target){
                res = true;
                break;
            }else if (array[row][col] > target){
                col--;
            }else {
                row++;
            }
        }
        return res;
    }
    /**
     * 从左下角开始找
     * 如果该数字大于要查找的数字，剔除这个数字所在的列（行值减一）；
     * 如果该数字小于要查找的数字，剔除这个数字所在的行（列值加一）。
     */
    public boolean Find1(int target, int [][] array) {
        boolean res = false;
        if (array == null || array.length == 0){
            return false;
        }
        int col = 0;
        int row = array.length - 1;
        while (col<array[0].length && row>=0){
            if (array[row][col] == target){
                res = true;
                break;
            }else if (array[row][col] < target){
                col++;
            }else {
                row--;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] array = {{1, 2, 8, 9},
                         {2, 4, 9, 12},
                         {4, 7, 10, 13},
                         {6, 8, 11, 15}};
        System.out.println(new FindInPartiallySortedMatrix().Find(7, array));
    }
}