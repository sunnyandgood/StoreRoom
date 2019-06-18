package JianZhiOffer_12_数值的整数次方;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/18 23:55
 * 12、数值的整数次方
 * 题目描述:
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * 注意事项：
 * 由于计算机表示小数（包括float和double型小数）都有误差，我们不能直接用等号（==）
 * 判断两个小数是否相等。如果两个小数的差的绝对值很小，比如小于0.0000001，就可以认为它们相等。
 *
 * 在判断底数base是不是等于0时，不能直接写base==0，这是因为在计算机内表示小数时（包括float和double型小数）都有误差。
 * 判断两个小数是否相等，只能判断它们之差的绝对值是不是在一个很小的范围内。如果两个数相差很小，就可以认为它们相等。
 */
public class Power {
    /**
     * 假设我们的目标是求出一个数字的32次方，如果我们已经知道了它的16次方，那么只要在16次方的基础上再平方一次就可以了。而16次方是8次方的平方。
     * 这样以此类推，我们求32次方只需要做5次乘法：先求平方，在平方的基础上求4次方，在4次方的基础上求8次方，
     * 在8次方的基础上求16次方，最后在16次方的基础上求32次方。
     * 由此可以推到出递推公式：
     * a^n = a^(n/2) * a^(n/2);             //n为偶数
     * a^n = a^((n-1)/2) * a^((n-1)/2) * a; //n为奇数
     */
    public double powerWithBaseAndUnsignedExponent(double base,int unsignedExponent){
        if (unsignedExponent == 0){
            return 1;
        }
        if (unsignedExponent == 1){
            return base;
        }
        double res = powerWithBaseAndUnsignedExponent(base,unsignedExponent >> 1);
        res *= res;
        if ((unsignedExponent & 1) == 1){
            res *= base;
        }
        return res;
    }
    public double Power(double base, int exponent) {
        //预防后边对0求倒数
        if ((base - 0.0 < 0.0000001) && (0.0 - base > -0.0000001)){//判断浮点数base是否为0
            if (exponent < 0){
                return 0.0;
            }
        }
        int newExponent = exponent;
        if (exponent < 0){
            newExponent = -exponent;
        }
        double res = powerWithBaseAndUnsignedExponent(base, newExponent);
        if (exponent < 0){
            res = 1 / res;
        }
        return res;
    }


    /**
     * 当指数为负数的时候，可以先对指数求绝对值，然后算出次方的结果之后再取倒数。
     * 既然有求倒数，我们很自然就要想到有没有可能对0求倒数，如果对0求倒数怎么办？
     * 当底数（base）是零且指数是负数的时候，如果不做特殊处理，就会出现对0求倒数从而导致程序运行出错。
     * 由于0的0次方在数学上是没有意义的，因此无论是输出0还是1都是可以接受的
     */
    public double Power1(double base, int exponent) {
        //预防后边对0求倒数
        if ((base - 0.0 < 0.0000001) && (0.0 - base > -0.0000001)){//判断浮点数base是否为0
            if (exponent < 0){
                return 0.0;
            }
        }

        double res = 1.0;
        int newExponent = exponent;
        if (exponent < 0){
            newExponent = -exponent;
        }
        while (newExponent > 0){
            res *= base;
            newExponent--;
        }
        if (exponent < 0){
            res = 1 / res;
        }
        return res;
    }

    public static void main(String[] args) {
        Power power = new Power();
        System.out.println(power.Power1(2.0,2));
    }
}
