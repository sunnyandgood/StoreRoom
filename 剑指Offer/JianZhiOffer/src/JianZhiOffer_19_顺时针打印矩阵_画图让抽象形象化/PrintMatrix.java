package JianZhiOffer_19_顺时针打印矩阵_画图让抽象形象化;

import java.util.ArrayList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/29 22:30
 * 19、顺时针打印矩阵
 * 题目描述
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1  2  3  4
 *                            5  6  7  8
 *                            9  10 11 12
 *                            13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * 解题思路：
 * 可以把打印一圈分为四步：第一步从左到右打印一行，第二步从上到下打印一列，第三步从右到左打印一行，第四步从下到上打印一列。
 * 每一步我们根据起始坐标和终止坐标用一个循环就能打印出一行或者一列。
 * 不过值得注意的是，最后一圈有可能退化成只有一行、只有一列，甚至只有一个数字，因此打印这样的一圈就不再需要四步。
 *
 */
public class PrintMatrix {
    /**public ArrayList<Integer> printMatrix_1(int [][] matrix) {
        if (matrix == null || matrix.length == 0){
            return null;
        }
        ArrayList resultList = new ArrayList();
        int start = 0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        while (columns > (start * 2) && rows > (start * 2)){
            printMatrixInCircle(matrix,start,resultList);
            start++;
        }
        return resultList;
    }

    void printMatrixInCircle(int[][] matrix,int start,ArrayList resultList){
        int endY = matrix.length - 1 - start;
        int endX = matrix[0].length - 1 - start;
        //从左到右打印一行
        for (int y = start;y < endX;y++){
            resultList.add(matrix[start][y]);
        }
        //从上到下打印一列
        if (start < endY){
            for (int x = start + 1;x <= endY;x++){
                resultList.add(matrix[x][endX]);
            }
        }
        //从右到左打印一行
        if (start <endX && start < endY){
            for (int y = endX - 1;y >= start;y--){
                resultList.add(matrix[endY][y]);
            }
        }
        //从下到上打印一列
        if (start < endX && start < endY - 1){
            for (int x = endY - 1;x > start + 1;x--){
                resultList.add(matrix[x][start]);
            }
        }
    }*/

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix == null || matrix.length == 0){
            return null;
        }
        ArrayList resultList = new ArrayList();
        //矩阵为偶数行时
        int layer = matrix.length / 2;
        //矩阵为奇数行时
        if (matrix.length % 2 != 0){
            layer = layer + 1;
        }
        //行数
        int row = matrix.length;
        //列数
        int column = matrix[0].length;
        int y = 0;
        for (int x = 0;x < layer;x++){
            //打印第一行
            for (int j = x;j < (column - x);j++){
//                System.out.println("打印最上边一行：" + matrix[x][j]);
                resultList.add(matrix[x][j]);
            }
            //打印最右边一列
            if (x < (row - x - 1)){
                int line = column - x - 1;
//                System.out.println(line);
                if (line > 0){
                    for (int j = (x + 1);j < (row - x);j++){
//                        System.out.println("打印最右边一列：" + matrix[j][line]);
                        resultList.add(matrix[j][line]);
                    }
                }
                if (line == 0 && column == 1){
                    for (int j = (x + 1);j < (row - x);j++){
//                        System.out.println("打印最右边一列：" + matrix[j][line]);
                        resultList.add(matrix[j][line]);
                    }
                }
            }
            //打印最下一行
            if (x < (row - x - 1) && y < (column - x - 1)){
                int tailRow = row - x - 1;
                for (int j = (column - x - 2);j >= x;j--){
//                    System.out.println("打印最下边一行：" + matrix[tailRow][j]);
                    resultList.add(matrix[tailRow][j]);
                }
            }
            //打印最左一列
            if (x < (row - x - 2) && y <= (column - x - 2)){
                for (int j = (row - x - 2);j > x;j--){
//                    System.out.println("打印最左边一行：" + matrix[j][x]);
                    resultList.add(matrix[j][x]);
                }
            }
            y++;
        }
        return resultList;
    }

    public static void main(String[] args) {
        PrintMatrix print = new PrintMatrix();
        int matrix4[][] = new int[][]{{1,2},
                                      {3,4},
                                      {5,6},
                                      {7,8},
                                      {9,10}};
        int matrix[][] = new int[][]{{ 1, 2, 3, 4},
                                     { 5, 6, 7, 8},
                                     { 9,10,11,12},
                                     {13,14,15,16}};
        int matrix1[][] = new int[][]{{1,2,3},
                                      {4,5,6},
                                      {7,8,9}};
        int matrix2[][] = new int[][]{{1,2,3,4,5}};
        int matrix3[][] = new int[][]{{1},{2},{3},{4},{5}};

        ArrayList<Integer> list = print.printMatrix(matrix4);
        System.out.println(list);
    }
}
