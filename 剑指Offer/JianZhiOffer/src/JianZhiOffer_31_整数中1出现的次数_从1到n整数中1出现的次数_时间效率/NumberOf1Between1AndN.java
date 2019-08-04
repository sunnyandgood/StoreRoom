package JianZhiOffer_31_整数中1出现的次数_从1到n整数中1出现的次数_时间效率;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/1 23:20
 * 31、整数中1出现的次数(从1到n整数中1出现的次数)
 * 题目描述：
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */
public class NumberOf1Between1AndN {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count=0;
        while(n>0){
            String str = String.valueOf(n);
            char [] chars=str.toCharArray();
            for(int i = 0;i < chars.length;i++){
                if(chars[i] == '1')
                    count++;
            }
            n--;
        }
        return count;
    }

//    public int NumberOf1Between1AndN(int n) {
//        if (n <= 0){
//            return 0;
//        }
//        char[] chars = String.valueOf(n).toCharArray();
//        return numberOf1(chars,0);
//    }
//    private int numberOf1(char[] chars,int index){
//        if (chars == null || chars.length == 0 || chars[index] < '0' || chars[index] > '9' || index > chars.length - 1){
//            return 0;
//        }
//        int first = chars[index] - '0';
//        int length = chars.length - (index + 1);
//        if (length == 1 && first == 0){
//            return 0;
//        }
//        if (length == 1 && first > 0){
//            return 1;
//        }
//        int numFirstDigit = 0;
//        if (first > 1){
//            numFirstDigit = powerBase10(length - 1);
//        }else if (first == 1 && index < chars.length - 1){
//            numFirstDigit = Integer.parseInt(String.valueOf(chars[index + 1])) + 1;
//        }
//        int numOtherDigits = first * (length - 1) * powerBase10(length - 2);
//        int numRecursive = 0;
//        if (index < chars.length - 1){
//            numberOf1(chars,index + 1);
//        }
//        return numFirstDigit + numOtherDigits + numRecursive;
//    }
//    private int powerBase10(int num){
//        int result = 1;
//        for (int i = 0;i < num;i++){
//            result *= 10;
//        }
//        return result;
//    }

    public static void main(String[] args) {
        NumberOf1Between1AndN number = new NumberOf1Between1AndN();
//        System.out.println(number.NumberOf1Between1AndN(1));
//        System.out.println(Integer.parseInt(String.valueOf('1')));
//        char[] chars = String.valueOf(22).toCharArray();
//        System.out.println(Arrays.toString(chars));
    }
}
