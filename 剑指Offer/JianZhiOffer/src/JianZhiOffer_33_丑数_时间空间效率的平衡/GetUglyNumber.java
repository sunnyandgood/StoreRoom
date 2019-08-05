package JianZhiOffer_33_丑数_时间空间效率的平衡;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/5 23:26
 * 33、丑数
 * 题目描述：
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class GetUglyNumber {
    public int GetUglyNumber_Solution(int index) {
        if(index <= 0){
            return 0;
        }
        int[] result = new int[index];
        int count = 0;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        result[0] = 1;
        int tmp = 0;
        while (count < index-1) {
            tmp = min(result[i2] * 2, min(result[i3] * 3, result[i5] * 5));
            if(tmp == result[i2] * 2) {
                i2++;//三条if防止值是一样的，不要改成else的
            }
            if(tmp == result[i3] * 3) {
                i3++;
            }
            if(tmp == result[i5]*5) {
                i5++;
            }
            result[++count] = tmp;
        }
        return result[index - 1];
    }

    private int min(int a, int b) {
        return (a > b) ? b : a;
    }
}
