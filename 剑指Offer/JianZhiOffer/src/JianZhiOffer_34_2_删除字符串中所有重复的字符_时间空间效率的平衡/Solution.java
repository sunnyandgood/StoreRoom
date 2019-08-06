package JianZhiOffer_34_2_删除字符串中所有重复的字符_时间空间效率的平衡;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/6 23:12
 * 34.2、删除字符串中所有重复的字符
 * eg：google --->  gole
 */
public class Solution {
    public String solution(String str){
        if (str == null || str.length() == 0){
            return str;
        }
        boolean[] flag = new boolean[256];
        char[] chars = str.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (char ch : chars){
            if (!flag[ch]){
                flag[ch] = true;
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("google"));
    }
}
