package JianZhiOffer_02_替换空格;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/14 23:25
 *
 * 2、替换空格
 * 题目描述：
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceBlank {
    /**
     * 最优解：
     * 在原字符串上操作
     * 从后往前遍历替换：每个字符只移动一次
     * 时间复杂度为O(n)
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0){
            return "";
        }
        char[] chars = str.toString().toCharArray();
        int blankSpaceNum = 0;//空格数
        //遍历字符串统计空格个数
        for (char ch : chars){
            if (ch == ' '){
                blankSpaceNum++;
            }
        }
        int oldLength = str.length();//原字符串长度
        int newLength = str.length() + blankSpaceNum * 2;//新字符串长度
        str.setLength(newLength);//重置字符串的长度

        oldLength--;
        newLength--;
        while (oldLength >= 0 && newLength > oldLength){
            if (str.charAt(oldLength) == ' '){
                str.setCharAt(newLength--,'0');
                str.setCharAt(newLength--,'2');
                str.setCharAt(newLength--,'%');
            }else {
                str.setCharAt(newLength--,str.charAt(oldLength));
            }
            oldLength--;
        }

        return str.toString();
    }

    //使用jdk中String类库的replaceAll()方法解题
    public String replaceSpace1(StringBuffer str) {
        if (str == null || str.length() == 0){
            return "";
        }
        return str.toString().replaceAll(" ","%20");
    }

    //新建一个StringBuffer对象来过渡
    public String replaceSpace2(StringBuffer str) {
        if (str == null || str.length() == 0){
            return "";
        }
        StringBuffer res = new StringBuffer("");
        char[] chars = str.toString().toCharArray();
        for (char ch : chars){
            if (ch == ' '){
                res.append("%20");
            }else {
                res.append(ch);
            }
        }
        return res.toString();
    }

    /**
     * 在原字符串上操作
     * 从前往后遍历替换：有的字符可能会移动多次
     * 时间复杂度为O(n^2)
     */
    public String replaceSpace3(StringBuffer str) {
        if (str == null || str.length() == 0){
            return "";
        }
        char[] chars = str.toString().toCharArray();
        int blankSpaceNum = 0;//空格数
        //遍历字符串统计空格个数
        for (char ch : chars){
            if (ch == ' '){
                blankSpaceNum++;
            }
        }
        int oldLength = str.length();//原字符串长度
        int newLength = str.length() + blankSpaceNum * 2;//新字符串长度
        str.setLength(newLength);//重置字符串的长度
        int index;
        for (int i=0;i<newLength;i++){
            index = oldLength - 1;
            if (str.charAt(i) == ' '){
                if (i != index){
                    for (int j=index;j>i;j--){
                        str.setCharAt(j + 2,str.charAt(index--));
                    }
                }
                str.setCharAt(i++,'%');
                str.setCharAt(i++,'2');
                str.setCharAt(i,'0');
                oldLength = oldLength + 2;
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer("hello world");
        System.out.println(new ReplaceBlank().replaceSpace3(buffer));
    }
}