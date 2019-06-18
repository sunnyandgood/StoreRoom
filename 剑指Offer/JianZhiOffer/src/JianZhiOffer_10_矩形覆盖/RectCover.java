package JianZhiOffer_10_矩形覆盖;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/18 12:40
 * 10、矩形覆盖
 * 题目描述：
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 题目分析：
 * 我们可以用2×1的小矩形横着或者竖着去覆盖更大的矩形。请问用8个2×1的小矩形无重叠地覆盖一个2×8的大矩形，总共有多少种方法？
 * 先把2×8的覆盖方法记为f（8）。用第一个1×2小矩形去覆盖大矩形的最左边时有两个选择，竖着放或者横着放。
 * 当竖着放的时候，右边还剩下2×7的区域，这种情形下的覆盖方法记为f（7）。
 * 接下来考虑横着放的情况。当1×2的小矩形横着放在左上角的时候，左下角必须和横着放一个1×2的小矩形，而在右边还还剩下2×6的区域，
 * 这种情形下的覆盖方法记为f（6），因此f（8）=f（7）＋f（6）。
 * 此时我们可以看出，这仍然是斐波那契数列。
 *
 *  斐波那契数列：0 1 1 2 3 5  8 13 21 34 55 89
 *  矩形覆盖的数：0 1 2 3 5 8 13 21 34 55 89
 */
public class RectCover {
    public int RectCover(int target) {
        if (target < 0){
            return -1;
        }
        if (target <= 2){
            return target;
        }
        int res = -1;
        int pre = 1;
        int cur = 2;
        for (int i=3;i<=target;i++){
            res = pre + cur;
            pre = cur;
            cur = res;
        }
        return res;
    }

    public static void main(String[] args) {
        RectCover cover = new RectCover();
        for (int i=0;i<=10;i++){
            System.out.println(cover.RectCover(i));
        }
    }
}
