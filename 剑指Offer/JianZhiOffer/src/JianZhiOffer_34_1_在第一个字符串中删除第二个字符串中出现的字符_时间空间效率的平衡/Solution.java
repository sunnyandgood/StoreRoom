package JianZhiOffer_34_1_在第一个字符串中删除第二个字符串中出现的字符_时间空间效率的平衡;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/6 22:53
 * 34.1、在第一个字符串中删除第二个字符串中出现的字符
 */
public class Solution {
    public String solution(String str1,String str2){
        if (str1 == null || str1.length() == 0 || str2 == null ||str2.length() == 0){
            return str1;
        }
        int[] direction = new int[256];
        char[] chars2 = str2.toCharArray();
        for (char ch : chars2){
            direction[ch] = 1;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0;i < str1.length();i++){
            if (direction[str1.charAt(i)] != 1){
                buffer.append(str1.charAt(i));
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("We are Student","aeiou"));
    }
}
