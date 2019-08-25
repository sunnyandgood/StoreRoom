package JianZhiOffer_45_扑克牌顺子_抽象建模能力;

import java.util.Arrays;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/25 0:11
 * 45、扑克牌顺子
 * 题目描述
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,
 * 看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
 * 他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
 * LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。
 * 为了方便起见,你可以认为大小王是0。
 */
public class ContinuousCards {
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length == 0){
            return false;
        }
        Arrays.sort(numbers);
        int zeroNum = 0;
        //统计数组中0的个数
        for (int num : numbers){
            if (num == 0){
                zeroNum += 1;
            }
        }
        //统计数组中间隔数目
        int small = zeroNum;
        int big = small + 1;
        int gapNum = 0;
        //统计数组中的间隔数目
        while (big < numbers.length){
            //两个数字相等，有对子，不可能是顺子
            if (numbers[small] == numbers[big]){
                return false;
            }
            gapNum += numbers[big] - numbers[small] - 1;
            small = big;
            ++big;
        }
        return zeroNum >= gapNum ? true : false;
    }

    public boolean isContinuous_1(int[] numbers) {
        if (numbers == null || numbers.length == 0){
            return false;
        }
        Arrays.sort(numbers);
        // 记录空缺数
        int vacancyCount = 0;
        // 记录大小王的个数
        int kingCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0){
                kingCount++;
            }
            else if (i + 1 < numbers.length) {
                // 两数相等，不可能是顺子
                if (numbers[i] == numbers[i + 1]) {
                    return false;
                }
                vacancyCount += numbers[i + 1] - numbers[i] - 1;
            }
        }
        return kingCount >= vacancyCount;
    }
}
